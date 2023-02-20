package aquality.selenium.template.forms;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginEntryForm extends Form {
    private ITextBox tbxLogin = getElementFactory().getTextBox(By.id("index_email"), "input field login");
    private final IButton btnSignIn = getElementFactory().getButton(By.xpath("//*[contains(@class, 'VkIdForm__signInButton')]"), "sign in button");
    public LoginEntryForm() {
        super(By.id("index_login"), "Authorization page");
    }

    public void setLogin(String login){
        tbxLogin.clearAndType(login);
    }

    public void clickSignInButton(){
        btnSignIn.click();
    }
}
