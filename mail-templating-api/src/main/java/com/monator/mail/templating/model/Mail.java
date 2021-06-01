package com.monator.mail.templating.model;

import java.util.*;

public class Mail {
    private Map<String, Object> templateAttributes;
    private final String templateKey;

    private MailContact sender;
    private MailContact recipient;
    private String subject;

    public Mail(String templateKey) {
        templateAttributes = new HashMap<>();

        this.templateKey = templateKey;
    }

    public String getTemplateKey() {
        return templateKey;
    }

    public MailContact getSender() {
        return sender;
    }

    public void setSender(MailContact sender) {
        this.sender = sender;
    }

    public MailContact getRecipient() {
        return recipient;
    }

    public void setRecipient(MailContact recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAttribute(String name, Object o) {
        templateAttributes.put(name, o);
    }

    public Map<String, Object> getAttributes() {
        return templateAttributes;
    }

    public List<String> getAttributeNames() {
        return new ArrayList<>(templateAttributes.keySet());
    }

    public Object getAttribute(String name) {
        return templateAttributes.get(name);
    }
}
