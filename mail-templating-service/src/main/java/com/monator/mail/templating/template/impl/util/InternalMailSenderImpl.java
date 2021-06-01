package com.monator.mail.templating.template.impl.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.monator.mail.templating.model.MailContact;
import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Component(
        service = InternalMailSender.class
)
public class InternalMailSenderImpl implements InternalMailSender {
    public void sendEmail(MailContact recipient,
                          MailContact sender,
                          String subject,
                          String body) {
        MailMessage mailMessage = new MailMessage();

        mailMessage.setHTMLFormat(true);
        mailMessage.setSubject(subject);
        mailMessage.setBody(body);

        try {
            InternetAddress senderAddress =
                    new InternetAddress(
                            sender.getEmail(),
                            sender.getName());

            InternetAddress recipientAddress =
                    new InternetAddress(
                            recipient.getEmail(),
                            recipient.getName());

            mailMessage.setFrom(senderAddress);
            mailMessage.setTo(recipientAddress);
        } catch (UnsupportedEncodingException e) {
            return;
        }

        MailServiceUtil.sendEmail(mailMessage);

    }
}
