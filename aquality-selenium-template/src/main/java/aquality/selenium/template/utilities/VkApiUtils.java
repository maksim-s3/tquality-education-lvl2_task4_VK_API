package aquality.selenium.template.utilities;

import aquality.selenium.template.models.attachments.Attachments;
import aquality.selenium.template.models.attachments.ResponseArraySavedPhoto;
import aquality.selenium.template.models.attachments.SavedPhoto;
import aquality.selenium.template.models.attachments.UploadedPhoto;
import aquality.selenium.template.models.post.PostLikes;
import aquality.selenium.template.models.post.PostComments;
import io.restassured.http.ContentType;
import java.io.File;
import static aquality.selenium.template.rest_assured.RestClient.getBaseSpec;
import static io.restassured.RestAssured.given;

public class VkApiUtils {
    private static final String CREATE_POST = "wall.post?access_token=%s&message=%s&v=%s";
    private static final String DELETE_POST = "wall.delete?access_token=%s&owner_id=%d&post_id=%d&v=%s";
    private static final String GET_URL_UPLOAD_SERVER = "photos.getWallUploadServer?access_token=%s&v=%s";
    private static final String SAVE_UPLOADED_PHOTO = "photos.saveWallPhoto?access_token=%s&server=%d&hash=%s&v=%s";
    private static final String EDIT_POST_WALL = "wall.edit?access_token=%s&owner_id=%d&post_id=%d&message=%s&attachments=%s%d_%d&v=%s";
    private static final String CREATE_COMMENT_FOR_POST_IN_WALL = "wall.createComment?access_token=%s&owner_id=%d&post_id=%d&message=%s&v=%s";
    private static final String GET_LIKES_POST = "wall.getLikes?access_token=%s&owner_id=%d&post_id=%d&v=%s";
    private static String versionApi;
    private static String token;

    public static void setToken(String text){
         token = text;
    }

    public static void setVersionApi(String text){
        versionApi = text;
    }

    public static int createPost(String message) {
        return given()
                .spec(getBaseSpec())
                .get(String.format(CREATE_POST, token, message, versionApi))
                .then()
                .extract().body().path("response.post_id");
    }

    private static String getUploadedServerForUploadImageToWall(){
        return given()
                .spec(getBaseSpec())
                .get(String.format(GET_URL_UPLOAD_SERVER, token, versionApi))
                .then()
                .extract().body().path("response.upload_url");
    }

    public static UploadedPhoto uploadPhoto(File file){
        return given()
                    .contentType(ContentType.MULTIPART)
                    .multiPart("photo", file)
                    .post(getUploadedServerForUploadImageToWall())
                .then()
                    .extract().body().jsonPath().getObject("", UploadedPhoto.class);
    }

    public static ResponseArraySavedPhoto saveWallPhoto(UploadedPhoto uploadedPhoto){
        return given()
                .spec(getBaseSpec())
                .queryParam("photo", uploadedPhoto.getPhoto())
                .post(String.format(SAVE_UPLOADED_PHOTO, token, uploadedPhoto.getServer(), uploadedPhoto.getHash(), versionApi))
                .then()
                .extract().body().jsonPath().getObject("", ResponseArraySavedPhoto.class);
    }

    public static void editPost(int postId, int ownerId, String message, SavedPhoto savedPhoto){
        given()
                .spec(getBaseSpec())
                .get(String.format(EDIT_POST_WALL, token, ownerId, postId, message, Attachments.PHOTO, ownerId, savedPhoto.getId(), versionApi))
                .then()
                .extract().body().path("response.post_id");
    }

    public static PostComments createComment(int postId, int ownerId, String message){
        return given()
                .spec(getBaseSpec())
                .get(String.format(CREATE_COMMENT_FOR_POST_IN_WALL, token, ownerId, postId, message, versionApi))
                .then()
                .extract().body().jsonPath().getObject("response", PostComments.class);
    }

    public static PostLikes getLikesPost(int ownerId, int postId){
        return given()
                .spec(getBaseSpec())
                .get(String.format(GET_LIKES_POST, token, ownerId, postId, versionApi))
                .then()
                .extract().body().jsonPath().getObject("response", PostLikes.class);
    }

    public static void deletePost(int ownerId, int postId) {
        given()
                .spec(getBaseSpec())
                .get(String.format(DELETE_POST, token, ownerId, postId, versionApi))
                .then()
                .extract().body().path("response");
    }
}
