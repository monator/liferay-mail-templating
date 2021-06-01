package com.monator.mail.templating.template.impl.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.monator.mail.templating.model.MailContact;
import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

@Component(
        service = InternalMailSender.class
)
public class InternalMailSenderImpl implements InternalMailSender {
    public void sendEmail(MailContact sender,
                          MailContact recipient,
                          String subject,
                          String body) {
        this.sendEmail(sender,
                Collections.singletonList(recipient),
                subject,
                body);
    }

    public void sendEmail(MailContact sender,
                          List<MailContact> recipients,
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

            int length = recipients.size();
            InternetAddress[] recipientAddresses = new InternetAddress[length];

            for (int i = 0; i < length; i++) {
                MailContact recipient = recipients.get(i);
                recipientAddresses[i] =
                        new InternetAddress(
                                recipient.getEmail(),
                                recipient.getName());
            }

            mailMessage.setFrom(senderAddress);
            mailMessage.setTo(recipientAddresses);
        } catch (UnsupportedEncodingException e) {
            return;
        }

        MailServiceUtil.sendEmail(mailMessage);
    }
}
