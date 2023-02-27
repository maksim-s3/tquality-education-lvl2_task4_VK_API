package aquality.selenium.template.models.wall;

import aquality.selenium.template.models.attachments.Attachment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NestedSavedWallPhoto extends Attachment {
    @JsonProperty(value = "album_id")
    private int albumId;
    private int date;
    private int id;
    @JsonProperty(value = "owner_id")
    private int ownerId;
    @JsonProperty(value = "access_key")
    private String accessKey;
    private ArrayList<NestedSizeSavedPhoto> sizes;
    private String text;
    @JsonProperty(value = "has_tags")
    private boolean hasTags;
}
