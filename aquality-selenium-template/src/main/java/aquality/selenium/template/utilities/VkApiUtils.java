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
    private static final String CREATE_POST = "wall.post?message=%s&";
    private static final String DELETE_POST = "wall.delete?owner_id=%d&post_id=%d&";
    private static final String GET_URL_UPLOAD_SERVER = "photos.getWallUploadServer?";
    private static final String SAVE_UPLOADED_PHOTO = "photos.saveWallPhoto?server=%d&hash=%s&";
    private static final String EDIT_POST_WALL = "wall.edit?owner_id=%d&post_id=%d&message=%s&attachments=%s%d_%d&";
    private static final String CREATE_COMMENT_FOR_POST_IN_WALL = "wall.createComment?owner_id=%d&post_id=%d&message=%s&";
    private static final String GET_LIKES_POST = "wall.getLikes?owner_id=%d&post_id=%d&";
    private static String versionApi = "v=%s&";
    private static String token = "access_token=%s&";

    public static void setToken(String text){
         token = String.format(token, text);
    }

    public static void setVersionApi(String text){
        versionApi = String.format(versionApi, text);
    }

    public static String requestBuilder(String method){
        return method + token + versionApi;
    }

    public static int createPost(String message) {
        return given()
                    .spec(getBaseSpec())
                    .get(String.format(requestBuilder(CREATE_POST), message))
                .then()
                    .extract().body().path("response.post_id");
    }

    private static String getUploadedServerForUploadImageToWall(){
        return given()
                    .spec(getBaseSpec())
                    .get(requestBuilder(GET_URL_UPLOAD_SERVER))
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
                    .queryParam(Attachments.PHOTO.toString(), uploadedPhoto.getPhoto())
                    .post(String.format(requestBuilder(SAVE_UPLOADED_PHOTO), uploadedPhoto.getServer(), uploadedPhoto.getHash()))
                .then()
                    .extract().body().jsonPath().getObject("", ResponseArraySavedPhoto.class);
    }

    public static void editPost(int postId, int ownerId, String message, SavedPhoto savedPhoto){
        given()
                    .spec(getBaseSpec())
                    .get(String.format(requestBuilder(EDIT_POST_WALL), ownerId, postId, message, Attachments.PHOTO, ownerId, savedPhoto.getId()))
                .then()
                    .extract().body().path("response.post_id");
    }

    public static PostComments createComment(int postId, int ownerId, String message){
        return given()
                    .spec(getBaseSpec())
                    .get(String.format(requestBuilder(CREATE_COMMENT_FOR_POST_IN_WALL), ownerId, postId, message))
                .then()
                    .extract().body().jsonPath().getObject("response", PostComments.class);
    }

    public static PostLikes getLikesPost(int ownerId, int postId){
        return given()
                    .spec(getBaseSpec())
                    .get(String.format(requestBuilder(GET_LIKES_POST), ownerId, postId))
                .then()
                    .extract().body().jsonPath().getObject("response", PostLikes.class);
    }

    public static void deletePost(int ownerId, int postId) {
        given()
                    .spec(getBaseSpec())
                    .get(String.format(requestBuilder(DELETE_POST), ownerId, postId))
                .then()
                    .extract().body().path("response");
    }
}
