package Controller.Character;

import Model.Entity;
import java.awt.image.BufferedImage;

public class EntityController {
    // Model entity
    public Entity entity;

    public EntityController() {
        entity = new Entity();
    }

    // Set sprite animation of each direction
    public void setAnimations() {
        entity.setAnimations();
    }

    // Draw animation per frame
    public BufferedImage drawAnimation() {
        BufferedImage image = null;
        image = entity.drawAnimation();
        return image;
    }
}
