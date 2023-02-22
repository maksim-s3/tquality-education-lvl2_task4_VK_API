package aquality.selenium.template.models.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostComments {
    @JsonProperty(value = "comment_id")
    private int commentId;
    @JsonProperty(value = "parents_stack")
    private int[] parentsStack;
}
