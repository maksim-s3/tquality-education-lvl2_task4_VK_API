package aquality.selenium.template.forms.pages;

public enum HtmlAttributes {
    STYLE("style");

    private final String title;

    HtmlAttributes(String title) {
        this.title = title;
    }

    public String toString(){
        return title;
    }
}
