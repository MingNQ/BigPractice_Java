package Controller.Monster;

import Model.Monster;
import Model.Projectile.Projectile;
import View.GameView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ProjectileController {
    GameView gp;
    Monster monster;

    public ProjectileController(GameView gp, Monster monster) {
        this.gp = gp;
        this.monster = monster;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        image = monster.projectile.drawAnimation();

        g2.drawImage(image, monster.posX,- monster.posY, gp.tileSize, gp.tileSize, null);
    }

    public void update() {
        
    }

    public void active() {

    }
}
