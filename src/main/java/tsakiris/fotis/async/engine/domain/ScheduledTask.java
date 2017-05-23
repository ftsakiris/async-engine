package tsakiris.fotis.async.engine.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
public class ScheduledTask extends AbstractTask {

    private final String taskId;
    private final String description;
    private final String cron;

    public ScheduledTask() {
        super();
        this.taskId = null;
        this.description = null;
        this.cron = null;
    }

    public ScheduledTask(String id, String taskId, String description, String cron) {
        super(id);
        this.taskId = taskId;
        this.description = description;
        this.cron = cron;
    }

    public ScheduledTask(String taskId, String description, String cron) {
        super();
        this.taskId = taskId;
        this.description = description;
        this.cron = cron;
    }
}
