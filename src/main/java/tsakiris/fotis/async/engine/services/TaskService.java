package tsakiris.fotis.async.engine.services;

import org.springframework.stereotype.Service;
import tsakiris.fotis.async.engine.domain.Task;

import static tsakiris.fotis.async.engine.common.Consts.JMS_DESTINATION;

@Service
public class TaskService extends AbstractService {

    public Task sendToJms(Task task) {
        getJmsTemplate().convertAndSend(JMS_DESTINATION, task);
        return task;
    }

}