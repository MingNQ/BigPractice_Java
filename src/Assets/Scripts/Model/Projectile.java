package Model;

public class Projectile extends Entity {
    public Projectile() {
        super();
    }

    public void set(double posX, double posY, String direction, boolean alive) {
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
        this.alive = alive;
        this.maxLife = 400;
        this.life = this.maxLife;
    }

}
