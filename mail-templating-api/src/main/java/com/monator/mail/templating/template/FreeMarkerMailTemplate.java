package com.monator.mail.templating.template;

import com.monator.mail.templating.exception.MailSendingExcpetion;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public abstract class FreeMarkerMailTemplate implements MailTemplate {
    private Configuration cfg;

    public FreeMarkerMailTemplate() {
        cfg = new Configuration(Configuration.getVersion());

        cfg.setClassForTemplateLoading(
                this.getClass(), "/META-INF/resources");

        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(
                TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
    }

    protected abstract String getTemplatePath();

    @Override
    public String render() {
        return render(new HashMap<>());
    }

    @Override
    public String render(Map<String, Object> data) {
        String templatePath = getTemplatePath();

        Writer out = new StringWriter();
        try {
            Template temp = cfg.getTemplate(templatePath);

            temp.process(data, out);
        } catch (IOException | TemplateException e) {
            throw new MailSendingExcpetion(e);
        }

        return out.toString();
    }
}
