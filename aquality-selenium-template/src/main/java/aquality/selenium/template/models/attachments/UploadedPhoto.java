package aquality.selenium.template.models.attachments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UploadedPhoto {
    private int server;
    private String photo;
    private String hash;
}
