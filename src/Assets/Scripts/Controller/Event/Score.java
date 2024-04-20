package Controller.Event;

import View.GameView;

import java.awt.*;

public class Score {
    GameView gp;
    private long startTime;
    private long score;
    private long timeCounter;
    private long timePivot;

    public Score(GameView gp) {
        this.gp = gp;
        this.startTime = System.currentTimeMillis();
        this.timePivot = 500;
        this.timeCounter = 0;
    }

    public boolean isPivotTime() {
        if (score - timeCounter >= timePivot) {
            timeCounter += timePivot;
            return true;
        }
        return false;
    }

    public void update() {
        score = System.currentTimeMillis() - startTime;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(new Font("Brick Sans", Font.BOLD, 30));
        g2.setColor(Color.WHITE);
        String res = score / 100 + "";
        int length = (int) g2.getFontMetrics().getStringBounds(res, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        g2.drawString(res, x, 50);
    }
}
