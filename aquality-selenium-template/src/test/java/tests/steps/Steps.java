package tests.steps;

import aquality.selenium.template.configuration.Configuration;
import aquality.selenium.template.forms.*;
import aquality.selenium.template.forms.navigation.*;
import aquality.selenium.template.forms.pages.*;
import aquality.selenium.template.models.attachments.*;
import aquality.selenium.template.models.wall.*;
import aquality.selenium.template.rest_assured.VkApiUtils;
import aquality.selenium.template.utilities.*;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.util.List;
import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.template.utilities.AllureHelper.takeScreenshot;

public class Steps {
    private static final String DEFAULT_URL = Configuration.getStartUrl();
    private SoftAssert softAssertion = new SoftAssert();
    private LoginEntryForm loginEntryForm = new LoginEntryForm();
    private PasswordEntryForm passwordEntryForm = new PasswordEntryForm();
    private SideBar sideBar = new SideBar();
    private MyPage myPage = new MyPage();
    private WallForm wallForm = new WallForm();


    @Step("Go to authorization page")
    public void goToAuthorizationPage() {
        getBrowser().goTo(DEFAULT_URL);
        getBrowser().maximize();
        takeScreenshot();
    }

    @Step("Log in")
    public void login(String login, String password) {
        loginEntryForm.state().waitForDisplayed();
        loginEntryForm.setLogin(login);
        takeScreenshot();
        loginEntryForm.clickSignInButton();
        passwordEntryForm.clickSwitchToPassword();
        passwordEntryForm.setPassword(password);
        takeScreenshot();
        passwordEntryForm.clickContinue();
    }

    @Step("Go to my page")
    public void goToMyPage() {
        sideBar.clickItemMenu(ItemsMenuSideBar.MY_PAGE);
        myPage.state().waitForDisplayed();
        takeScreenshot();
    }

    @Step("Using an API request, create a post with random message on the wall and get the post id from the response")
    public int getIdFromCreatedPostWithRandomTextOnTheWall(String message) {
        WallPost post = VkApiUtils.createPost(message);
        takeScreenshot();
        return post.getNestedWallPost().getPostId();
    }

    @Step("Without refreshing the page, make sure that an entry with the right text from the right user has appeared on the wall")
    public void assertTextOnTheWallFromCorrectUser(String message, int postId, int ownerId) {
        Assert.assertTrue(wallForm.isPostCreatedValidUser(ownerId, postId));
        Assert.assertEquals(wallForm.getTextFromPost(ownerId, postId), message);
        takeScreenshot();
    }

    @Step("Edit an entry via an API request - change the text and add (upload) any image")
    public void editTextAndAddPictureInPost(int postId, int ownerId, String message, File image) {
        SavedWallPhoto savedWallPhotos = VkApiUtils.getSavedWallPhotos(VkApiUtils.uploadPhoto(image));
        VkApiUtils.editPostWithAttachment(postId, ownerId, message, AttachmentTypes.PHOTO, savedWallPhotos.getNestedSavedWallPhotos().get(0));
        takeScreenshot();
    }

    @Step("Without refreshing the page, make sure that the text of the message has changed and the uploaded picture has been added (make sure that the pictures are the same)")
    public void assertTextAndPicture(int postId, int ownerId, String message, File image) {
        File imageFromPost = wallForm.getImageFromPost(postId, ownerId);
        Assert.assertEquals(wallForm.getTextFromPost(ownerId, postId), message);
        softAssertion.assertTrue(CompareImage.isEquals(imageFromPost, image), "picture from disk does not match the picture from post");
        takeScreenshot();
    }

    @Step("Using an API request to add a comment to an entry with random text")
    public int addComment(int postId, int ownerId, String message) {
        CreatedComment createdComment = VkApiUtils.createComment(postId, ownerId, message);
        takeScreenshot();
        return createdComment.getNestedComment().getCommentId();
    }

    @Step("Without refreshing the page, make sure that a comment from the correct user has been added to the desired entry")
    public void assertExistCommentFromCorrectUser(int commentId, int ownerId, int postId, String randomTextForComment) {
        wallForm.clickShowNextComments(ownerId, postId);
        Assert.assertTrue(wallForm.isCommentExist(ownerId, commentId), "comment is not exist");

        int authorCommentId = wallForm.getIdAuthorComment(ownerId, commentId);
        String textComment = wallForm.getTextComment(ownerId, commentId);
        Assert.assertEquals(authorCommentId, ownerId, "author comment is not equals");
        Assert.assertEquals(textComment, randomTextForComment, "text comment is not equals");
        takeScreenshot();
    }

    @Step("Using UI put a like to the record")
    public void likePost(int ownerId, int postId) {
        wallForm.addLikeAPost(ownerId, postId);
        takeScreenshot();
    }

    @Step("Through an API request, make sure that the record has a like from the correct user")
    public void assertLikePostFromCorrectUser(int ownerId, int postId) {
        List<UserPutLikeOnPost> users = VkApiUtils.getLikesPost(ownerId, postId).getNestedGetLikes().getUsers();
        boolean isUser = users.stream().anyMatch(user -> ownerId==user.getUid());
        Assert.assertTrue(isUser, "there is no user in the list of likes");
    }

    @Step("Delete the created record via an API request")
    public void deletePost(int ownerId, int postId) {
        takeScreenshot();
        VkApiUtils.deletePost(ownerId, postId);
    }

    @Step("Without refreshing the page, make sure that the entry is deleted")
    public void assertPostHasBeenDeleted(int ownerId, int postId) {
        Assert.assertTrue(wallForm.isPostNotExist(ownerId, postId), "post is not deleted");
        takeScreenshot();
    }
}
