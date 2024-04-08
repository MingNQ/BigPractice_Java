package Model.Projectile;

import java.awt.image.BufferedImage;

public class Projectile {
    public int posX, posY;
    public int life;
    public boolean alive;
    public int spriteNum;
    public int spriteCount;
    public String direction;
    public int speed;

    BufferedImage img;

    public Projectile() {
    }

    public void set(int posX, int posY, String direction, boolean alive) {
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
        this.alive = alive;
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

    public BufferedImage drawAnimation() {
        BufferedImage image = null;

        switch (this.direction) {
            case "up":
                if (spriteNum == 1)
                    image = img;
                if (spriteNum == 2)
                    image = img;
                if (spriteNum == 3)
                    image = img;
                if (spriteNum == 4)
                    image = img;
                break;
            case "down":
                if (spriteNum == 1)
                    image = img;
                if (spriteNum == 2)
                    image = img;
                if (spriteNum == 3)
                    image = img;
                if (spriteNum == 4)
                    image = img;
                break;
            case "left":
                if (spriteNum == 1)
                    image = img;
                if (spriteNum == 2)
                    image = img;
                if (spriteNum == 3)
                    image = img;
                if (spriteNum == 4)
                    image = img;
                break;
            case "right":
                if (spriteNum == 1)
                    image = img;
                if (spriteNum == 2)
                    image = img;
                if (spriteNum == 3)
                    image = img;
                if (spriteNum == 4)
                    image = img;
                break;
        }
        return image;
    }
}
