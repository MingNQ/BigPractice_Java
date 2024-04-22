package Application;

import View.GamePanel;

import javax.swing.*;

public class Application {
    public static void run() {
        try {
            System.setProperty("sun.java2d.opengl", "True");

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            JFrame window = new JFrame();
            window.setTitle("Game"); // Set title
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CLOSE window and END run time
            window.setResizable(false); // Against resize window

            GamePanel gamePanel = new GamePanel();

            window.add(gamePanel); // Add panel to main window
            window.pack(); // Causes window to be sized to fit the preferred size
            gamePanel.startGameThread(); // Start game loop

            window.setLocationRelativeTo(null); // Display on center
            window.setVisible(true);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
