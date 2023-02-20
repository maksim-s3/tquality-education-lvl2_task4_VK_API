package aquality.selenium.template.models.attachments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavedPhoto {
    private int album_id;
    private int date;
    private int id;
    private int owner_id;
    private String access_key;
    private ArrayList<SizeSavedPhoto> sizes;
    private String text;
    private boolean has_tags;
}
