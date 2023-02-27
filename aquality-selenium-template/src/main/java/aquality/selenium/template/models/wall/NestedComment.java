package aquality.selenium.template.models.wall;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestedComment {
    @JsonProperty(value = "comment_id")
    private int commentId;
    @JsonProperty(value = "parents_stack")
    private List<Integer> parentsStack;
}
