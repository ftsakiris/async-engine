package tsakiris.fotis.async.engine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsakiris.fotis.async.engine.domain.Task;
import tsakiris.fotis.async.engine.persistence.TaskRepository;

import static tsakiris.fotis.async.engine.common.Consts.JMS_DESTINATION;

@Service
public class TaskService extends AbstractService {

    @Autowired
    private TaskRepository taskRepository;

    public Task create(Task task) {
        taskRepository.saveEntity(task);
        run(task);
        return task;
    }

    public Task get(String id) {
        return taskRepository.findOne(id);
    }

    public Task run(Task task) {
        getJmsTemplate().convertAndSend(JMS_DESTINATION, task);
        return task;
    }

}