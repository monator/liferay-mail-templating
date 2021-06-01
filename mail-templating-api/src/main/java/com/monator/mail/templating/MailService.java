package com.monator.mail.templating;

import com.monator.mail.templating.model.Mail;

import java.util.List;

public interface MailService {
    void send(Mail mail);

    List<String> getTemplateList();
}
