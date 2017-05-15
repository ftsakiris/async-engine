package tsakiris.fotis.async.engine.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import tsakiris.fotis.async.engine.domain.Task;

import static tsakiris.fotis.async.engine.common.Consts.ASYNC_ENGINE_FACTORY;
import static tsakiris.fotis.async.engine.common.Consts.JMS_DESTINATION;

@Component
public class TaskReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskReceiver.class);

    @Autowired
    private HttpRequestService httpRequestService;

    @JmsListener(destination = JMS_DESTINATION, containerFactory = ASYNC_ENGINE_FACTORY)
    public void receiveMessage(Task task) {
        LOGGER.info("Received <" + task.getResourcePath() + ">");
        LOGGER.info(httpRequestService.startRequest(task));
    }

}
