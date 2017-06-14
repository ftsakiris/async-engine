package tsakiris.fotis.async.engine.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import tsakiris.fotis.async.engine.json.JsonDateSerializer;

import java.util.Date;

@Getter
@Document
public class TaskResult extends Entity {
    private final String taskId;
    private final Date dateTime;
    private final String taskResult;

    public TaskResult(String code, String taskId, Date dateTime, String taskResult) {
        super(code);
        this.taskId = taskId;
        this.dateTime = dateTime;
        this.taskResult = taskResult;
    }

    public TaskResult() {
        this(null, null, null);
    }

    public TaskResult(String taskId, Date dateTime, String taskResult) {
        this(null, taskId, dateTime, taskResult);
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getDateTime() {
        return dateTime;
    }
}
