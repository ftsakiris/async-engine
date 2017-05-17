package tsakiris.fotis.async.engine.services;

import org.springframework.stereotype.Service;
import tsakiris.fotis.async.engine.domain.ScheduledTask;

@Service
public class ScheduledTaskService extends AbstractService {

    public ScheduledTask create(ScheduledTask task) {

        return task;
    }

}