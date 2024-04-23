package Controller.Item;

import Controller.Character.PlayerController;

public class ItemFlashController {
    private double flashCoolDown = 45 * 70; // Cooldown
    private double flashCoolDownCurr; // Counter cooldown
    private PlayerController player;

    public ItemFlashController(PlayerController player) {
        this.player = player;
        this.flashCoolDownCurr = 0;
    }

    public boolean isAvailable() {
        return flashCoolDownCurr <= 0;
    }

    public double getFlashCoolDown() {
        return flashCoolDown;
    }

    public void setFlashCoolDown(double flashCoolDown) {
        this.flashCoolDown = flashCoolDown;
    }

    public double getFlashCoolDownCurr() {
        return flashCoolDownCurr;
    }

    public void setFlashCoolDownCurr(double flashCoolDownCurr) {
        this.flashCoolDownCurr = flashCoolDownCurr;
    }

    // When player use setup time and start countdown
    public void use() {
        if (isAvailable()) {
            flashCoolDownCurr = flashCoolDown;
        }
    }

    // Countdown
    public void countDown() {
        flashCoolDownCurr -= 2;
    }
}

