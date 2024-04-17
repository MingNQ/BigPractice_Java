package Model;

import javax.imageio.ImageIO;
import java.util.Objects;

public class Monster extends Entity {
    // Default value
    public double targetPosX, targetPosY;
    public double startPosX, startPosY;
    public int spawnSide;
    public double targetDistance;

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
        return distance() >= targetDistance;
    }

    // BEGIN: Upload image
    public void getImage(String monster) {
        try {
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/down_01.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/down_02.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/down_03.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/down_04.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/left_01.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/left_02.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/left_03.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/left_04.png")));

            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/right_01.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/right_02.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/right_03.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/right_04.png")));

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/up_01.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/up_02.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/up_03.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/" + monster + "/up_04.png")));

            shadow = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Characters/Shadow_player.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
