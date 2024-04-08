package Model;

import Controller.Monster.ProjectileController;
import Model.Projectile.FireBall;
import Model.Projectile.Projectile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Monster {
    // Default value
    public int posX, posY;
    public int targetPosX, targetPosY;
    public int startPosX, startPosY;
    public int speed = 1;
    public int spawnSide;
    public String direction;
    public boolean isAttack;
    public double targetDistance;
    public boolean alive;

    // Image
    public BufferedImage up1   , up2   , up3   , up4;
    public BufferedImage down1 , down2 , down3 , down4;
    public BufferedImage left1 , left2 , left3 , left4;
    public BufferedImage right1, right2, right3, right4;
    public BufferedImage shadow;

    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4;
    public BufferedImage attackDown1 , attackDown2 , attackDown3 , attackDown4;
    public BufferedImage attackLeft1 , attackLeft2 , attackLeft3 , attackLeft4;
    public BufferedImage attackRight1, attackRight2, attackRight3, attackRight4;

    // Handle animations
    public int spriteNum = 1;
    public int spriteCount = 0;

    public Projectile projectile = new Projectile();

    // Constructor
    public Monster() {
    }

    // BEGIN: Setter & Getter
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getTargetPosX() {
        return targetPosX;
    }

    public void setTargetPosX(int targetPosX) {
        this.targetPosX = targetPosX;
    }

    public int getTargetPosY() {
        return targetPosY;
    }

    public void setTargetPosY(int targetPosY) {
        this.targetPosY = targetPosY;
    }

    public int getStartPosX() {
        return startPosX;
    }

    public void setStartPosX(int startPosX) {
        this.startPosX = startPosX;
    }

    public int getStartPosY() {
        return startPosY;
    }

    public void setStartPosY(int startPosY) {
        this.startPosY = startPosY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpawnSide() {
        return spawnSide;
    }

    public void setSpawnSide(int spawnSide) {
        this.spawnSide = spawnSide;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    public double getTargetDistance() {
        return targetDistance;
    }

    public void setTargetDistance(double targetDistance) {
        this.targetDistance = targetDistance;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // END: Setter & Getter

    // Check if monster is reach target position
    public boolean isReachTargetPos() {
        if (posX == targetPosX || posY == targetPosY) return true;
        return false;
    }

    public boolean isReachTargetDistance() {
        if (distance() >= targetDistance) return true;
        return false;
    }

    // BEGIN: Upload image
    public void getImage(String monster) {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_01.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_02.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_03.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_04.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_01.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_02.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_03.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_04.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_01.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_02.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_03.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_04.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_01.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_02.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_03.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_04.png"));

            shadow = ImageIO.read(getClass().getResourceAsStream("/Characters/Shadow_player.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getImageAttack(String monster) {
        try {
            attackUp1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_shot_01.png"));
            attackUp2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_shot_02.png"));
            attackUp3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_shot_03.png"));
            attackUp4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_shot_04.png"));

            attackDown1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_shot_01.png"));
            attackDown2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_shot_02.png"));
            attackDown3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_shot_03.png"));
            attackDown4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_shot_04.png"));

            attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_shot_01.png"));
            attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_shot_02.png"));
            attackLeft3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_shot_03.png"));
            attackLeft4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_shot_04.png"));

            attackRight1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_shot_01.png"));
            attackRight2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_shot_02.png"));
            attackRight3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_shot_03.png"));
            attackRight4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_shot_04.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // END: Upload image

    // BEGIN: Animation
    // Animation each direction
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

    // Monster animation
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

    public BufferedImage drawAttackAnimation() {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1)
                    image = attackUp1;
                if (spriteNum == 2)
                    image = attackUp2;
                if (spriteNum == 3)
                    image = attackUp3;
                if (spriteNum == 4)
                    image = attackUp4;
                break;
            case "down":
                if (spriteNum == 1)
                    image = attackDown1;
                if (spriteNum == 2)
                    image = attackDown2;
                if (spriteNum == 3)
                    image = attackDown3;
                if (spriteNum == 4)
                    image = attackDown4;
                break;
            case "left":
                if (spriteNum == 1)
                    image = attackLeft1;
                if (spriteNum == 2)
                    image = attackLeft2;
                if (spriteNum == 3)
                    image = attackLeft3;
                if (spriteNum == 4)
                    image = attackLeft4;
                break;
            case "right":
                if (spriteNum == 1)
                    image = attackRight1;
                if (spriteNum == 2)
                    image = attackRight2;
                if (spriteNum == 3)
                    image = attackRight3;
                if (spriteNum == 4)
                    image = attackRight4;
                break;
        }
        return image;
    }
    // END: Animation

    // BEGIN: MOVEMENT
    public double distance() {
        return Math.sqrt(Math.pow(startPosX - posX, 2) + Math.pow(startPosY - posY, 2));
    }

    public void moveUp() {
        posY = posY - speed;
    }

    public void moveDown() {
        posY = posY + speed;
    }

    public void moveLeft() {
        posX = posX - speed;
    }

    public void moveRight() {
        posX = posX + speed;
    }

    public void moveDownRight() {
        moveDown();
        moveRight();
    }

    public void moveDownLeft() {
        moveDown();
        moveLeft();
    }

    public void moveUpRight() {
        moveUp();
        moveRight();
    }

    public void moveUpLeft() {
        moveUp();
        moveLeft();
    }

    public void moveFromUp() {
        direction = "down";
        if (posY <= targetPosY) {
            if (posX <= targetPosX) {
                moveDownRight();
            } else {
                moveDownLeft();
            }
        }
    }

    public void moveFromDown() {
        direction = "up";
        if (posY >= targetPosY) {
            if (posX <= targetPosX) {
                moveUpRight();
            } else {
                moveUpLeft();
            }
        }
    }

    public void moveFromLeft() {
        direction = "right";
        if (posX <= targetPosX) {
            if (posY <= targetPosY) {
                moveDownRight();
            } else {
                moveUpRight();
            }
        }
    }

    public void moveFromRight() {
        direction = "left";
        if (posX >= targetPosX) {
            if (posY <= targetPosY) {
                moveDownLeft();
            } else {
                moveUpLeft();
            }
        }
    }
    // END: MOVEMENT

    // BEGIN: ATTACK
    public void attack() {
        isAttack = true;
        projectile.set(posX, posY, direction, alive);

    }
    // END: ATTACK
}
