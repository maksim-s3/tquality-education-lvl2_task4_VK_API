package aquality.selenium.template.rest_assured;

public enum RequestParams {
    ACCESS_TOKEN("access_token"),
    VERSION_API("v"),
    OWNER_ID("owner_id"),
    POST_ID("post_id"),
    MESSAGE("message"),
    ATTACHMENTS("attachments"),
    SERVER("server"),
    HASH("hash"),
    PHOTO("photo");

    private final String title;

    RequestParams(String title) {
        this.title = title;
    }

    public String toString(){
        return title;
    }
}
