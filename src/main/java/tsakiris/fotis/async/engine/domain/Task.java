package tsakiris.fotis.async.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Getter
@Document
public class Task {

    @Id
    private final String id;
    private final String description;
    private final List<KeyValue> headers;
    private final String protocol;
    private final String domain;
    private final String resourcePath;
    private final String port;
    private final Method method;
    private final List<KeyValue> queryParams;
    private final String body;
}
