package Controller.Monster;

import View.GameView;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ProjectileController extends MonsterController {
    public ProjectileController(GameView gp) {
        super(gp);
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (projectile.alive) {
            image = projectile.drawAnimation();
            AffineTransform at = AffineTransform.getTranslateInstance(projectile.posX, projectile.posY);
            at.scale((double)gp.tileSize / image.getWidth() , (double)gp.tileSize / image.getHeight());
            g2.drawImage(image, at, null);
        }
    }

    public void update() {
        switch (projectile.direction) {
            case "up": projectile.moveUp(); break;
            case "down": projectile.moveDown(); break;
            case "left": projectile.moveLeft(); break;
            case "right": projectile.moveRight(); break;
        }

        projectile.life--;
        if (projectile.life <= 0) {
            projectile.alive = false;
        }

        projectile.setAnimations();
    }

    public void active() {

    }
}
