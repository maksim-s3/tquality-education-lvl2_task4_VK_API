package aquality.selenium.template.models.attachments;


public enum AttachmentTypes {
    PHOTO("photo");

    private final String title;

    AttachmentTypes(String title) {
        this.title = title;
    }

    public String toString(){
        return title;
    }

}
