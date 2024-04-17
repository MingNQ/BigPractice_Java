package Controller.Monster;

import Model.Projectile;
import View.GameView;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ProjectileController {
    GameView gp;
    Random rd = new Random();
    public Projectile projectile = new Projectile();
    private double xDir = 3;
    private double yDir = 3;
    public ProjectileController(GameView gp) {
        this.gp = gp;
        setDefaulValue();
        projectile.getImage(rd.nextInt(4));
    }

    public void setDefaulValue() {
        projectile.posX = rd.nextDouble(gp.screenWidth);
        projectile.posY = rd.nextDouble(gp.screenHeight);
        projectile.alive = true;
        projectile.direction = "down";

        double projectileRecPosX = projectile.posX + 14;
        double projectileRecPosY = projectile.posY + 16;
        double projectileRecW = projectile.recW - 12;
        double projectileRecH = projectile.recH - 12;
        projectile.hitbox = new Rectangle2D.Double(projectileRecPosX, projectileRecPosY, projectileRecW, projectileRecH);
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (projectile.alive) {
            image = projectile.drawAnimation();
            AffineTransform at = AffineTransform.getTranslateInstance(projectile.posX, projectile.posY);
            at.scale((double)gp.tileSize / image.getWidth() , (double)gp.tileSize / image.getHeight());
            g2.drawImage(image, at, null);

            g2.setColor(Color.RED);
            g2.drawRect((int)projectile.hitbox.x, (int)projectile.hitbox.y, (int)projectile.hitbox.width, (int)projectile.hitbox.height);
        }
    }

    public void update() {
        projectile.posX += xDir;
        projectile.hitbox.x += xDir;
        if (projectile.posX > gp.screenWidth || projectile.posX < 0) {
            xDir *= -1;
            projectile.getImage(rd.nextInt(4));
        }
        projectile.posY += yDir;
        projectile.hitbox.y += yDir;
        if (projectile.posY > gp.screenHeight || projectile.posY < 0) {
            yDir *= -1;
            projectile.getImage(rd.nextInt(4));
        }

        xDir += xDir > 0 ? 0.00001 : -0.00001;
        yDir += yDir > 0 ? 0.00001 : -0.00001;

        projectile.setAnimations();
    }
}
