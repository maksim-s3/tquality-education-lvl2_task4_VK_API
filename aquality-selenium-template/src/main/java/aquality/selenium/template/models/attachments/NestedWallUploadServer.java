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
public class NestedWallUploadServer {
    @JsonProperty(value = "album_id")
    private int albumId;
    @JsonProperty(value = "upload_url")
    private String uploadUrl;
    @JsonProperty(value = "user_id")
    private int userId;
}
