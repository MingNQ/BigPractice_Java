package Controller.Monster;

import Model.Monster;
import View.GameView;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterController {
    public static final Random rd = new Random();

    // Monsters
    private int monNums = 5; // Number of monsters
    List<Monster> ms;
    GameView gp;
    ProjectileController projectile;

    public MonsterController(GameView gp) {
        this.gp = gp;
        ms = new ArrayList<Monster>();

        for (int i = 0; i < monNums; i++) {
            Monster tmp = new Monster();
            ms.add(tmp);
            projectile = new ProjectileController(gp, tmp);
            spawn(i);
            setDefaultValue(i);
        }
    }

    // Set default value
    public void setDefaultValue(int i) {
        Monster m = ms.get(i);

        m.setAttack(false);
        m.setTargetPosX(gp.screenWidth/2);
        m.setTargetPosY(gp.screenHeight/2);
        m.setTargetDistance(150);
        m.setStartPosX(m.getPosX());
        m.setStartPosY(m.getPosY());
        m.setAlive(false);
    }

    // Random spawn monster
    public void spawn(int i) {
        Monster m = ms.get(i);
        m.setSpawnSide(rd.nextInt(4));
        String num = rd.nextInt(4) + "";
        m.getImage("Monster_0" + num);
        m.getImageAttack("Monster_0" + num);

        switch (ms.get(i).getSpawnSide()) {
            case 0: // Spawn from up
                ms.get(i).setPosX(rd.nextInt(gp.screenWidth));
                ms.get(i).setPosY(-1);
                break;
            case 1: // Spawn from down
                ms.get(i).setPosX(rd.nextInt(gp.screenWidth));
                ms.get(i).setPosY(gp.screenHeight);
                break;
            case 2: // Spawn from left
                ms.get(i).setPosX(-1);
                ms.get(i).setPosY(rd.nextInt(gp.screenHeight));
                break;
            case 3: // Spawn from right
                ms.get(i).setPosX(gp.screenWidth);
                ms.get(i).setPosY(rd.nextInt(gp.screenHeight));
                break;
        }
    }

    // Render image on game view
    public void draw(Graphics2D g2) {
        for (int i = 0; i < monNums; i++) {
            Monster m = ms.get(i);
            int tmpPosX = m.getPosX() + 8;
            int tmpPosY = m.getPosY() + 16;
            double scaleX = gp.tileSize/m.shadow.getWidth();
            double scaleY = gp.tileSize/m.shadow.getHeight();

            BufferedImage image = null;
            if (!m.isAttack())
                image = m.drawAnimation();
            else {
                image = m.drawAttackAnimation();
                projectile.draw(g2);
            }

            AffineTransform at = AffineTransform.getTranslateInstance(tmpPosX, tmpPosY);

            at.scale(scaleX, scaleY);
            g2.drawImage(m.shadow, at, null);

            g2.drawImage(image, m.getPosX(), m.getPosY(), gp.tileSize, gp.tileSize, null);
        }
    }

    // Attack
    public void attack(int i) {
        Monster m = ms.get(i);
        m.attack();-
    }

    // Update property per frame
    public void update() {
        for (int i = 0; i < monNums; i++) {
            Monster m = ms.get(i);

            if (m.isReachTargetDistance()) {
                attack(i);
            } else {
                switch (m.getSpawnSide()) {
                    case 0: // Spawn from up
                        m.moveFromUp();
                        break;
                    case 1: // Spawn from down
                        m.moveFromDown();
                        break;
                    case 2: // Spawn from left
                        m.moveFromLeft();
                        break;
                    case 3: // Spawn from right
                        m.moveFromRight();
                        break;
                }
            }

            m.setAnimations();
        }
    }
}
