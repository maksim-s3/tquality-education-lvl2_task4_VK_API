package aquality.selenium.template.utilities;

import io.qameta.allure.Attachment;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class AllureHelper {
    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public static byte[] takeScreenshot() {
        return getBrowser().getScreenshot();
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Log", type = "text/html", fileExtension = ".log")
    public static byte[] takeLog() {
        return FileHelper.getLogAsBytes();
    }
}
