package aquality.selenium.template.forms.navigation;

public enum ItemsMenuSideBar {
    MY_PAGE("l_pr");

    private final String title;

    ItemsMenuSideBar(String title) {
        this.title = title;
    }

    public String toString(){
        return title;
    }
}
