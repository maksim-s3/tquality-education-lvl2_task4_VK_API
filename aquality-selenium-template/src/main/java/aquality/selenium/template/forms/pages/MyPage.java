package aquality.selenium.template.forms.pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyPage extends Form {
    public MyPage() {
        super(By.id("profile_redesigned"), "my profile");
    }
}
