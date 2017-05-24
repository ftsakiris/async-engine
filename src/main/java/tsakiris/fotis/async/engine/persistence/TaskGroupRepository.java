package tsakiris.fotis.async.engine.persistence;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tsakiris.fotis.async.engine.domain.TaskGroup;

@RepositoryRestResource(exported = false)
public interface TaskGroupRepository extends IEntityRepository<TaskGroup, String> {
}
