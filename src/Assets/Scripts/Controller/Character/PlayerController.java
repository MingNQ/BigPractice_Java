package Controller.Character;

import Controller.Event.KeyHandler;
import Controller.Item.ItemFlashController;
import Controller.Item.ItemGhostController;
import Model.Player;
import View.GameView;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerController {
    public Player player;
    GameView gp;
    KeyHandler keyH;
    // Item
    public boolean isUsed;
    public ItemFlashController flash = new ItemFlashController(this);
    public ItemGhostController ghost = new ItemGhostController(this);

    public PlayerController(GameView gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.player = new Player();
        this.player.getPlayerImage("Character_01");
        // getEntity().setSolidArea(new Rectangle(8, 16, (int)(gp.tileSize/(1.5)), (int)(gp.tileSize/(1.5))));

        this.setDefaultValues();
    }

    // Default value
    public void setDefaultValues() {
        player.posX = gp.screenWidth/2;
        player.posY = gp.screenHeight/2;
        player.speed = 5;
        player.direction = "down";
        isUsed = false;
        player.isCollision = false;
    }

    // Update is called 60 per frame
    public void update() {
        useItem(); // Check if player use item

        // Check player movement
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // Movement
            if (keyH.upPressed) {
                if (keyH.leftPressed) {
                    player.direction = "left";
                    player.moveUpLeft();
                } else if (keyH.rightPressed) {
                    player.direction = "right";
                    player.moveUpRight();
                } else {
                    player.direction = "up";
                    player.moveUp();
                }
            } else if (keyH.downPressed) {
                if (keyH.leftPressed) {
                    player.direction = "left";
                    player.moveDownLeft();
                } else if (keyH.rightPressed) {
                    player.direction = "right";
                    player.moveDownRight();
                } else {
                    player.direction = "down";
                    player.moveDown();
                }
            } else if (keyH.leftPressed) {
                player.direction = "left";
                player.moveLeft();
            } else if (keyH.rightPressed) {
                player.direction = "right";
                player.moveRight();
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
            player.setAnimations();
        }
    }

    // Render image on game view
    public void draw(Graphics2D g2) {
        // Handle direction property
        BufferedImage image = player.drawAnimation();

        System.out.println("Player: " + player.direction + player.posX + player.posY);

        double tmpPosX = player.posX + 8;
        double tmpPosY = player.posY + 28;
        double shadowScaleX = gp.tileSize / player.shadow.getWidth();
        double shadowScaleY = gp.tileSize / player.shadow.getHeight();
        double playerScaleX = (double)gp.tileSize / image.getWidth();
        double playerScaleY = (double)gp.tileSize / image.getHeight();

        // Draw player's image
        AffineTransform at = AffineTransform.getTranslateInstance(tmpPosX, tmpPosY);
        at.scale(shadowScaleX, shadowScaleY);
        g2.drawImage(player.shadow, at, null);

        at = AffineTransform.getTranslateInstance(player.posX, player.posY);
        at.scale(playerScaleX, playerScaleY);
        g2.drawImage(image, at, null);
    }

    // Player use item
    public void useItem() {
        isUsed = true;
        // Use first item
        if (keyH.fistSkillPressed && flash.isAvailable()) {
            flash.use();
            if (player.direction == "up") {
                player.posX = player.posX;
                player.posY = player.posY - gp.tileSize;
            } else if (player.direction == "down") {
                player.posX = player.posX;
                player.posY = player.posY + gp.tileSize;
            } else if (player.direction == "left") {
                player.posX = player.posX - gp.tileSize;
                player.posY = player.posY;;
            } else if (player.direction == "right") {
                player.posX = player.posX + gp.tileSize;
                player.posY = player.posY;
            }
            System.out.println("Player use Flash!");
        }
        // Use second item
        if (keyH.secondSkillPressed && ghost.isAvailable()) {
            double buff = 1.2;
            ghost.use();
            player.speed = player.speed * 1.2;
//            getEntity().setSpeed(getEntity().getSpeed() * 1.2);
            System.out.println("Player use Ghost! Speed is " + player.speed);
        }
    }

    // Countdown time when timeout using item
    public void countDount() {
        flash.countDown();
        ghost.countDown();
        if (ghost.getGhostTimeUseCurr() <= 0 && !ghost.isTimeOut()) {
            player.speed = player.speed / 1.2;
//            getEntity().setSpeed(getEntity().getSpeed() / 1.2);
            ghost.setTimeOut(true);
            System.out.println("Timeout Ghost! Speed is " + player.speed);
        }
    }
}
