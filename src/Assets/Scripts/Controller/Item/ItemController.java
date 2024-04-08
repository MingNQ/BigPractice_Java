package Controller.Item;

import Model.Item;
import View.GameView;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ItemController {
    private Item item;

    GameView gp;

    public ItemController(GameView gp) {
        this.gp = gp;
        item = new Item();
    }


    // Draw item's image
    public void draw(Graphics2D g2) {
        g2.drawImage(item.getFlashImg(), gp.screenWidth/2 - gp.tileSize + 24, gp.screenHeight - gp.tileSize - 24, gp.tileSize, gp.tileSize, null);
        g2.drawImage(item.getGhostImg(), gp.screenWidth/2 + 32, gp.screenHeight - gp.tileSize - 24, gp.tileSize, gp.tileSize, null);
        g2.drawImage(item.getBg(), gp.screenWidth/2 - gp.tileSize, gp.screenHeight - gp.tileSize * 2, gp.tileSize * 3, gp.tileSize * 2, null);
    }
}
