package com.monator.mail.templating.template.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.monator.mail.templating.MailService;
import com.monator.mail.templating.model.Mail;
import com.monator.mail.templating.model.MailContact;
import com.monator.mail.templating.template.MailTemplate;
import com.monator.mail.templating.exception.NoSuchMailTemplateException;
import com.monator.mail.templating.template.impl.util.InternalMailSender;
import org.osgi.service.component.annotations.*;

import java.util.*;

@Component(
        immediate = true,
        service = MailService.class
)
public class MailServiceImpl implements MailService {

    private Map<String, MailTemplate> mailTemplates;

    public MailServiceImpl() {
        mailTemplates = new HashMap<>();
    }

    @Reference(
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            service = MailTemplate.class,
            target = "(mail.template.key=*)"
    )
    public void addMailTemplate(MailTemplate instance, Map properties) {
        String templateKey = (String) properties.get("mail.template.key");

        if (templateKey == null || templateKey.isEmpty()) {
            log.warn("Trying to register mail template without key");
            return;
        }

        log.debug(String.format(
                "Registering mail template with key \"%s\".", templateKey));

        mailTemplates.put(templateKey, instance);
    }

    public void removeMailTemplate(MailTemplate template, Map properties) {
        String templateKey = (String) properties.get("mail.template.key");

        if (templateKey == null || templateKey.isEmpty()) {
            log.warn("Trying to unregister mail template without key");
            return;
        }

        log.debug(String.format(
                "Unregistering mail template with key \"%s\".", templateKey));

        MailTemplate removedTemplate = mailTemplates.remove(templateKey);
    }

    @Override
    public void send(Mail mail) {
        String templateKey = mail.getTemplateKey();

        MailTemplate mailTemplate = mailTemplates.get(templateKey);

        if (mailTemplate == null) {
            throw new NoSuchMailTemplateException(templateKey);
        }

        MailContact sender = mail.getSender();
        MailContact recipient = mail.getRecipient();
        String subject = mail.getSubject();

        Map<String, Object> data = mail.getAttributes();
        String body = mailTemplate.render(data);

        internalMailSender.sendEmail(recipient, sender, subject, body);
    }

    @Override
    public List<String> getTemplateList() {
        Set<String> keys = mailTemplates.keySet();

        return new ArrayList<>(keys);
    }

    @Reference
    private InternalMailSender internalMailSender;

    private static final Log log =
            LogFactoryUtil.getLog(MailServiceImpl.class);

}
