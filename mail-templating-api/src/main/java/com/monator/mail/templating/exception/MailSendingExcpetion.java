package com.monator.mail.templating.exception;

public class MailSendingExcpetion extends RuntimeException {
    public MailSendingExcpetion() {
        super();
    }

    public MailSendingExcpetion(String message) {
        super(message);
    }

    public MailSendingExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public MailSendingExcpetion(Throwable cause) {
        super(cause);
    }

    protected MailSendingExcpetion(String message,
                                   Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
