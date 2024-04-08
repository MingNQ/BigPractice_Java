package Application;

import View.GameView;

import javax.swing.*;

public class Application {
    public static void run() {
        JFrame window = new JFrame();
        window.setTitle("Game"); // Set title
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CLOSE window and END run time
        window.setResizable(false); // Against resize window

        GameView gameView = new GameView();

        window.add(gameView); // Add panel to main window
        window.pack(); // Causes window to be sized to fit the preferred size
        gameView.startGameThread(); // Start game loop

        window.setLocationRelativeTo(null); // Display on center
        window.setVisible(true);
    }
}
