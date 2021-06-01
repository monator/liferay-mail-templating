package com.monator.mail.templating.exception;

public class NoSuchMailTemplateException extends RuntimeException {
    public NoSuchMailTemplateException() {
        super();
    }

    public NoSuchMailTemplateException(String message) {
        super(message);
    }

    public NoSuchMailTemplateException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchMailTemplateException(Throwable cause) {
        super(cause);
    }

    protected NoSuchMailTemplateException(String message,
                                          Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
