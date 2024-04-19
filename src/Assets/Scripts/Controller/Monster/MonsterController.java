package Controller.Monster;

import Model.Monster;
import View.GameView;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MonsterController {
    public static final Random rd = new Random();

    // Monsters
    public Monster ms;
    public GameView gp;

    public MonsterController(GameView gp) {
        this.gp = gp;
        this.ms = new Monster();
        ms.targetPosX = gp.screenWidth/2;
        ms.targetPosY = gp.screenHeight/2;
        setDefaultValue();
    }

    // Set default value
    public void setDefaultValue() {
        spawn();
        ms.targetDistance = 400;
        ms.startPosX = ms.posX;
        ms.startPosY = ms.posY;

        double monRecPosX = ms.posX + 12;
        double monRecPosY = ms.posY + 16;
        double monRecW = ms.recW - 8;
        double monRecH = ms.recH - 8;
        ms.hitbox = new Rectangle2D.Double(monRecPosX, monRecPosY, monRecW, monRecH);
    }

    // Random spawn monster
    public void spawn() {
        ms.spawnSide = rd.nextInt(4);
        String num = rd.nextInt(4) + "";
        ms.getImage("Monster_0" + num);

        switch (ms.spawnSide) {
            case 0: // Spawn from up
                ms.direction = "down";
                ms.posX = rd.nextInt(gp.screenWidth);
                ms.posY = -1;
                break;
            case 1: // Spawn from down
                ms.direction = "up";
                ms.posX = rd.nextInt(gp.screenWidth);
                ms.posY = gp.screenHeight;
                break;
            case 2: // Spawn from left
                ms.direction = "right";
                ms.posX = -1;
                ms.posY = rd.nextInt(gp.screenHeight);
                break;
            case 3: // Spawn from right
                ms.direction = "left";
                ms.posX = gp.screenWidth;
                ms.posY = rd.nextInt(gp.screenHeight);
                break;
        }
    }

    // Render image on game view
    public void draw(Graphics2D g2) {
        double tmpPosX = ms.posX + 8;
        double tmpPosY = ms.posY + 16;
        double shadowScaleX = gp.tileSize/ms.shadow.getWidth();
        double shadowScaleY = gp.tileSize/ms.shadow.getHeight();

        BufferedImage image = null;

        image = ms.drawAnimation();

        if (image != null) {
            double monsterScaleX = gp.tileSize / image.getWidth();
            double monsterScaleY = gp.tileSize / image.getHeight();
            AffineTransform at = AffineTransform.getTranslateInstance(tmpPosX, tmpPosY);

            at.scale(shadowScaleX, shadowScaleY);
            g2.drawImage(ms.shadow, at, null);

            at = AffineTransform.getTranslateInstance(ms.posX, ms.posY);
            at.scale(monsterScaleX , monsterScaleY);
            g2.drawImage(image, at, null);

            g2.setColor(Color.pink);
            g2.drawRect((int)ms.hitbox.x,(int) ms.hitbox.y, (int)ms.hitbox.width,(int)ms.hitbox.height);
        }
    }

    // Update property per frame
    public void update() {
        if (!ms.isReachTargetDistance()) {
            switch (ms.spawnSide) {
                case 0: // Spawn from up
                    ms.moveFromUp();
                    break;
                case 1: // Spawn from down
                    ms.moveFromDown();
                    break;
                case 2: // Spawn from left
                    ms.moveFromLeft();
                    break;
                case 3: // Spawn from right
                    ms.moveFromRight();
                    break;
            }
        }

        ms.setAnimations();
    }
}
