package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    public Player() {
    }

    // Upload image
    public void getPlayerImage(String character) {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/down_3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/down_4.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/left_3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/left_4.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/right_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/right_4.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/up_3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/Characters/"+ character + "/up_4.png"));

            shadow = ImageIO.read(getClass().getResourceAsStream("/Characters/Shadow_player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAnimations() {
        spriteCount++;
        if (spriteCount > 5) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 1;
            }
            spriteCount = 0;
        }
    }

    // Draw animation per frame
    public BufferedImage drawAnimation() {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1)
                    image = up1;
                if (spriteNum == 2)
                    image = up2;
                if (spriteNum == 3)
                    image = up3;
                if (spriteNum == 4)
                    image = up4;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                if (spriteNum == 2)
                    image = down2;
                if (spriteNum == 3)
                    image = down3;
                if (spriteNum == 4)
                    image = down4;
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
                if (spriteNum == 3)
                    image = left3;
                if (spriteNum == 4)
                    image = left4;
                break;
            case "right":
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
                if (spriteNum == 3)
                    image = right3;
                if (spriteNum == 4)
                    image = right4;
                break;
        }
        return image;
    }
}
