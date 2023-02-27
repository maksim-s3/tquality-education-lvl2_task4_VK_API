package aquality.selenium.template.forms.navigation;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SideBar extends Form {
    private String templateBtmMenu = "//*[@id='%s']//*[contains(@class, 'itemLabel')]";
    public SideBar() {
        super(By.id("side_bar"), "left menu");
    }

    public void clickItemMenu(ItemsMenuSideBar item){
        IButton btnMenu = getElementFactory().getButton(By.xpath(String.format(templateBtmMenu, item)), "item '"+item+"' left menu");
        btnMenu.click();
    }
}
