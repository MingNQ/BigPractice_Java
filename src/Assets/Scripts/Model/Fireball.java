package Model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Fireball extends Projectile {
    public Fireball() {
        super();
        speed = 10;
        maxLife = 160;
        life = maxLife;
        damage = 1;
        alive = false;

        getImage();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_00.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
