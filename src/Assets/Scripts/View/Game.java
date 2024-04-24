package View;

import javax.swing.*;

public class Game extends JFrame {
    public int screenWidth = 768;
    public int screenHeight = 576;

    private GamePanel gamePanel;
    private StartScreen startScreen;
    private SelectChamp selectScreen;
    private OptionScreen optionScreen;
    private GameOverScreen gameOverScreen;

    public Game() {
        gamePanel = new GamePanel(this);
        startScreen = new StartScreen(this);
        selectScreen = new SelectChamp(this);
        optionScreen = new OptionScreen(this);
        gameOverScreen = new GameOverScreen(this);

        this.setTitle("Dodge Game"); // Set title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// CLOSE window and END run time
        this.setResizable(false); // Against resize window

        this.add(startScreen); // Add panel to main window
        this.pack(); // Causes window to be sized to fit the preferred size
        this.setLocationRelativeTo(null); // Display on center
        this.setVisible(true);
    }

    public void switchToGamePanel() {
        this.setContentPane(gamePanel);
        gamePanel.startGameThread(); // Start game loop
        this.revalidate();
    }

    public void switchToSelect() {
        this.setContentPane(selectScreen);
        this.revalidate();
    }

    public void switchToOption() {
        this.setContentPane(optionScreen);
        this.revalidate();
    }

    public void switchToStart() {
        this.setContentPane(startScreen);
        this.revalidate();
    }

    public void switchToGameOver() {
        this.setContentPane(gameOverScreen);
        this.revalidate();
    }
}