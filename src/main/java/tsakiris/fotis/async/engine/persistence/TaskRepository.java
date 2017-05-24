package tsakiris.fotis.async.engine.persistence;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tsakiris.fotis.async.engine.domain.Task;

@RepositoryRestResource(exported = false)
public interface TaskRepository extends IEntityRepository<Task, String> {
}
