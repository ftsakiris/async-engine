package tsakiris.fotis.async.engine.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Document
public class Task extends AbstractTask {

    private final String taskGroupId;
    private final List<KeyValue> headers;
    private final String protocol;
    private final String domain;
    private final String resourcePath;
    private final String port;
    private final Method method;
    private final List<KeyValue> queryParams;
    private final String body;

    public Task(String id, String description, String taskGroupId, List<KeyValue> headers, String protocol, String domain, String resourcePath, String port, Method method, List<KeyValue> queryParams, String body) {
        super(id, description);
        this.taskGroupId = taskGroupId;
        this.headers = headers;
        this.protocol = protocol;
        this.domain = domain;
        this.resourcePath = resourcePath;
        this.port = port;
        this.method = method;
        this.queryParams = queryParams;
        this.body = body;
    }

    public Task() {
        this(null, null, null, null, null, null, null, null, null, null, null);
    }

    public Task(String description, String taskGroupId, List<KeyValue> headers, String protocol, String domain, String resourcePath, String port, Method method, List<KeyValue> queryParams, String body) {
        this(null, description, taskGroupId, headers, protocol, domain, resourcePath, port, method, queryParams, body);
    }
}
