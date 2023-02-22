package aquality.selenium.template.rest_assured;

import aquality.selenium.template.models.attachments.Attachment;
import aquality.selenium.template.models.attachments.AttachmentTypes;
import aquality.selenium.template.models.attachments.SavedPhoto;
import aquality.selenium.template.models.attachments.UploadedPhoto;
import aquality.selenium.template.models.post.PostLikes;
import aquality.selenium.template.models.post.PostComments;
import io.restassured.http.ContentType;

import java.io.File;
import java.util.List;

import static aquality.selenium.template.rest_assured.RestClient.getBaseSpec;
import static io.restassured.RestAssured.given;

public class VkApiUtils {
    private static final String CREATE_POST = "wall.post";
    private static final String DELETE_POST = "wall.delete";
    private static final String GET_URL_UPLOAD_SERVER = "photos.getWallUploadServer";
    private static final String SAVE_UPLOADED_PHOTO = "photos.saveWallPhoto";
    private static final String EDIT_POST_WALL = "wall.edit";
    private static final String CREATE_COMMENT_FOR_POST_IN_WALL = "wall.createComment";
    private static final String GET_LIKES_POST = "wall.getLikes";
    private static final String TEMPLATE_PARAM_ATTACHMENTS = "%s%d_%d";

    public static int createPost(String message) {
        return given()
                .spec(getBaseSpec())
                .queryParam(RequestParams.MESSAGE.toString(), message)
                .get(CREATE_POST)
                .then()
                .extract().body().path("response.post_id");
    }

    private static String getWallUploadServer() {
        return given()
                .spec(getBaseSpec())
                .get(GET_URL_UPLOAD_SERVER)
                .then()
                .extract().body().path("response.upload_url");
    }

    public static UploadedPhoto uploadPhoto(File file) {
        return given()
                .contentType(ContentType.MULTIPART)
                .multiPart(RequestParams.PHOTO.toString(), file)
                .post(getWallUploadServer())
                .then()
                .extract().body().jsonPath().getObject("", UploadedPhoto.class);
    }

    public static List<SavedPhoto> getSavedWallPhotos(UploadedPhoto uploadedPhoto) {
        return given()
                .spec(getBaseSpec())
                .queryParam(AttachmentTypes.PHOTO.toString(), uploadedPhoto.getPhoto())
                .queryParam(RequestParams.SERVER.toString(), uploadedPhoto.getServer())
                .queryParam(RequestParams.HASH.toString(), uploadedPhoto.getHash())
                .post(SAVE_UPLOADED_PHOTO)
                .then()
                .extract().body().jsonPath().getList("response", SavedPhoto.class);
    }

    public static void editPostWithAttachment(int postId, int ownerId, String message, AttachmentTypes attachmentType, Attachment attachment) {
        given()
                .spec(getBaseSpec())
                .queryParam(RequestParams.OWNER_ID.toString(), ownerId)
                .queryParam(RequestParams.POST_ID.toString(), postId)
                .queryParam(RequestParams.MESSAGE.toString(), message)
                .queryParam(RequestParams.ATTACHMENTS.toString(), String.format(TEMPLATE_PARAM_ATTACHMENTS, attachmentType, ownerId, attachment.getId()))
                .get(EDIT_POST_WALL)
                .then()
                .extract().body().path("response.post_id");
    }

    public static PostComments createComment(int postId, int ownerId, String message) {
        return given()
                .spec(getBaseSpec())
                .queryParam(RequestParams.OWNER_ID.toString(), ownerId)
                .queryParam(RequestParams.POST_ID.toString(), postId)
                .queryParam(RequestParams.MESSAGE.toString(), message)
                .get(CREATE_COMMENT_FOR_POST_IN_WALL)
                .then()
                .extract().body().jsonPath().getObject("response", PostComments.class);
    }

    public static PostLikes getLikesPost(int ownerId, int postId) {
        return given()
                .spec(getBaseSpec())
                .queryParam(RequestParams.OWNER_ID.toString(), ownerId)
                .queryParam(RequestParams.POST_ID.toString(), postId)
                .get(GET_LIKES_POST)
                .then()
                .extract().body().jsonPath().getObject("response", PostLikes.class);
    }

    public static void deletePost(int ownerId, int postId) {
        given()
                .spec(getBaseSpec())
                .queryParam(RequestParams.OWNER_ID.toString(), ownerId)
                .queryParam(RequestParams.POST_ID.toString(), postId)
                .get(DELETE_POST)
                .then()
                .extract().body().path("response");
    }
}