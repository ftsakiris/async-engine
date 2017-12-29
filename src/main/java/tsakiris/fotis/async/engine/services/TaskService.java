package tsakiris.fotis.async.engine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsakiris.fotis.async.engine.common.CommonUtils;
import tsakiris.fotis.async.engine.domain.Task;
import tsakiris.fotis.async.engine.persistence.TaskRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static tsakiris.fotis.async.engine.common.Consts.JMS_DESTINATION;

@Service
public class TaskService extends AbstractService {

    @Autowired
    private TaskRepository taskRepository;

    public Task create(Task task) {
        taskRepository.saveEntity(task);
        return run(task);
    }

    public Task get(String id) {
        return taskRepository.findOne(id);
    }

    public Task run(Task task) {
        if (CommonUtils.isEmpty(task)) {
            return null;
        }
        getJmsTemplate().convertAndSend(JMS_DESTINATION, task);
        return task;
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Set<String> findByTaskGroupId(String value) {
        Set<String> taskIds = new HashSet<>();
        if (CommonUtils.isEmpty(value)) {
            return taskIds;
        }
        taskRepository.findByTaskGroupId(value).forEach(task -> taskIds.add(task.getId()));
        return taskIds;
    }
}