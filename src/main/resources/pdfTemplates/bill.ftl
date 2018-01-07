<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional" "https://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="https://www.w3.org/1999/xhtml" lang="fr">
<head>
    <title>Votre reçu de Qaobee [#${receipt_number!''}]</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width"/>
    <meta name="robots" content="noindex, nofollow"/>
    <meta name="googlebot" content="noindex, nofollow, noarchive"/>

    <style type="text/css">
        a {
            text-decoration: none !important;
        }

        /* overrides addresses, numbers, etc. being linked */

        span.apple-override-header a {
            color: #ffffff !important;
            text-decoration: none !important;
        }

        span.apple-override-hidden a {
            color: #d53239 !important;
            text-decoration: none !important;
        }

        span.apple-override-dark a {
            color: #292e31 !important;
            text-decoration: none !important;
        }

        span.apple-override-light a {
            color: #77858c !important;
            text-decoration: none !important;
        }

        /* [override] prevents Yahoo Mail breaking media queries */

        /* retina */
        @media (-webkit-min-device-pixel-ratio: 1.25), (min-resolution: 120dpi) {

            body[override] span.retina img {
                visibility: hidden !important;
            }

            body[override] td.icon span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/acct_1ALu4nArxO1IWesL/3/circular_icon@2x.png') no-repeat 0 0 !important;
                background-size: 72px 72px !important;
                display: block !important;
                height: 72px !important;
                width: 72px !important;
            }

            body[override] table.card td.card-type span.retina {
                display: block !important;
                height: 16px !important;
            }

            /* default */
            body[override] table.card-default td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/default-light@2x.png') no-repeat 0 0 !important;
                background-size: 22px 16px !important;
                width: 22px !important;
            }

            body[override] table.card-default tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/default-dark@2x.png') no-repeat 0 0 !important;
                background-size: 22px 16px !important;
                width: 22px !important;
            }

            /* visa */
            body[override] table.card-visa td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/visa-light@2x.png') no-repeat 0 0 !important;
                background-size: 36px 16px !important;
                width: 36px !important;
            }

            body[override] table.card-visa tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/visa-dark@2x.png') no-repeat 0 0 !important;
                background-size: 36px 16px !important;
                width: 36px !important;
            }

            /* mastercard */
            body[override] table.card-mastercard td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/mastercard-light@2x.png') no-repeat 0 0 !important;
                background-size: 75px 16px !important;
                width: 75px !important;
            }

            body[override] table.card-mastercard tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/mastercard-dark@2x.png') no-repeat 0 0 !important;
                background-size: 75px 16px !important;
                width: 75px !important;
            }

            /* amex */
            body[override] table.card-amex td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/amex-light@2x.png') no-repeat 0 0 !important;
                background-size: 45px 16px !important;
                width: 45px !important;
            }

            body[override] table.card-amex tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/amex-dark@2x.png') no-repeat 0 0 !important;
                background-size: 45px 16px !important;
                width: 45px !important;
            }

            /* discover */
            body[override] table.card-discover td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/discover-light@2x.png') no-repeat 0 0 !important;
                background-size: 57px 16px !important;
                width: 57px !important;
            }

            body[override] table.card-discover tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/discover-dark@2x.png') no-repeat 0 0 !important;
                background-size: 57px 16px !important;
                width: 57px !important;
            }

            /* jcb */
            body[override] table.card-jcb td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/jcb-light@2x.png') no-repeat 0 0 !important;
                background-size: 19px 16px !important;
                width: 19px !important;
            }

            body[override] table.card-jcb tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/jcb-dark@2x.png') no-repeat 0 0 !important;
                background-size: 19px 16px !important;
                width: 19px !important;
            }

            /* diners */
            body[override] table.card-diners td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/diners-light@2x.png') no-repeat 0 0 !important;
                background-size: 20px 16px !important;
                width: 20px !important;
            }

            body[override] table.card-diners tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/diners-dark@2x.png') no-repeat 0 0 !important;
                background-size: 20px 16px !important;
                width: 20px !important;
            }

        }

        /* tablets */
        @media all and (max-device-width: 768px) {

            body[override] span.retina img {
                visibility: hidden !important;
            }

            body[override] td.font-large, body[override] td.font-large span, body[override] td.font-large a {
                font-size: 18px !important;
                line-height: 25px !important;
            }

            body[override] td.font-medium, body[override] td.font-medium span, body[override] td.font-medium a {
                font-size: 16px !important;
                line-height: 23px !important;
            }

            body[override] td.font-small, body[override] td.font-small span, body[override] td.font-small a {
                font-size: 15px !important;
                line-height: 21px !important;
            }

            body[override] td.icon span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/acct_1ALu4nArxO1IWesL/3/circular_icon@2x.png') no-repeat 0 0 !important;
                background-size: 72px 72px !important;
                display: block !important;
                height: 72px !important;
                width: 72px !important;
            }

            body[override] td.title, body[override] td.title span, body[override] td.title a {
                font-size: 24px !important;
                line-height: 28px !important;
            }

            body[override] table.card td.card-type span.retina {
                display: block !important;
                height: 19px !important;
            }

            /* default */
            body[override] table.card-default td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/default-light@2x.png') no-repeat 0 0 !important;
                background-size: 25px 19px !important;
                width: 25px !important;
            }

            body[override] table.card-default tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/default-dark@2x.png') no-repeat 0 0 !important;
                background-size: 25px 19px !important;
                width: 25px !important;
            }

            /* visa */
            body[override] table.card-visa td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/visa-light-mobile.png') no-repeat 0 0 !important;
                background-size: 43px 19px !important;
                width: 43px !important;
            }

            body[override] table.card-visa tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/visa-dark-mobile.png') no-repeat 0 0 !important;
                background-size: 43px 19px !important;
                width: 43px !important;
            }

            /* mastercard */
            body[override] table.card-mastercard td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/mastercard-light-mobile.png') no-repeat 0 0 !important;
                background-size: 87px 19px !important;
                width: 87px !important;
            }

            body[override] table.card-mastercard tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/mastercard-dark-mobile.png') no-repeat 0 0 !important;
                background-size: 87px 19px !important;
                width: 87px !important;
            }

            /* amex */
            body[override] table.card-amex td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/amex-light-mobile.png') no-repeat 0 0 !important;
                background-size: 53px 19px !important;
                width: 53px !important;
            }

            body[override] table.card-amex tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/amex-dark-mobile.png') no-repeat 0 0 !important;
                background-size: 53px 19px !important;
                width: 53px !important;
            }

            /* discover */
            body[override] table.card-discover td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/discover-light-mobile.png') no-repeat 0 0 !important;
                background-size: 70px 19px !important;
                width: 70px !important;
            }

            body[override] table.card-discover tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/discover-dark-mobile.png') no-repeat 0 0 !important;
                background-size: 70px 19px !important;
                width: 70px !important;
            }

            /* jcb */
            body[override] table.card-jcb td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/jcb-light@2x.png') no-repeat 0 0 !important;
                background-size: 22px 19px !important;
                width: 22px !important;
            }

            body[override] table.card-jcb tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/jcb-dark@2x.png') no-repeat 0 0 !important;
                background-size: 22px 19px !important;
                width: 22px !important;
            }

            /* diners */
            body[override] table.card-diners td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/diners-light@2x.png') no-repeat 0 0 !important;
                background-size: 23px 19px !important;
                width: 23px !important;
            }

            body[override] table.card-diners tr.card-dark td.card-type span.retina {
                background: url('https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/diners-dark@2x.png') no-repeat 0 0 !important;
                background-size: 23px 19px !important;
                width: 23px !important;
            }

            /* */
            body[override] table.card td.card-digits, body[override] table.card td.card-digits span {
                font-size: 16px !important;
                line-height: 16px !important;
            }

            body[override] td.summary-total {
                font-size: 20px !important;
                line-height: 25px !important;
            }

        }

        /* mobile */
        @media all and (max-device-width: 500px) {

            body[override] table.width, body[override] td.width {
                width: 100% !important;
            }

            body[override] td.temp-padding, body[override] td.temp-padding div.clear {
                display: none !important;
                width: 0 !important;
            }

            body[override] td.banner {
                height: 186px !important;
            }

            body[override] td.subbanner-item {
                -moz-box-sizing: border-box !important;
                -webkit-box-sizing: border-box !important;
                box-sizing: border-box !important;
                display: block !important;
                padding-left: 10px !important;
                padding-right: 10px !important;
                text-align: center !important;
                width: 100% !important;
            }

            body[override] tr.summary-item td.summary-padding, body[override] tr.summary-item td.summary-padding div.clear {
                width: 17px !important;
            }

            body[override] a.browser-link {
                display: block !important;
            }

        }
    </style>
