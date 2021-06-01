package com.monator.mail.templating.model;

import java.util.*;

public class Mail {
    private final String templateKey;
    private Map<String, Object> templateAttributes;

    private MailContact sender;
    private Set<MailContact> recipients;
    private String subject;

    public Mail(String templateKey) {
        templateAttributes = new HashMap<>();
        recipients = new HashSet<>();

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

    public List<MailContact> getRecipients() {
        return new ArrayList<>(recipients);
    }

    public void setRecipient(MailContact recipient) {
        this.recipients.add(recipient);
    }

    public void setRecipients(List<MailContact> recipients) {
        this.recipients.clear();
        this.recipients.addAll(recipients);
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
