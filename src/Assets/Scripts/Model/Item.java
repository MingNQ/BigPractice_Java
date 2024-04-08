package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Item {
    // Position
    private String name;
    private BufferedImage flashImg;
    private BufferedImage ghostImg;
    private BufferedImage bg;

    public Item() {
        name = "";
        setItemImage();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getFlashImg() {
        return flashImg;
    }

    public BufferedImage getGhostImg() {
        return ghostImg;
    }

    public BufferedImage getBg() {
        return bg;
    }

    public void setItemImage() {
        try {
            bg = ImageIO.read(getClass().getResourceAsStream("/Items/bg.png"));
            flashImg = ImageIO.read(getClass().getResourceAsStream("/Items/item_00.png"));
            ghostImg = ImageIO.read(getClass().getResourceAsStream("/Items/item_01.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
