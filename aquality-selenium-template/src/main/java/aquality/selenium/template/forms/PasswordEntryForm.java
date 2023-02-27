package aquality.selenium.template.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordEntryForm extends Form {
    private ITextBox tbxPassword = getElementFactory().getTextBox(By.xpath("//*[contains(@name, 'password')]"), "password");
    private IButton btnContinue = getElementFactory().getButton(By.xpath("//*[contains(@class, 'vkuiButton__in')]"), "continue");
    private IButton btnSwitchToPassword = getElementFactory().getButton(By.xpath("//*[contains(@class, 'switchToPassword')]//*"), "switch to log in by password");

    public PasswordEntryForm() {
        super(By.xpath("//*[contains(@class, 'vkc__EnterPasswordNoUserInfo__content')]"), "password entry field");
    }

    public void clickSwitchToPassword() {
        btnSwitchToPassword.state().waitForClickable();
        btnSwitchToPassword.click();
    }

    public void setPassword(String password) {
        tbxPassword.state().waitForDisplayed();
        tbxPassword.clearAndType(password);
    }

    public void clickContinue() {
        btnContinue.click();
    }
}
