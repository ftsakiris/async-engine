package tsakiris.fotis.async.engine.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
public class ScheduledTask extends AbstractTask {

    private final String taskId;
    private final String cron;

    public ScheduledTask() {
        this(null, null, null, null);
    }

    public ScheduledTask(String id, String description, String taskId, String cron) {
        super(id, description);
        this.taskId = taskId;
        this.cron = cron;
    }

    public ScheduledTask(String description, String taskId, String cron) {
        this(null, description, taskId, cron);
    }
}
