package tsakiris.fotis.async.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Task {
    private final String headers;
    private final String protocol;
    private final String domain;
    private final String resourcePath;
    private final String port;
}
