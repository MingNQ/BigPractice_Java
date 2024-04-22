package Controller.UI;

import View.GamePanel;

import java.awt.*;

public class UIController {
    GamePanel gp;
    Graphics2D g2;

    Font brickSans_40;

    public UIController(GamePanel gp) {
        this.gp = gp;

        // Set Font
        brickSans_40 = new Font("Brick Sans", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(brickSans_40);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.playState) {

        }
    }

    public void drawPauseScreen() {
        String text = "PAUSED";

        int x = getXForCenterText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXForCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;

        return x;
    }

}
