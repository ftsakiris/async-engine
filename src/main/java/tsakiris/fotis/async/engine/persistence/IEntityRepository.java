package tsakiris.fotis.async.engine.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import tsakiris.fotis.async.engine.domain.Entity;

import java.io.Serializable;

import static tsakiris.fotis.async.engine.common.CommonUtils.generateUUID;

public interface IEntityRepository<T extends Entity, ID extends Serializable> extends MongoRepository<T, ID> {
    default void saveEntity(T task) {
        task.setId(generateUUID());
        save(task);
    }
}
