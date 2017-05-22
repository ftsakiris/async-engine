package tsakiris.fotis.async.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Document
public class ScheduledTask {
    @Id
    private final String id;
    private final String taskId;
    private final String description;
}
