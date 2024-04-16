package Model;

import Controller.AssetsSetup;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Monster extends Entity {
    // Default value
    public double targetPosX, targetPosY;
    public double startPosX, startPosY;
    public int spawnSide;
    public double targetDistance;

    // Image
    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4;
    public BufferedImage attackDown1 , attackDown2 , attackDown3 , attackDown4;
    public BufferedImage attackLeft1 , attackLeft2 , attackLeft3 , attackLeft4;
    public BufferedImage attackRight1, attackRight2, attackRight3, attackRight4;

    // Constructor
    public Monster() {
        speed = 1.5;
    }

    // Check if monster is reach target position
    public boolean isReachTargetPos() {
        if (posX == targetPosX || posY == targetPosY) return true;
        boolean check = false;
        switch (spawnSide) {
            case 0: // Up
                if (posY >= targetPosY) check = true;
                break;
            case 1: // Down
                if (posY <= targetPosY) check = true;
                break;
            case 2: // Left
                if (posX >= targetPosX) check = true;
                break;
            case 3: // Right
                if (posX <= targetPosX) check = true;
                break;
        }

        return check;
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

//    public void getImageAttack(String monster) {
//        try {
//            attackUp1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_shot_01.png"));
//            attackUp2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_shot_02.png"));
//            attackUp3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_shot_03.png"));
//            attackUp4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/up_shot_04.png"));
//
//            attackDown1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_shot_01.png"));
//            attackDown2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_shot_02.png"));
//            attackDown3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_shot_03.png"));
//            attackDown4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/down_shot_04.png"));
//
//            attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_shot_01.png"));
//            attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_shot_02.png"));
//            attackLeft3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_shot_03.png"));
//            attackLeft4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/left_shot_04.png"));
//
//            attackRight1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_shot_01.png"));
//            attackRight2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_shot_02.png"));
//            attackRight3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_shot_03.png"));
//            attackRight4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/"+ monster + "/right_shot_04.png"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    // END: Upload image

    // BEGIN: Animation
    // Animation each direction
//    public BufferedImage drawAttackAnimation() {
//        BufferedImage image = null;
//        switch (direction) {
//            case "up":
//                if (spriteNum == 1)
//                    image = attackUp1;
//                if (spriteNum == 2)
//                    image = attackUp2;
//                if (spriteNum == 3)
//                    image = attackUp3;
//                if (spriteNum == 4)
//                    image = attackUp4;
//                break;
//            case "down":
//                if (spriteNum == 1)
//                    image = attackDown1;
//                if (spriteNum == 2)
//                    image = attackDown2;
//                if (spriteNum == 3)
//                    image = attackDown3;
//                if (spriteNum == 4)
//                    image = attackDown4;
//                break;
//            case "left":
//                if (spriteNum == 1)
//                    image = attackLeft1;
//                if (spriteNum == 2)
//                    image = attackLeft2;
//                if (spriteNum == 3)
//                    image = attackLeft3;
//                if (spriteNum == 4)
//                    image = attackLeft4;
//                break;
//            case "right":
//                if (spriteNum == 1)
//                    image = attackRight1;
//                if (spriteNum == 2)
//                    image = attackRight2;
//                if (spriteNum == 3)
//                    image = attackRight3;
//                if (spriteNum == 4)
//                    image = attackRight4;
//                break;
//        }
//        return image;
//    }
    // END: Animation


    // BEGIN: MOVEMENT
    public double distance() {
        return Math.sqrt(Math.pow(startPosX - posX, 2) + Math.pow(startPosY - posY, 2));
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
}
