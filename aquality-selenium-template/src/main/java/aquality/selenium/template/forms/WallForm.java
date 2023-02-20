package aquality.selenium.template.forms;

import aquality.selenium.elements.Label;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import aquality.selenium.template.utilities.FileHelper;
import org.openqa.selenium.By;

import java.io.File;

public class WallForm extends Form {
    private static final String ATTRIBUTE_ID_AUTHOR_COMMENT = "data-from-id";
    private String templatePost = "wpt%d_%d";
    private String templateComment = "//*[@id='post%d_%d']";
    private String templateReplies = "//*[@id='replies_wrap%d_%d']//*[contains(@class, 'js-replies_next_label')]";
    private String templateLikePost = "//*[contains(@class, 'like_wrap _like_wall%d_%d')]//*[contains(@class, 'PostButtonReactions--post')]";
    private String templateAuthorComment = "//*[@id='post%d_%d']//*[@class='author']";
    private String templateTextComment = "//*[@id='post%d_%d']//*[contains(@class, 'wall_reply_text')]";
    private String templateImageInPost = "//*[@id='wpt%d_%d']//img";
    private By locatorTextPost = By.xpath("//*[contains(@class, 'wall_post_text')]");


    public WallForm() {
        super(By.id("profile_wall"), "profile wall");
    }

    public boolean isPostNotExist(int ownerId, int postId) {
        ILabel post = getElementFactory().getLabel(By.id(String.format(templatePost, ownerId, postId)), "post");
        return post.state().waitForNotDisplayed();
    }

    public boolean isPostCreatedValidUser(int ownerId, int postId){
        ILabel postAuthor = getElementFactory().getLabel(By.id(String.format(templatePost, ownerId, postId)), "post");
        return postAuthor.state().waitForDisplayed();
    }

    public String getTextFromPost(int ownerId, int postId){
        ILabel postAuthor = getElementFactory().getLabel(By.id(String.format(templatePost, ownerId, postId)), "post");
        ILabel postText = getElementFactory().findChildElement(postAuthor, locatorTextPost, "text in post", Label.class);
        return postText.getText();
    }

    public String getTextComment(int ownerId, int commentId) {
        ILabel commentText = getElementFactory().getLabel(By.xpath(String.format(templateTextComment, ownerId, commentId)), "text comment");
        return commentText.getText();
    }

    public int getIdAuthorComment(int ownerId, int commentId) {
        ILabel authorComment = getElementFactory().getLabel(By.xpath(String.format(templateAuthorComment, ownerId, commentId)), "author comment");
        return Integer.parseInt(authorComment.getAttribute(ATTRIBUTE_ID_AUTHOR_COMMENT));
    }

    public boolean isCommentExist(int ownerId, int commentId) {
        ILabel comment = getElementFactory().getLabel(By.xpath(String.format(templateComment, ownerId, commentId)), "comment");
        return comment.state().waitForExist();
    }

    public void clickShowNextComments(int ownerId, int postId) {
        IButton btnShowNextComments = getElementFactory().getButton(By.xpath(String.format(templateReplies, ownerId, postId)), "show next comments");
        btnShowNextComments.state().waitForDisplayed();
        btnShowNextComments.click();
    }

    public void addLikeAPost(int ownerId, int postId){
        IButton btnLike = getElementFactory().getButton(By.xpath(String.format(templateLikePost, ownerId, postId)), "like a post");
        btnLike.click();
    }

    public File getImageFromPost(int postId, int ownerId) {
        ILink image = getElementFactory().getLink(By.xpath(String.format(templateImageInPost, ownerId, postId)), "image in the post");
        return FileHelper.downloadImage(image.getAttribute("src"), "copy_avatar", "png");
    }
}
