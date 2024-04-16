package Controller.Item;

import Controller.Character.PlayerController;

public class ItemGhostController {
    private double ghostCoolDown = 30; // Cooldown
    private double ghostCoolDownCurr; // Counter cooldown
    private double ghostTimeUse = 9; // Time using
    private double ghostTimeUseCurr = -1; // Counter time using
    private boolean timeOut = true;
    private PlayerController player;

    public ItemGhostController(PlayerController player) {
        this.player = player;
        this.ghostCoolDownCurr = 0;
    }

    public double getGhostCoolDown() {
        return ghostCoolDown;
    }

    public void setGhostCoolDown(double ghostCoolDown) {
        this.ghostCoolDown = ghostCoolDown;
    }

    public double getGhostCoolDownCurr() {
        return ghostCoolDownCurr;
    }

    public void setGhostCoolDownCurr(double ghostCoolDownCurr) {
        this.ghostCoolDownCurr = ghostCoolDownCurr;
    }

    public double getGhostTimeUse() {
        return ghostTimeUse;
    }

    public void setGhostTimeUse(double ghostTimeUse) {
        this.ghostTimeUse = ghostTimeUse;
    }

    public double getGhostTimeUseCurr() {
        return ghostTimeUseCurr;
    }

    public void setGhostTimeUseCurr(double ghostTimeUseCurr) {
        this.ghostTimeUseCurr = ghostTimeUseCurr;
    }

    public boolean isTimeOut() {
        return timeOut;
    }

    public void setTimeOut(boolean timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isAvailable() {
        return ghostCoolDownCurr <= 0;
    }

    // When player use setup time and start countdown
    public void use() {
        if (isAvailable() && player.isUsed) {
            ghostCoolDownCurr = ghostCoolDown;
            ghostTimeUseCurr = ghostTimeUse;
        }
    }

    // Countdown
    public void countDown() {
        if (ghostTimeUseCurr > 0) {
            ghostTimeUseCurr -= 2;
            timeOut = false;
        } else if (ghostTimeUseCurr < 0) {
            timeOut = true;
        }
        ghostCoolDownCurr -= 2;
//        System.out.println("Ghost: " + ghostTimeUseCurr + " " + ghostCoolDownCurr);
    }
}
