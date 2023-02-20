package aquality.selenium.template.forms.navigation;

public enum ItemsMenuSideBar {
    MY_PAGE("Моя страница");

    private final String title;

    ItemsMenuSideBar(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
