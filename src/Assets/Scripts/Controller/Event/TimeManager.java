package Controller.Event;

import View.GamePanel;

import java.awt.*;

public class TimeManager {
    GamePanel gp;
    private long timeToUpgrade = 10 * 70;
    private long countDown;
    private long startTime;
    private long score;
    private long timeCounterBall;
    private long timeCounterLaser;
    private long timeSpawnBall;
    private long timeSpawnLaser;

    public TimeManager(GamePanel gp) {
        this.gp = gp;
        this.startTime = System.currentTimeMillis();
        this.timeSpawnBall = 500;
        this.timeSpawnLaser = 2500;
        this.timeCounterBall = 0;
        this.timeCounterLaser = 0;
    }

    public boolean isTimeSpawnBall() {
        if (score - timeCounterBall >= timeSpawnBall) {
            timeCounterBall += timeSpawnBall;
            return true;
        }
        return false;
    }

    public boolean isTimeSpawnLaser() {
        if (score - timeCounterLaser >= timeSpawnLaser) {
            timeCounterLaser += timeSpawnLaser;
            return true;
        }
        return false;
    }

    public void update() {
        score = System.currentTimeMillis() - startTime;

        if (countDown >= timeToUpgrade && timeSpawnBall >= 0) {
            timeSpawnBall -= 100; // Decrease time spawn ball
            timeToUpgrade += 5 * 70; // Increase time to update
            countDown = 0;
        }
        countDown++;
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
