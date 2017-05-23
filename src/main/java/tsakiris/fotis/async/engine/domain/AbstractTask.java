package tsakiris.fotis.async.engine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractTask {

    @Id
    @JsonIgnore
    private String id;
    private String description;
}
