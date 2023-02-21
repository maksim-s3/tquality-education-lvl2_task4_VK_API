package aquality.selenium.template.utilities;

import aquality.selenium.browser.AqualityServices;
import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class FileHelper {
    private static final String RESOURCES_PATH = "/src/test/resources/";
    private static final String LOG_FILE = "target/log/log.log";

    private static String getProjectBaseDir() {
        return System.getProperty("user.dir") != null ? System.getProperty("user.dir") : System.getProperty("project.basedir");
    }

    public static File getResourceFileByName(String fileName) {
        return Paths.get(getProjectBaseDir(), RESOURCES_PATH + fileName).toFile();
    }

    public static byte[] getLogAsBytes() {
        try {
            return Files.readAllBytes(Paths.get(LOG_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File downloadImage(String src, String fileName, String formatName) {
        BufferedImage bufferedImage = null;
        File outputfile = null;
        try {
            bufferedImage = ImageIO.read(new URL(src));
            outputfile = new File(AqualityServices.getBrowser().getDownloadDirectory() + fileName);
            ImageIO.write(bufferedImage, formatName, outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputfile;
    }
}
