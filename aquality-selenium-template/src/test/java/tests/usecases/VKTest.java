package tests.usecases;

import aquality.selenium.template.utilities.Random;
import io.qameta.allure.Description;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;
import tests.steps.Steps;
import java.io.File;

public class VKTest extends BaseTest {
    private Steps steps = new Steps();
    private File image = new File("src/test/resources/avatar.png");

    @Description("VK API")
    @Parameters({"login", "password", "ownerId"})
    @Test
    public void test(String login, String password, int ownerId) {
        steps.goToAuthorizationPage();

        steps.login(login, password);

        steps.goToMyPage();

        String randomTextForPost = Random.getRandomText();
        int postId = steps.getIdFromCreatedPostWithRandomTextOnTheWall(randomTextForPost);

        steps.assertTextOnTheWallFromCorrectUser(randomTextForPost,postId, ownerId);

        String newRandomTextForPost = Random.getRandomText();
        steps.changeTextAndAddPictureInPost(postId, ownerId, newRandomTextForPost, image);

        steps.assertTextAndPicture(postId, ownerId, newRandomTextForPost, image);

        String randomTextForComment = Random.getRandomText();
        int commentId = steps.addComment(postId, ownerId, randomTextForComment);

        steps.assertCommentFromCorrectUser(commentId, ownerId, postId, randomTextForComment);

        steps.likePost(ownerId, postId);

        steps.assertLikePostFromCorrectUser(ownerId, postId);

        steps.deletePost(ownerId, postId);

        steps.assertPostHasBeenDeleted(ownerId, postId);
    }
}
