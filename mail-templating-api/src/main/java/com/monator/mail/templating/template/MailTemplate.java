package com.monator.mail.templating.template;

import java.util.Map;

/**
 * @author martlin
 */
public interface MailTemplate {
    String render();

    default String render(Map<String, Object> data) {
        return render();
    }
}
