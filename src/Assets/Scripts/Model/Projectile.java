package Model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Projectile extends Entity {
    public Projectile() {
        super();
    }

    public void getImage(int num) {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
