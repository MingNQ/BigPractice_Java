package View;

import Controller.Tool.SoundManager;

import javax.swing.*;

public class Game extends JFrame {
    public int screenWidth = 768;
    public int screenHeight = 576;

    private GamePanel gamePanel;
    private StartScreen startScreen;
    private SelectChamp selectScreen;
    private OptionScreen optionScreen;
    private GameOverScreen gameOverScreen;
    private SoundManager music;

    public Game() {
        gamePanel = new GamePanel(this);
        startScreen = new StartScreen(this);
        selectScreen = new SelectChamp(this);
        optionScreen = new OptionScreen(this);
        music = new SoundManager();
        playMusic(0);
        this.setTitle("Dodge Game"); // Set title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// CLOSE window and END run time
        this.setResizable(false); // Against resize window

        this.add(startScreen); // Add panel to main window
        this.pack(); // Causes window to be sized to fit the preferred size
        this.setLocationRelativeTo(null); // Display on center
        this.setVisible(true);
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    public void switchToGamePanel() {
        if (gamePanel.gameThread == null) {
            gamePanel = new GamePanel(this);
        }
        gamePanel.playerController.player.getPlayerImage("Character_0" + selectScreen.getCharacterNum());
        stopMusic();
        this.setContentPane(gamePanel);
        gamePanel.startGameThread(); // Start game loop
        gamePanel.requestFocus();
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
        playMusic(0);
        this.setContentPane(startScreen);
        this.revalidate();
    }

    public void switchToGameOver() {
        gameOverScreen = new GameOverScreen(this);
        gamePanel.gameThread = null;
        this.setContentPane(gameOverScreen);
        this.revalidate();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }
}