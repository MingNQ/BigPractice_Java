package Controller.Character;

import Controller.Event.KeyHandler;
import Controller.Item.ItemFlashController;
import Controller.Item.ItemGhostController;
import View.GameView;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerController extends EntityController {
    GameView gp;
    KeyHandler keyH;
    // Item
    private boolean isUsed;
    ItemFlashController flash = new ItemFlashController(this);
    ItemGhostController ghost = new ItemGhostController(this);

    public PlayerController(GameView gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        getEntity().setSolidArea(new Rectangle(8, 16, (int)(gp.tileSize/(1.5)), (int)(gp.tileSize/(1.5))));

        this.setDefaultValues();
        this.getPlayerImage("Character_01");
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    // Default value
    public void setDefaultValues() {
        getEntity().setPosX(100);
        getEntity().setPosY(100);
        getEntity().setSpeed(6);
        setDirection("down");
        isUsed = false;
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

    // Update is called 60 per frame
    public void update() {
        useItem(); // Check if player use item

        // Check player movement
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // Movement
            if (keyH.upPressed) {
                if (keyH.leftPressed) {
                    setDirection("left");
                    moveUpLeft();
                } else if (keyH.rightPressed) {
                    setDirection("right");
                    moveUpRight();
                } else {
                    setDirection("up");
                    moveUp();
                }
            } else if (keyH.downPressed) {
                if (keyH.leftPressed) {
                    setDirection("left");
                    moveDownLeft();
                } else if (keyH.rightPressed) {
                    setDirection("right");
                    moveDownRight();
                } else {
                    setDirection("down");
                    moveDown();
                }
            } else if (keyH.leftPressed) {
                setDirection("left");
                moveLeft();
            } else if (keyH.rightPressed) {
                setDirection("right");
                moveRight();
            }

            // Collision
            /*
            getEntity().setCollisionOn(false);
            gp.checkTile(this.getEntity());

            if (getEntity().isCollisionOn() == false) {
                switch (getEntity().getDirection()) {
                    case "up":
                        moveUp();
                        break;
                    case "down":
                        moveDown();
                        break;
                    case "left":
                        moveLeft();
                        break;
                    case "right":
                        moveRight();
                        break;
                }
            }
            */

            // Animations
            setAnimations();
        }
    }

    // Render image on game view
    public void draw(Graphics2D g2) {
        // Handle direction property
        BufferedImage image = drawAnimation();

        // Draw player's image
        int shadowTile = (int) (gp.tileSize / 1.5);
        AffineTransform at = AffineTransform.getTranslateInstance(getEntity().getPosX() + 8, getEntity().getPosY() + 28);
        at.scale(gp.tileSize/shadow.getWidth(), gp.tileSize/shadow.getHeight());
        g2.drawImage(shadow, at, null);

        at = AffineTransform.getTranslateInstance(getEntity().getPosX(), getEntity().getPosY());
        at.scale((double)gp.tileSize / image.getWidth() , (double)gp.tileSize / image.getHeight());
        g2.drawImage(image, at, null);
    }

    // Player use item
    public void useItem() {
        isUsed = true;
        // Use first item
        if (keyH.fistSkillPressed && flash.isAvailable()) {
            flash.use();
            if (getDirection() == "up") {
                getEntity().setPosX(getEntity().getPosX());
                getEntity().setPosY(getEntity().getPosY() - gp.tileSize);
            } else if (getDirection() == "down") {
                getEntity().setPosX(getEntity().getPosX());
                getEntity().setPosY(getEntity().getPosY() + gp.tileSize);
            } else if (getDirection() == "left") {
                getEntity().setPosX(getEntity().getPosX() - gp.tileSize);
                getEntity().setPosY(getEntity().getPosY());
            } else if (getDirection() == "right") {
                getEntity().setPosX(getEntity().getPosX() + gp.tileSize);
                getEntity().setPosY(getEntity().getPosY());
            }
            System.out.println("Player use Flash!");
        }
        // Use second item
        if (keyH.secondSkillPressed && ghost.isAvailable()) {
            double buff = 1.2;
            ghost.use();
            getEntity().setSpeed(getEntity().getSpeed() * 1.2);
            System.out.println("Player use Ghost! Speed is " + getEntity().getSpeed());
        }
    }

    // Countdown time when timeout using item
    public void countDount() {
        flash.countDown();
        ghost.countDown();
        if (ghost.getGhostTimeUseCurr() <= 0 && !ghost.isTimeOut()) {
            getEntity().setSpeed(getEntity().getSpeed() / 1.2);
            ghost.setTimeOut(true);
            System.out.println("Timeout Ghost! Speed is " + getEntity().getSpeed());
        }
    }
}
