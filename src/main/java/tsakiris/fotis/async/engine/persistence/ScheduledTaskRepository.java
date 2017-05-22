package tsakiris.fotis.async.engine.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tsakiris.fotis.async.engine.domain.ScheduledTask;

@RepositoryRestResource(exported = false)
public interface ScheduledTaskRepository extends MongoRepository<ScheduledTask, String> {
}
