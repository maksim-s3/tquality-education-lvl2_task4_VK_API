package aquality.selenium.template.models.wall;

import com.fasterxml.jackson.annotation.JsonProperty;
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestedWallPost {
    @JsonProperty(value = "post_id")
    private int postId;
}
