package Controller.Event;

import Model.Entity;
import Model.Item;
import View.GameView;

public class CollisionManager {
    GameView gp;

    public CollisionManager(GameView gp) {
        this.gp = gp;
    }

    public GameView getGp() {
        return gp;
    }

    public void setGp(GameView gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
//        int entityLeftPosX = entity.getPosX() + entity.getSolidArea().x;
//        int entityRightPosX = entity.getPosX() + entity.getSolidArea().x + entity.getSolidArea().width;
//        int entityTopPosY = entity.getPosY() + entity.getSolidArea().y;
//        int entityBottomPosY = entity.getPosY() + entity.getSolidArea().y + entity.getSolidArea().height;
//
//        int entityLeftCol = entityLeftPosX/gp.tileSize;
//        int entityRightCol = entityRightPosX/gp.tileSize;
//        int entityTopRow = entityTopPosY/gp.tileSize;
//        int entityBottomRow = entityBottomPosY/gp.tileSize;
//
//        int tileNum1, tileNum2;
//
//        switch (entity.getDirection()) {
//            case "up":
//                // Predict where after player move
//                entityTopRow = (entityTopPosY - entity.getSpeed()) / gp.tileSize;
//                tileNum1 = gp.getMapTileNum(entityLeftCol, entityTopRow);
//                tileNum2 = gp.getMapTileNum(entityRightCol, entityTopRow);
//
//                if (gp.getTile(tileNum1).isCollision() == true ||
//                    gp.getTile(tileNum2).isCollision() == true) {
//                    entity.setCollision(true);
//                }
//                break;
//            case "down":
//                break;
//            case "left":
//                break;
//            case "right":
//                break;
//        }
    }

    public void checkItem(Entity entity, Item item) {
//        int entityLeftPosX = entity.getPosX() + entity.getSolidArea().x;
//        int entityRightPosX = entity.getPosX() + entity.getSolidArea().x + entity.getSolidArea().width;
//        int entityTopPosY = entity.getPosY() + entity.getSolidArea().y;
//        int entityBottomPosY = entity.getPosY() + entity.getSolidArea().y + entity.getSolidArea().height;

    }
}
