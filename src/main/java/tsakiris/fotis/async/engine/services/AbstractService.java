package tsakiris.fotis.async.engine.services;

import org.springframework.jms.core.JmsTemplate;

import static tsakiris.fotis.async.engine.Application.configurableApplicationContext;

public abstract class AbstractService {

    protected JmsTemplate getJmsTemplate() {
        return configurableApplicationContext.getBean(JmsTemplate.class);
    }

}
