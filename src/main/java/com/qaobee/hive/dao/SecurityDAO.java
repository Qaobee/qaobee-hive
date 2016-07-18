package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonObject;

/**
 * Created by b3605 on 18/07/16.
 *
 * @author Xavier MARIN (b3605)
 */
public interface SecurityDAO {
    /**
     * Login by token json object.
     *
     * @param login       the login
     * @param mobileToken the mobile token
     * @param locale      the locale
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject loginByToken(String login, String mobileToken, String locale) throws QaobeeException;

    /**
     * Password reset boolean.
     *
     * @param id                   the id
     * @param code                 the code
     * @param passwd               the passwd
     * @param byPassActivationCode the by pass activation code
     * @return the boolean
     * @throws QaobeeException the qaobee exception
     */
    boolean passwordReset(String id, String code, String passwd, boolean byPassActivationCode) throws QaobeeException;

    /**
     * Password renew check json object.
     *
     * @param id   the id
     * @param code the code
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject passwordRenewCheck(String id, String code) throws QaobeeException;

    /**
     * Password renew boolean.
     *
     * @param login  the login
     * @param locale the locale
     * @return the boolean
     * @throws QaobeeException the qaobee exception
     */
    boolean passwordRenew(String login, String locale) throws QaobeeException;

    /**
     * Logout boolean.
     *
     * @param token the token
     * @return the boolean
     * @throws QaobeeException the qaobee exception
     */
    boolean logout(String token) throws QaobeeException;

    /**
     * Login json object.
     *
     * @param login       the login
     * @param password    the password
     * @param mobileToken the mobile token
     * @param locale      the locale
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject login(String login, String password, String mobileToken, String locale) throws QaobeeException;

}
