package aquality.selenium.template.models.attachments;


public enum Attachments {
    PHOTO("photo");

    private final String title;

    Attachments(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public String toString(){
        return title;
    }

}
