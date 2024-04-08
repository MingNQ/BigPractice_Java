package Model.Projectile;

import Model.Monster;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class FireBall extends Projectile {
    // Constructor
    public FireBall() {
        this.speed = 2;
        this.getImage();
    }

    public void getImage() {
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveUp() {
        this.posY -= speed;
    }

    public void moveDown() {
        this.posY += speed;
    }

    public void moveLeft() {
        this.posX -= speed;
    }

    public void moveRight() {
        this.posX += speed;
    }

    public void moveUpLeft() {
        this.direction = "up";
        this.moveUp();
        this.moveLeft();
    }

    public void moveUpRight() {
        this.direction = "up";
        this.moveUp();
        this.moveRight();
    }

    public void moveDownLeft() {
        this.direction = "down";
        this.moveDown();
        this.moveLeft();
    }

    public void moveDownRight() {
        this.direction = "down";
        this.moveDown();
        this.moveRight();
    }
}
