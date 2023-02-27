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
public class SavedWallPhoto {
    @JsonProperty(value = "response")
    private List<NestedSavedWallPhoto> nestedSavedWallPhotos;
}
