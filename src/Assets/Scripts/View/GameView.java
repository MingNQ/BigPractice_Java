package View;

import Controller.Event.CollisionManager;
import Controller.Event.KeyHandler;
import Controller.Character.PlayerController;
import Controller.Item.ItemController;
import Controller.Item.ItemFlashController;
import Controller.Item.ItemGhostController;
import Controller.Monster.MonsterController;
import Controller.TileSet.TileManager;
import Model.Entity;
import Model.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameView extends JPanel implements Runnable {
    // CONSTANT var
    public static final long ONE_BILL = 1000000000;

    // SCREEN SETTINGS
    private final int originalTileSize = 16; // 16x16px tile
    private final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48px
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize; // WIDTH of screen = 48 * 16 = 768px
    public final int screenHeight = maxScreenRow * tileSize; // HEIGHT of screen = 48 * 12 = 576px

    // FPS of game
    private final int FPS = 60;

    // Instantiate
    private TileManager tileM = new TileManager(this);
    private KeyHandler keyH = new KeyHandler();
    private Thread gameThread;
//    private CollisionManager collisionManager = new CollisionManager(this);
    private PlayerController playerController = new PlayerController(this, keyH);
    private ItemController itemController = new ItemController(this);
    private MonsterController monsterController = new MonsterController(this);

    public GameView() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set size for screen
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Improve rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); // FOCUS on key event;
    }

    // Run game
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

//      "SLEEP" method
//    @Override
//    public void run() { // CREATE game LOOP
//
//        // DRAW per 0.01667s
//        double drawInterval = ONE_BILL / FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while(gameThread != null) {
//            // 1. UPDATE: update information
//            update(); // Call update method
//
//            // 2. DRAW: draw the screen with the updated information
//            repaint(); // Call paintComponent method
//
//            // Calculate remaining time to next frame and make THREAD sleep
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= 1000000; // Convert to milliseconds
//
//                if (remainingTime < 0)
//                    remainingTime = 0;
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    "DELTA" method
    @Override
    public void run() {
        double drawInterval = ONE_BILL / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = System.nanoTime();


            if (delta >= 1) {
                // 1. UPDATE: update information
                update(); // Call update method
                // 2. DRAW: draw the screen with the updated information
                repaint(); // Call paintComponent method
                delta--;
                drawCount++;
            }

            // Display FPS
            if (timer >= ONE_BILL) {
                playerController.countDount(); // Countdown time using item
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    // Update is called 60 per frame
    public void update() {
        playerController.update(); // Update player
        monsterController.update();

    }

    // Draw component
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // Convert graphics to graphics2D

        tileM.draw(g2); // Draw tile set background
        playerController.draw(g2); // Draw player
        itemController.draw(g2);// Draw item
        monsterController.draw(g2);

        g2.dispose(); // Release system resource that is using
    }

//    public void checkTile(Entity entity) {
//        collisionManager.checkTile(entity);
//    }

    public int getMapTileNum(int col, int row) {
        return tileM.layerMapTileFirst[col][row];
    }

    public Tile getTile(int i) {
        return tileM.tile[i];
    }
}
