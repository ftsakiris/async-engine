package tsakiris.fotis.async.engine.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
public class TaskResult extends Entity {
    private final String taskId;
    private final String taskResult;

    public TaskResult(String code, String taskId, String taskResult) {
        super(code);
        this.taskId = taskId;
        this.taskResult = taskResult;
    }

    public TaskResult() {
        this(null, null);
    }

    public TaskResult(String taskId, String taskResult) {
        this(null, taskId, taskResult);
    }
}
