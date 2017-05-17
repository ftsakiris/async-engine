package tsakiris.fotis.async.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Task {
    private final String id;
    private final List<KeyValue> headers;
    private final String protocol;
    private final String domain;
    private final String resourcePath;
    private final String port;
    private final Method method;
    private final List<KeyValue> queryParams;
    private final String body;
}