</head>
<body bgcolor="f9fafa" style="border: 0; margin: 0; padding: 0; min-width: 100%;" override="fix">

<!-- background -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
    <tr>
        <td bgcolor="f9fafa" style="border: 0; margin: 0; padding: 0;">

            <!-- header -->
            <table style="background-color: #d53239;" border="0" cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                <tr>
                    <td align="center" style="border: 0; margin: 0; padding: 0;">

                        <!-- preheader -->
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tbody>
                            <tr>
                                <td align="center" style="border: 0; margin: 0; padding: 0;">
                                    <table align="center" border="0" cellpadding="0" cellspacing="0" class="width"
                                           width="500">
                                        <tbody>
                                        <tr>
                                            <td align="center" height="20"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; color: #d53239; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;">
                                                Merci pour votre paiement de ${amountPaid!''} € à <span
                                                    class="apple-override-hidden"
                                                    style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; color: #d53239; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;">Qaobee</span>.
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <!-- /preheader -->

                        <!-- banner -->
                        <table border="0" cellpadding="0" cellspacing="0" class="width" width="500">
                            <tbody>
                            <tr>
                                <td height="7"
                                    style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                    width="100%">
                                    <div class="clear" style="height: 7px; width: 1px;">&nbsp;</div>
                                </td>
                            </tr>
                            <tr>
                                <td class="banner"
                                    style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                    valign="middle">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tr>
                                            <td class="perm-padding"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                width="20">
                                                <div class="clear" style="height: 1px; width: 20px;"></div>
                                            </td>
                                            <td style="border: 0; margin: 0; padding: 0;" width="100%">
                                                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                                    <tr>
                                                        <td align="center" class="icon" height="72"
                                                            style="border: 0; margin: 0; padding: 0;">
                                                            <a href="https://www.qaobee.com/"
                                                               style="border: 0; margin: 0; padding: 0;" target="_blank"
                                                               rel="noreferrer">
                                              <span class="retina">
                                                <img alt="" height="72"
                                                     src="https://stripe-images.s3.amazonaws.com/emails/acct_1ALu4nArxO1IWesL/3/circular_icon@2x.png"
                                                     style="border: 0; margin: 0; padding: 0;" width="72"/>
                                              </span>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td height="22"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                            width="100%">
                                                            <div class="clear" style="height: 22px; width: 1px;">
                                                                &nbsp;
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="title"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; color: #ffffff; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 22px; line-height: 25px; text-shadow: 0 1px 1px #bf2d33;">
                                                            <span class="apple-override"
                                                                  style="color: #ffffff; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 22px; line-height: 25px;">${amountPaid!''} €</span>
                                                            <span style="color: #ffffff; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 22px; line-height: 25px; opacity: 0.75;">à</span>
                                                            <a href="https://www.qaobee.com/"
                                                               style="color: #ffffff; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 22px; line-height: 25px; text-decoration: none;"
                                                               target="_blank" rel="noreferrer"><span
                                                                    class="apple-override-header"
                                                                    style="color: #ffffff; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 22px; line-height: 25px; text-decoration: none;">Qaobee</span></a>
                                                        </td>
                                                    </tr>


                                                    <!-- card -->
                                                    <tr>
                                                        <td height="13"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                            width="100%">
                                                            <div class="clear" style="height: 13px; width: 1px;">
                                                                &nbsp;
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" height="1"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                            width="100%">
                                                            <table align="center" border="0" cellpadding="0"
                                                                   cellspacing="0" width="200">
                                                                <tbody>
                                                                <tr>
                                                                    <td bgcolor="#bf2d33"
                                                                        style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                                                        <div class="clear"
                                                                             style="height: 1px; width: 200px;">&nbsp;
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td height="18"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                            width="100%">
                                                            <div class="clear" style="height: 18px; width: 1px;">
                                                                &nbsp;
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                            width="100%">
                                                            <table border="0" cellpadding="0" cellspacing="0"
                                                                   class="card card-visa">
                                                                <tbody>
                                                                <tr class="card-light">

                                                                    <td class="card-type"
                                                                        style="border: 0; margin: 0; padding: 0; mso-line-height-rule: exactly;"
                                                                        valign="top">
              <span class="retina">
                <img alt="Visa" height="16"
                     src="https://stripe-images.s3.amazonaws.com/emails/receipt_assets/card/${brand}-light@2x.png"
                     style="border: 0; margin: 0; padding: 0;" width="36"/>
              </span>
                                                                    </td>
                                                                    <td style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                                        width="6">
                                                                        <div class="clear"
                                                                             style="height: 1px; width: 6px;">&nbsp;
                                                                        </div>
                                                                    </td>
                                                                    <td class="card-digits"
                                                                        style="color: #ffffff; border: 0; margin: 0; padding: 0; mso-line-height-rule: exactly;
                                                                         font-family: 'Lucida Console', monospace;
                                                                         font-size: 14px; line-height: 14px; text-shadow: 0 1px 1px #bf2d33; vertical-align: middle;"
                                                                        valign="middle">
                                                                        <span style="color: #ffffff; font-family: 'Lucida Console', monospace; font-size: 14px; line-height: 14px;">${card!''}</span>
                                                                    </td>
                                                                </tr>
                                                                </tbody>
                                                            </table>

                                                        </td>
                                                    </tr>
                                                    <!-- /card -->


                                                </table>
                                            </td>
                                            <td class="perm-padding"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                width="20">
                                                <div class="clear" style="height: 1px; width: 20px;"></div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td height="27"
                                    style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                    width="100%">
                                    <div class="clear" style="height: 27px; width: 1px;">&nbsp;</div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <!-- /banner -->

                        <!-- subbanner -->
                        <table bgcolor="#bf2d33" border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tbody>
                            <tr>
                                <td align="center"
                                    style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                    <table class="width" border="0" cellpadding="0" cellspacing="0" width="500">
                                        <tbody>
                                        <tr>
                                            <td colspan="4" height="8"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                width="100%">
                                                <div class="clear" style="height: 8px; width: 1px;">&nbsp;</div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="perm-padding"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                width="20">
                                                <div class="clear" style="height: 1px 20px;"></div>
                                            </td>
                                            <td align="left" class="subbanner-item font-small"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; color: #ffffff; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 13px; line-height: 17px; text-shadow: 0 1px 1px #bf2d33;"
                                                width="230">
                                    <span
                                            class="apple-override-header"
                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; color: #ffffff; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 13px; line-height: 17px; text-shadow: 0 1px 1px #bf2d33;"
                                    >
                                         ${paidDate!''}
                                    </span>
                                            </td>
                                            <td align="right" class="subbanner-item font-small"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; color: #ffffff; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 13px; line-height: 17px; text-shadow: 0 1px 1px #bf2d33;"
                                                width="230">
                                                <span class="apple-override-header"
                                                      style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; color: #ffffff; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 13px; line-height: 17px; text-shadow: 0 1px 1px #bf2d33;">#${receipt_number!''}</span>
                                            </td>
                                            <td class="perm-padding"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                width="20">
                                                <div class="clear" style="height: 1px 20px;"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" height="8"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;"
                                                width="100%">
                                                <div class="clear" style="height: 8px; width: 1px;">&nbsp;</div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <!-- /subbanner -->

                    </td>
                </tr>
                </tbody>
            </table>
            <!-- /header -->

            <!-- summary -->
            <table bgcolor="ffffff" border="0" cellpadding="0" cellspacing="0" style="border-bottom: 1px solid #e4e6e8;"
                   width="100%">
                <tbody>
                <tr>
                    <td align="center" style="border: 0; margin: 0; padding: 0;">
                        <table border="0" cellpadding="0" cellspacing="0" class="width" width="500">
                            <tbody>
                            <tr>
                                <td class="temp-padding"
                                    style="border: 0; margin: 0; padding: 0; mso-line-height-rule: exactly; font-size: 1px; line-height: 1px;">
                                    <div class="clear" style="height: 1px; width: 20px;"></div>
                                </td>
                                <td style="border: 0; margin: 0; padding: 0; mso-line-height-rule: exactly; font-size: 1px; line-height: 1px;"
                                    width="460">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tbody>

                                        <!-- title -->
                                        <tr>
                                            <td colspan="5" height="12"
                                                style="border: 0; margin: 0; padding: 0; mso-line-height-rule: exactly; font-size: 1px; line-height: 1px;">
                                                <div class="clear" style="height: 12px; width: 1px;">&nbsp;</div>
                                            </td>
                                        </tr>
                                        <tr class="summary-item">
                                            <td class="summary-padding"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                width="1">
                                                <div class="clear" style="height: 1px; width: 1px;"></div>
                                            </td>
                                            <td align="left" class="font-small"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; color: #77858c; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 12px; font-weight: bold; line-height: 20px;"
                                                width="100%">
                                                Description
                                            </td>
                                            <td style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                width="10">
                                                <div class="clear" style="height: 1px; width: 10px;"></div>
                                            </td>
                                            <td align="right" class="font-small"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; color: #77858c; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 12px; font-weight: bold; line-height: 20px;"
                                                width="120">
                                                Total
                                            </td>
                                            <td class="summary-padding"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                width="1">
                                                <div class="clear" style="height: 1px; width: 1px;"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" height="12"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; border-bottom: 1px solid #eaeff2;">
                                                <div class="clear" style="height: 12px; width: 1px;">&nbsp;</div>
                                            </td>
                                        </tr>
                                        <!-- /title -->

                                        <!-- item -->
                                        <tr>
                                            <td colspan="5" height="11"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; border-top: 1px solid #eaeff2;">
                                                <div class="clear" style="height: 11px; width: 1px;">&nbsp;</div>
                                            </td>
                                        </tr>
                                        <tr class="summary-item">
                                            <td class="summary-padding"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                width="1">
                                                <div class="clear" style="height: 1px; width: 1px;"></div>
                                            </td>
                                            <td align="left" class="font-medium"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; color: #292e31; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 17px;"
                                                width="100%">
                                                <span class="apple-override-dark"
                                                      style="border: 0; margin: 0; padding: 0;">Abonnement à ${plan!''}</span>
                                            </td>
                                            <td style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                width="10">
                                                <div class="clear" style="height: 1px; width: 10px;"></div>
                                            </td>
                                            <td align="right" class="font-medium"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; color: #292e31; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 17px;"
                                                width="120">
                                            ${amountPaid!''} €
                                            </td>
                                            <td class="summary-padding"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                width="1">
                                                <div class="clear" style="height: 1px; width: 1px;"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" height="12"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                                <div class="clear" style="height: 12px; width: 1px;">&nbsp;</div>
                                            </td>
                                        </tr>
                                        <!-- /item -->

                                        <!-- starting balance -->
                                        <!-- /starting balance -->

                                        <!-- breakdown -->
                                        <tr>
                                            <td colspan="5" align="right"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; border-top: 2px solid #eaeff2;">
                                                <table border="0" cellpadding="0" cellspacing="0" class="width"
                                                       width="320">
                                                    <tbody>

                                                    <!-- subtotal -->
                                                    <!-- /subtotal -->

                                                    <!-- coupons -->
                                                    <!-- /coupons -->

                                                    <!-- applied coupons -->
                                                    <!-- /applied coupons -->

                                                    <!-- tax -->
                                                    <!-- /tax -->

                                                    <!-- total -->
                                                    <tr>
                                                        <td colspan="5" height="11"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                                            <div class="clear" style="height: 11px; width: 1px;">
                                                                &nbsp;
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr class="summary-item">
                                                        <td class="summary-padding"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                            width="1">
                                                            <div class="clear" style="height: 1px; width: 1px;"></div>
                                                        </td>
                                                        <td align="left" class="font-small"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; color: #77858c; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 12px; font-weight: bold; line-height: 17px;"
                                                            width="100">
                                                            Total
                                                        </td>
                                                        <td style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                            width="10">
                                                            <div class="clear" style="height: 1px; width: 10px;"></div>
                                                        </td>
                                                        <td align="right" class="font-medium width"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; color: #292e31; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 17px;"
                                                            width="208">
                                                        ${amountPaid!''} €
                                                        </td>
                                                        <td class="summary-padding"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                            width="1">
                                                            <div class="clear" style="height: 1px; width: 1px;"></div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="5" height="12"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly; border-bottom: 1px solid #eaeff2;">
                                                            <div class="clear" style="height: 12px; width: 1px;">
                                                                &nbsp;
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <!-- /total -->

                                                    <!-- applied balance -->
                                                    <!-- /applied balance -->

                                                    <!-- amount -->
                                                    <tr>
                                                        <td colspan="5" height="11"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                                            <div class="clear" style="height: 11px; width: 1px;">
                                                                &nbsp;
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr class="summary-item">
                                                        <td class="summary-padding"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                            width="1">
                                                            <div class="clear" style="height: 1px; width: 1px;"></div>
                                                        </td>
                                                        <td align="left"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; color: #292e31; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 12px; font-weight: bold; line-height: 17px;"
                                                            width="100">
                                                            Payé
                                                        </td>
                                                        <td style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                            width="10">
                                                            <div class="clear" style="height: 1px; width: 10px;"></div>
                                                        </td>
                                                        <td align="right" class="summary-total width"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; color: #292e31; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 16px; font-weight: bold; line-height: 17px;"
                                                            width="208">
                                                        ${amountPaid!''} €
                                                        </td>
                                                        <td class="summary-padding"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                                            width="1">
                                                            <div class="clear" style="height: 1px; width: 1px;"></div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="5" height="12"
                                                            style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                                            <div class="clear" style="height: 12px; width: 1px;">
                                                                &nbsp;
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <!-- /amount -->

                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        <!-- /breakdown -->

                                        </tbody>
                                    </table>
                                </td>
                                <td class="temp-padding"
                                    style="border: 0; margin: 0; padding: 0; mso-line-height-rule: exactly; font-size: 1px; line-height: 1px;">
                                    <div class="clear" style="height: 1px; width: 20px;"></div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
            <!-- /summary -->


            <!-- help -->
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                <tr>
                    <td align="center" style="border: 0; margin: 0; padding: 0;">
                        <table border="0" cellpadding="0" cellspacing="0" class="width" width="500">
                            <tbody>
                            <tr>
                                <td colspan="3" height="37"
                                    style="border: 0; margin: 0; padding: 0; mso-line-height-rule: exactly;">
                                    <div class="clear" style="height: 37px; width: 1px;">&nbsp;</div>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                    <div class="clear" style="height: 1px; width: 20px;"></div>
                                </td>

                                <td align="center" class="font-large"
                                    style="color: #515f66; border: 0; margin: 0; padding: 0; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 15px; line-height: 21px;">

                                    Vous avez une question ou avez besoin d'aide ? <a href="mailto:contact@qaobee.com"
                                                                                      style="border: 0; margin: 0; padding: 0; color: #515f66; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; text-decoration: none;"
                                                                                      target="_blank"
                                                                                      rel="noreferrer"><span
                                        style="border: 0; margin: 0; padding: 0; color: #008cdd; text-decoration: none;">Envoyez-nous un email</span></a>.
                                </td>

                                <td style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                    <div class="clear" style="height: 1px; width: 20px;"></div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" height="37"
                                    style="border: 0; margin: 0; padding: 0; mso-line-height-rule: exactly;">
                                    <div class="clear" style="height: 37px; width: 1px;">&nbsp;</div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center" height="1"
                                    style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                    <table align="center" border="0" cellpadding="0" cellspacing="0" width="200">
                                        <tbody>
                                        <tr>
                                            <td bgcolor="edeff0"
                                                style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                                <div class="clear" style="height: 1px; width: 200px;">&nbsp;</div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
            <!-- /help -->

            <!-- footer -->
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                <tr>
                    <td align="center" style="border: 0; margin: 0; padding: 0;">
                        <table border="0" cellpadding="0" cellspacing="0" class="width" width="500">
                            <tbody>
                            <tr>
                                <td colspan="3" height="28"
                                    style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                    <div class="clear" style="height: 28px; width: 1px;">&nbsp;</div>
                                </td>
                            </tr>


                            <tr>
                                <td class="perm-padding"
                                    style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                    width="20">
                                    <div class="clear" style="height: 1px; width: 20px;"></div>
                                </td>
                                <td align="center" class="font-small"
                                    style="border: 0; margin: 0; padding: 0; color: #959fa5; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 13px; line-height: 17px;">
                                    Vous recevez cet email car vous avez réalisé un paiement sur <a
                                        href="https://www.qaobee.com/"
                                        style="border: 0; margin: 0; padding: 0; color: #008cdd; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; text-decoration: none;"
                                        target="_blank" rel="noreferrer"><span
                                        style="border: 0; margin: 0; padding: 0; color: #008cdd; text-decoration: none;">Qaobee</span></a>.
                                </td>
                                <td class="perm-padding"
                                    style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px;"
                                    width="20">
                                    <div class="clear" style="height: 1px; width: 20px;"></div>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="3" height="28"
                                    style="border: 0; margin: 0; padding: 0; font-size: 1px; line-height: 1px; mso-line-height-rule: exactly;">
                                    <div class="clear" style="height: 28px; width: 1px;">&nbsp;</div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
            <!-- /footer -->

        </td>
    </tr>
    </tbody>
</table>
<!-- /background -->

</body>
</html>