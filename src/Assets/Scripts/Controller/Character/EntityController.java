package Controller.Character;

import Model.Entity;
import java.awt.image.BufferedImage;

public class EntityController {
    public static double SQRT2 = Math.sqrt(2);

    // Model entity
    private Entity entity;

    // Render image
    BufferedImage up1   , up2   , up3   , up4;
    BufferedImage down1 , down2 , down3 , down4;
    BufferedImage left1 , left2 , left3 , left4;
    BufferedImage right1, right2, right3, right4;
    BufferedImage shadow;

    // Handle animations
    private int spriteNum = 1;
    private int spriteCount = 0;

    public EntityController() {
        entity = new Entity();
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void moveUp() {
        entity.setPosY(entity.getPosY() - entity.getSpeed());
    }

    public void moveDown() {
        entity.setPosY(entity.getPosY() + entity.getSpeed());
    }

    public void moveLeft() {
        entity.setPosX(entity.getPosX() - entity.getSpeed());
    }

    public void moveRight() {
        entity.setPosX(entity.getPosX() + entity.getSpeed());
    }

    public void moveUpLeft() {
        entity.setPosY(entity.getPosY() - entity.getSpeed()/SQRT2);
        entity.setPosX(entity.getPosX() - entity.getSpeed()/SQRT2);
//        moveUp();
//        moveLeft();
//        System.out.println("Move up left");
    }

    public void moveUpRight() {
        entity.setPosY(entity.getPosY() - entity.getSpeed()/SQRT2);
        entity.setPosX(entity.getPosX() + entity.getSpeed()/SQRT2);
//        moveUp();
//        moveRight();
//        System.out.println("Move up right");
    }

    public void moveDownLeft() {
        entity.setPosY(entity.getPosY() + entity.getSpeed()/SQRT2);
        entity.setPosX(entity.getPosX() - entity.getSpeed()/SQRT2);
//        moveDown();
//        moveLeft();
//        System.out.println("Move down left");
    }

    public void moveDownRight() {
        entity.setPosY(entity.getPosY() + entity.getSpeed()/SQRT2);
        entity.setPosX(entity.getPosX() + entity.getSpeed()/SQRT2);
//        moveDown();
//        moveRight();
//        System.out.println("Move down right");
    }

    public void setDirection(String d) {
        entity.setDirection(d);
    }

    public String getDirection() {
        return entity.getDirection();
    }

    // Set sprite animation of each direction
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

        switch (getEntity().getDirection()) {
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
