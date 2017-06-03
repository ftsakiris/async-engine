package tsakiris.fotis.async.engine.domain;

import lombok.Getter;

@Getter
public abstract class AbstractTask extends Entity {

    private final String description;

    public AbstractTask(String id, String description) {
        super(id);
        this.description = description;
    }

    public AbstractTask() {
        this(null, null);
    }
}
