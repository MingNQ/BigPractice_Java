package Controller.Projectile;

import Model.Laser;
import Model.Projectile;
import View.GameView;

import java.awt.*;

public class LaserController {
    GameView gp;
    public Projectile laser = new Laser();

    public LaserController(GameView gp) {
        this.gp = gp;
    }

    public void setDefaultValue() {
//        this.laser.state = "idle";
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {

    }
}
