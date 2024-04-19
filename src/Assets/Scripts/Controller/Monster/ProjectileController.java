package Controller.Monster;

import Model.Projectile;
import View.GameView;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ProjectileController {
    private double xDir = 4; // x direction
    private double yDir = 4; // y direction

    GameView gp;
    Random rd = new Random();
    public Projectile projectile = new Projectile();

    // Constructor
    public ProjectileController(GameView gp) {
        this.gp = gp;
        setDefaultValue();
        projectile.getImage(rd.nextInt(4));
    }

    // Set default value when call
    public void setDefaultValue() {
        spawn();
        projectile.speed = 5.5;
        setTargetDistance();

        projectile.startPosX = projectile.posX;
        projectile.startPosY = projectile.posY;

        double projectileRecPosX = projectile.posX + 14;
        double projectileRecPosY = projectile.posY + 16;
        double projectileRecW = projectile.recW - 12;
        double projectileRecH = projectile.recH - 12;
        projectile.hitbox = new Rectangle2D.Double(projectileRecPosX, projectileRecPosY, projectileRecW, projectileRecH);
    }

    // Check if projectile is out of screen
    public boolean isOutRange() {
        if (projectile.posX < -1 || projectile.posX > gp.screenWidth || projectile.posY < -1 || projectile.posY > gp.screenHeight)
            return true;
        return false;
    }

    // Set where target distance for projectile move to
    public void setTargetDistance() {
        double distance = Math.sqrt(Math.pow(projectile.posX - projectile.targetPosX, 2) + Math.pow(projectile.posY - projectile.targetPosY, 2));

        xDir = (projectile.targetPosX - projectile.posX) / distance;
        yDir = (projectile.targetPosY - projectile.posY) / distance;
    }

    // Spawn from random side
    public void spawn() {
        // Random side
        projectile.spawnSide = rd.nextInt(4);
        projectile.targetPosX = gp.playerController.player.posX;
        projectile.targetPosY = gp.playerController.player.posY;

        switch (projectile.spawnSide) {
            case 0: // Spawn from up
                projectile.direction = "down";
                projectile.posX = rd.nextDouble(gp.screenWidth);
                projectile.posY = -1;

                break;
            case 1: // Spawn from down
                projectile.direction = "up";
                projectile.posX = rd.nextInt(gp.screenWidth);
                projectile.posY = gp.screenHeight;

                break;
            case 2: // Spawn from left
                projectile.direction = "right";
                projectile.posX = -1;
                projectile.posY = rd.nextInt(gp.screenHeight);

                break;
            case 3: // Spawn from right
                projectile.direction = "left";
                projectile.posX = gp.screenWidth;
                projectile.posY = rd.nextInt(gp.screenHeight);

                break;
        }
    }

    // Draw image
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // If alive can draw and vice versa
        image = projectile.drawAnimation();
        AffineTransform at = AffineTransform.getTranslateInstance(projectile.posX, projectile.posY);
        at.scale((double)gp.tileSize / image.getWidth() , (double)gp.tileSize / image.getHeight());
        g2.drawImage(image, at, null);
    }

    // Update per frame
    public void update() {
        if (!isOutRange()) {
            projectile.posX += xDir * projectile.speed;
            projectile.hitbox.x += xDir * projectile.speed;

            projectile.posY += yDir * projectile.speed;
            projectile.hitbox.y += yDir * projectile.speed;
        }

        projectile.setAnimations();
    }
}
