package tsakiris.fotis.async.engine.persistence;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tsakiris.fotis.async.engine.domain.TaskResult;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface TaskResultRepository extends IEntityRepository<TaskResult, String> {
    List<TaskResult> findByTaskId(String taskId);
}
