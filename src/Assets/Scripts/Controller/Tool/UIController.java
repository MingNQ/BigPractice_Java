package Controller.Tool;

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

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        int frameX = gp.tileSize * 4;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        Color c = new Color(0, 0, 0, 210);

        g2.setColor(c);
        g2.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        // Draw outline
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(frameX+5, frameY+5, frameWidth-10, frameHeight-10, 25, 25);
    }

    public int getXForCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;

        return x;
    }

}
