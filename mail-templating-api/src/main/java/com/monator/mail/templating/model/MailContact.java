package com.monator.mail.templating.model;

public class MailContact {
    private String name;
    private String email;

    public MailContact(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
