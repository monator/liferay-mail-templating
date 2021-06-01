package com.monator.mail.templating.template.impl.util;

import com.monator.mail.templating.model.MailContact;

import java.util.List;

public interface InternalMailSender {
    void sendEmail(MailContact sender, MailContact recipient,
                   String subject, String body);

    void sendEmail(MailContact sender,
                   List<MailContact> recipient,
                   String subject, String body);
}
