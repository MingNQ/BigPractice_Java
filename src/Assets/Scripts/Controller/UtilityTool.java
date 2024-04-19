package Controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage image, int width, int height) {
        BufferedImage scale = new BufferedImage(width, height, image.getType());
        Graphics2D g2 = (Graphics2D) scale.getGraphics();
        g2.drawImage(scale, 0, 0, width, height, null);
        return scale;
    }
}
