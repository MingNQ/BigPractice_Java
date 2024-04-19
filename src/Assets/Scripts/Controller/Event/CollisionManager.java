package Controller.Event;

import Model.Entity;
import View.GameView;

public class CollisionManager {
    GameView gp;

    public CollisionManager(GameView gp) {
        this.gp = gp;
    }

    public boolean checkCollide(Entity player, Entity monster) {
        return player.hitbox.intersects(monster.hitbox);
    }

}
