package com.qaobee.hive.technical.exceptions;

import io.vertx.serviceproxy.ServiceException;

/**
 * The type Qaobee svc exception.
 */
public class QaobeeSvcException extends ServiceException {

    private static final long serialVersionUID = 3702929307917485614L;
    private final ExceptionCodes code; // NOSONAR
    private String jsonContext = ""; // NOSONAR
    private final long timestamp = System.currentTimeMillis(); // NOSONAR
    private boolean report; // NOSONAR

    /**
     * Instantiates a new qaobee exception.
     *
     * @param code    the code
     * @param message the message
     */
    public QaobeeSvcException(final ExceptionCodes code, final String message) {
        super(code.getCode(), message);
        this.code = code;
    }

    /**
     * Instantiates a new Qaobee svc exception.
     *
     * @param ex the ex
     */
    public QaobeeSvcException(QaobeeException ex) {
        super(ex.getCode().getCode(), ex.getMessage());
        this.jsonContext =  ex.getJsonContext();
        this.code =  ex.getCode();
        this.report =  ex.isReport();
    }

    /**
     * Instantiates a new Qaobee svc exception.
     *
     * @param code  the code
     * @param cause the cause
     */
    public QaobeeSvcException(ExceptionCodes code, Throwable cause) {
        super(code.getCode(), cause.getMessage());
        this.code = code;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public ExceptionCodes getCode() {
        return code;
    }

    /**
     * Gets the json context.
     *
     * @return the json context
     */
    public String getJsonContext() {
        return jsonContext;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Checks if is the report.
     *
     * @return the report
     */
    public boolean isReport() {
        return report;
    }
}
