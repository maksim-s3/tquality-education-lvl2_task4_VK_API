package aquality.selenium.template.utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CompareImage {
    public static boolean isEquals(File image1, File image2) {
        BufferedImage bufImg1 = null;
        BufferedImage bufImg2 = null;
        try {
            bufImg1 = ImageIO.read(image1);
            bufImg2 = ImageIO.read(image2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (bufImg1.getWidth() != bufImg2.getWidth() || bufImg1.getHeight() != bufImg2.getHeight()){
            return false;
        }

        for (int row = 0; row <bufImg1.getHeight(); row++) {
            for (int col = 0; col < bufImg1.getWidth(); col++) {
                int rgb = bufImg1.getRGB(col, row);
                int rgb2 = bufImg2.getRGB(col, row);
                if (rgb != rgb2){
                    return false;
                }
            }
        }
        return true;
    }
}
