package tsakiris.fotis.async.engine.domain;

import lombok.Getter;

@Getter
public abstract class AbstractTask extends Entity {

    private final String description;
    private final String taskGroupId;

    public AbstractTask(String id, String description, String taskGroupId) {
        super(id);
        this.description = description;
        this.taskGroupId = taskGroupId;
    }

    public AbstractTask() {
        this(null, null, null);
    }
}
