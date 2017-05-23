package tsakiris.fotis.async.engine.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import tsakiris.fotis.async.engine.domain.AbstractTask;

import java.io.Serializable;

import static tsakiris.fotis.async.engine.common.CommonUtils.generateUUID;

public interface ITaskRepository<T extends AbstractTask, ID extends Serializable> extends MongoRepository<T, ID> {
    default void saveTask(T task) {
        task.setId(generateUUID());
        save(task);
    }
}
