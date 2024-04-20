package Model;

import Controller.UI.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Laser extends Projectile {
    public BufferedImage startImage, endImage;

    public Laser() {
        super();
    }

    @Override
    public void getImage() {
        startImage = setUp(1);
        endImage = setUp(2);
    }

    @Override
    public BufferedImage setUp(int num) {
        BufferedImage image = null;
        UtilityTool uTool = new UtilityTool();

        try {
            image = ImageIO.read(getClass().getResourceAsStream("Monsters/MonsterProjectile/laser_0" + num +".png"));
            image = uTool.scaleImage(image, 48, 48);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void setAnimations() {

    }

    @Override
    public BufferedImage drawAnimation() {
        BufferedImage image = null;
        return image;
    }

}
