package tsakiris.fotis.async.engine.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
public class ScheduledTask extends AbstractTask {

    private final String taskGroupId;
    private final String taskId;
    private final String cron;

    public ScheduledTask(String id, String description, String taskGroupId, String taskId, String cron) {
        super(id, description);
        this.taskGroupId = taskGroupId;
        this.taskId = taskId;
        this.cron = cron;
    }

    public ScheduledTask() {
        this(null, null, null, null);
    }

    public ScheduledTask(String description, String taskGroupId, String taskId, String cron) {
        this(null, description, taskGroupId, taskId, cron);
    }
}
