/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.technical.vertx.utils;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import com.qaobee.technical.constantes.Constantes;
import com.qaobee.technical.exceptions.ExceptionCodes;
import com.qaobee.technical.exceptions.QaobeeException;
import com.qaobee.technical.mongo.MongoDB;
import com.qaobee.technical.utils.Utils;
import com.qaobee.technical.vertx.RequestWrapper;
import com.qaobee.technical.vertx.utils.guice.AbstractGuiceVerticle;

/**
 * The Class CrudVerticle.
 * 
 * @author xavier
 */
public abstract class CrudVerticle<T> extends AbstractGuiceVerticle {

	/** The sorted field. */
	private String sortedField = null;

	/** The criteria. */
	private Map<String, Object> criteria = null;

	/** The minimal fields. */
	private List<String> minimalFields = null;

	/** The upsert mandatory fields. */
	private List<String> upsertMandatoryFields = new ArrayList<String>();
	@Inject
	protected MongoDB mongo;
	@Inject
	protected Utils utils;

	/**
	 * Retrieve the collection object
	 * 
	 * @return the collection
	 */
	protected Class<?> getCollection() {
		return CrudVerticle.getTypeArguments(CrudVerticle.class, this.getClass()).get(0);
	}

	/** The list handler. */
	final Handler<Message<String>> listHandler = new Handler<Message<String>>() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
		 */
		@Override
		public void handle(final Message<String> message) {
			final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
			try {
				utils.testHTTPMetod(Constantes.GET, req.getMethod());
				message.reply(mongo.findByCriterias(criteria, minimalFields, sortedField, 1, -1, getCollection()).encode());
			} catch (final NoSuchMethodException e) {
				container.logger().error(e.getMessage(), e);
				utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
			}
		}
	};

	/** The add handler. */
	final Handler<Message<String>> addHandler = new Handler<Message<String>>() {
		@Override
		public void handle(final Message<String> message) {
			final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
			try {
				utils.testHTTPMetod(Constantes.PUT, req.getMethod());
				utils.testMandatoryParams(new JsonObject(req.getBody()).toMap(), (String[]) upsertMandatoryFields.toArray(new String[] {}));
				final JsonObject json = new JsonObject(req.getBody());
				final String id = mongo.save(json, getCollection());
				json.putString("_id", id);
				message.reply(json.encode());
			} catch (final NoSuchMethodException e) {
				container.logger().error(e.getMessage(), e);
				utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
			} catch (final EncodeException e) {
				container.logger().error(e.getMessage(), e);
				utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
			} catch (final QaobeeException e) {
				container.logger().error(e.getMessage(), e);
				utils.sendError(message, e);
			} catch (final IllegalArgumentException e) {
				container.logger().error(e.getMessage(), e);
				utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
			}
		}
	};

	/** The del handler. */
	final Handler<Message<String>> delHandler = new Handler<Message<String>>() {
		@Override
		public void handle(final Message<String> message) {
			final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
			try {
				utils.testHTTPMetod(Constantes.DELETE, req.getMethod());
				mongo.deleteById(req.getParams().get("id").get(0), getCollection());
				utils.sendStatus(true, message);
			} catch (final NoSuchMethodException e) {
				container.logger().error(e.getMessage(), e);
				utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
			}
		}
	};

	/** The get handler. */
	final Handler<Message<String>> getHandler = new Handler<Message<String>>() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
		 */
		@Override
		public void handle(final Message<String> message) {
			final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
			try {
				utils.testHTTPMetod(Constantes.GET, req.getMethod());
				utils.testMandatoryParams(req.getParams(), "id");
				message.reply(mongo.getById(req.getParams().get("id").get(0), getCollection()).encode());
			} catch (final NoSuchMethodException e) {
				container.logger().error(e.getMessage(), e);
				utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
			} catch (final QaobeeException e) {
				container.logger().error(e.getMessage(), e);
				utils.sendError(message, e);
			} catch (final IllegalArgumentException e) {
				container.logger().error(e.getMessage(), e);
				utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
			}
		}
	};

	/**
	 * Get the actual type arguments a child class has used to extend a generic base class.
	 * 
	 * @param baseClass
	 *            the base class
	 * @param childClass
	 *            the child class
	 * @return a list of the raw classes for the actual type arguments.
	 */
	public static <T> List<Class<?>> getTypeArguments(Class<T> baseClass, Class<?> childClass) {
		Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
		Type type = childClass;
		// start walking up the inheritance hierarchy until we hit baseClass
		while (!getClass(type).equals(baseClass)) {
			if (type instanceof Class) {
				// there is no useful information for us in raw types, so just keep going.
				type = ((Class<?>) type).getGenericSuperclass();
			} else {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Class<?> rawType = (Class<?>) parameterizedType.getRawType();

				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
				for (int i = 0; i < actualTypeArguments.length; i++) {
					resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);
				}

				if (!rawType.equals(baseClass)) {
					type = rawType.getGenericSuperclass();
				}
			}
		}

		// finally, for each actual type argument provided to baseClass, determine (if possible)
		// the raw class for that type argument.
		Type[] actualTypeArguments;
		if (type instanceof Class) {
			actualTypeArguments = ((Class<?>) type).getTypeParameters();
		} else {
			actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
		}
		List<Class<?>> typeArgumentsAsClasses = new ArrayList<Class<?>>();
		// resolve types by chasing down type variables.
		for (Type baseType : actualTypeArguments) {
			while (resolvedTypes.containsKey(baseType)) {
				baseType = resolvedTypes.get(baseType);
			}
			typeArgumentsAsClasses.add(getClass(baseType));
		}
		return typeArgumentsAsClasses;
	}

	/**
	 * Get the underlying class for a type, or null if the type is a variable type.
	 * 
	 * @param type
	 *            the type
	 * @return the underlying class
	 */
	public static Class<?> getClass(Type type) {
		if (type instanceof Class) {
			return (Class<?>) type;
		} else if (type instanceof ParameterizedType) {
			return getClass(((ParameterizedType) type).getRawType());
		} else if (type instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) type).getGenericComponentType();
			Class<?> componentClass = getClass(componentType);
			if (componentClass != null) {
				return Array.newInstance(componentClass, 0).getClass();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Gets the sorted field.
	 * 
	 * @return the sortedField
	 */
	public String getSortedField() {
		return sortedField;
	}

	/**
	 * Sets the sorted field.
	 * 
	 * @param sortedField
	 *            the sortedField to set
	 */
	public void setSortedField(final String sortedField) {
		this.sortedField = sortedField;
	}

	/**
	 * Gets the list handler.
	 * 
	 * @return the listHandler
	 */
	public Handler<Message<String>> getListHandler() {
		return listHandler;
	}

	/**
	 * Gets the adds the handler.
	 * 
	 * @return the addHandler
	 */
	public Handler<Message<String>> getAddHandler() {
		return addHandler;
	}

	/**
	 * Gets the del handler.
	 * 
	 * @return the delHandler
	 */
	public Handler<Message<String>> getDelHandler() {
		return delHandler;
	}

	/**
	 * Gets the gets the handler.
	 * 
	 * @return the getHandler
	 */
	public Handler<Message<String>> getGetHandler() {
		return getHandler;
	}

	/**
	 * Gets the criteria.
	 * 
	 * @return the criteria
	 */
	public Map<String, Object> getCriteria() {
		return criteria;
	}

	/**
	 * Sets the criteria.
	 * 
	 * @param criteria
	 *            the criteria to set
	 */
	public void setCriteria(final Map<String, Object> criteria) {
		this.criteria = criteria;
	}

	/**
	 * Gets the minimal fields.
	 * 
	 * @return the minimalFields
	 */
	public List<String> getMinimalFields() {
		return minimalFields;
	}

	/**
	 * Sets the minimal fields.
	 * 
	 * @param minimalFields
	 *            the minimalFields to set
	 */
	public void setMinimalFields(final List<String> minimalFields) {
		this.minimalFields = minimalFields;
	}

	/**
	 * Gets the upsert mandatory fields.
	 * 
	 * @return the upsertMandatoryFields
	 */
	public List<String> getUpsertMandatoryFields() {
		return upsertMandatoryFields;
	}

	/**
	 * Sets the upsert mandatory fields.
	 * 
	 * @param upsertMandatoryFields
	 *            the upsertMandatoryFields to set
	 */
	public void setUpsertMandatoryFields(final List<String> upsertMandatoryFields) {
		this.upsertMandatoryFields = upsertMandatoryFields;
	}

}
