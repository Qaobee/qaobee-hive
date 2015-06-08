/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may 
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.technical.tools;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;

import com.lowagie.text.Image;

/**
 * A factory for creating MediaReplacedElement objects.
 *
 * @author xavier Replaced element in order to replace elements like <tt>&lt;div class="media" data-src="image.png" /></tt> with the real media content.
 */
public class MediaReplacedElementFactory implements ReplacedElementFactory {

	/** The super factory. */
	private final ReplacedElementFactory superFactory;

	/**
	 * Instantiates a new media replaced element factory.
	 *
	 * @param superFactory
	 *            the super factory
	 */
	public MediaReplacedElementFactory(final ReplacedElementFactory superFactory) {
		this.superFactory = superFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xhtmlrenderer.extend.ReplacedElementFactory#createReplacedElement(org.xhtmlrenderer.layout.LayoutContext, org.xhtmlrenderer.render.BlockBox, org.xhtmlrenderer.extend.UserAgentCallback,
	 * int, int)
	 */
	@Override
	public ReplacedElement createReplacedElement(final LayoutContext layoutContext, final BlockBox blockBox, final UserAgentCallback userAgentCallback, final int cssWidth, final int cssHeight) {
		final Element element = blockBox.getElement();
		if (element == null) {
			return null;
		}
		final String nodeName = element.getNodeName();
		final String className = element.getAttribute("class");
		// Replace any <div class="media" data-src="image.png" /> with the
		// binary data of `image.png` into the PDF.
		if ("div".equals(nodeName) && "media".equals(className)) {
			if (!element.hasAttribute("data-src")) {
				throw new RuntimeException("An element with class `media` is missing a `data-src` attribute indicating the media file.");
			}
			InputStream input = null;
			try {
				input = new FileInputStream(element.getAttribute("data-src"));
				final byte[] bytes = IOUtils.toByteArray(input);
				final Image image = Image.getInstance(bytes);
				final FSImage fsImage = new ITextFSImage(image);
				if (fsImage != null) {
					if (cssWidth != -1 || cssHeight != -1) {
						fsImage.scale(cssWidth, cssHeight);
					}
					return new ITextImageElement(fsImage);
				}
			} catch (final Exception e) {
				throw new RuntimeException("There was a problem trying to read a template embedded graphic.", e);
			} finally {
				IOUtils.closeQuietly(input);
			}
		}
		return superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xhtmlrenderer.extend.ReplacedElementFactory#reset()
	 */
	@Override
	public void reset() {
		superFactory.reset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xhtmlrenderer.extend.ReplacedElementFactory#remove(org.w3c.dom.Element)
	 */
	@Override
	public void remove(final Element e) {
		superFactory.remove(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xhtmlrenderer.extend.ReplacedElementFactory#setFormSubmissionListener(org.xhtmlrenderer.simple.extend.FormSubmissionListener)
	 */
	@Override
	public void setFormSubmissionListener(final FormSubmissionListener listener) {
		superFactory.setFormSubmissionListener(listener);
	}
}