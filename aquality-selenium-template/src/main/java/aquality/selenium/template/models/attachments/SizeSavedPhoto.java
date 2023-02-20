package aquality.selenium.template.models.attachments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeSavedPhoto {
    private int height;
    private String type;
    private int width;
    private String url;
}
