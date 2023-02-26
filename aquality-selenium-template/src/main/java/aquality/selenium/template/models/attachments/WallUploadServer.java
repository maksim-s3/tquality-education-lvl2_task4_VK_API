package aquality.selenium.template.models.attachments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WallUploadServer {
    @JsonProperty(value = "response")
    private NestedWallUploadServer nestedWallUploadServer;
}
