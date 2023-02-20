package aquality.selenium.template.models.attachments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseUploadPhoto {
    @JsonProperty(value = "markers_restarted")
    private boolean markers_restarted;
    @JsonProperty(value = "photo")
    private String photo;
    @JsonProperty(value = "sizes")
    private ArrayList<Object> sizes;
    @JsonProperty(value = "latitude")
    private int latitude;
    @JsonProperty(value = "longitude")
    private int longitude;
    @JsonProperty(value = "kid")
    private String kid;
    @JsonProperty(value = "sizes2")
    private ArrayList<ArrayList<Object>> sizes2;
    @JsonProperty(value = "urls")
    private ArrayList<Object> urls;
    @JsonProperty(value = "urls2")
    private ArrayList<String> urls2;
}
