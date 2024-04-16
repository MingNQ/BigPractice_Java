package Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AssetsSetup {

    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = scaleImage.createGraphics();

        g2.drawImage(scaleImage, 0, 0, width, height, null);
        g2.dispose();

        return scaleImage;
    }

}
