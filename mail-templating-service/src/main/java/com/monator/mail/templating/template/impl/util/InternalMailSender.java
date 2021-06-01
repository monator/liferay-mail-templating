package com.monator.mail.templating.template.impl.util;

import com.monator.mail.templating.model.MailContact;

public interface InternalMailSender {
    void sendEmail(MailContact recipient, MailContact sender,
                   String subject, String body);
}
