package aquality.selenium.template.forms.navigation;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import aquality.selenium.template.forms.navigation.ItemsMenuSideBar;
import org.openqa.selenium.By;

public class SideBar extends Form {
    private String templateBtmMenu = "//*[contains(text(), 'Моя страница')]";
    public SideBar() {
        super(By.id("side_bar"), "menu side bar");
    }

    public void clickItemMenu(ItemsMenuSideBar item){
        IButton btnMenu = getElementFactory().getButton(By.xpath(String.format(templateBtmMenu, item.getTitle())), "item '"+item.getTitle()+"' menu side bar");
        btnMenu.click();
    }
}
