package aquality.selenium.template.models.wall;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestedGetLikes {
    private int count;
    private List<UserPutLikeOnPost> users;
}
