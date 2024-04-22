package View;

import Controller.Character.PlayerController;
import Controller.Event.CollisionManager;
import Controller.Event.KeyHandler;
import Controller.Event.Score;
import Controller.Item.ItemController;
import Controller.Projectile.BallPool;
import Controller.Projectile.BallController;
import Controller.Projectile.LaserController;
import Controller.TileSet.TileManager;
import Controller.UI.UIController;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class GamePanel extends JPanel implements Runnable {
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

    // Instantiate
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public Thread gameThread;
    public CollisionManager collisionManager = new CollisionManager(this);
    public PlayerController playerController = new PlayerController(this, keyH);
    public ItemController itemController = new ItemController(this);
    public LinkedList<BallController> projectileList = new LinkedList<>();
    public BallPool projectilePool = new BallPool(this);
    public Score score = new Score(this);

    public LaserController laserController = new LaserController(this);

    // Game state
    public int gameState;
    public final int playState = 1;
    public final int gameEndState = 2;

    // UI
    public UIController ui = new UIController(this);

    public GamePanel() {
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

    // "DELTA" method loop game
    @Override
    public void run() {
        // FPS of game
        int FPS = 60;
        double drawInterval = (double) ONE_BILL / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        setUpGame();
        while (gameThread != null) {
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

    // Set up default entity
    public void setUpGame() {
        gameState = playState;
        projectileList.add(projectilePool.getBall());
    }

    // Update is called 60 per frame
    public void update() {
        // Check if state is playing
        if (gameState == playState) {
            score.update(); // Update time and score
            playerController.update(); // Update player

            // Update Ball Projectile
//            if (canSpawn()) {
//                projectileList.add(projectilePool.getBall());
//            }
//            Iterator<BallController> it = projectileList.iterator();
//
//            while (it.hasNext()) {
//                BallController curr = it.next();
//                curr.update();
//
//                if (curr.isOutRange()) {
//                    it.remove();
//                    projectilePool.returnBall(curr);
//                }
//
//                // Check if collide
//                if (collisionManager.checkCollide(playerController.player, curr.projectile))
//                    System.exit(1);
//            }

            // Update Laser Projectile
            laserController.update();
//            if (laserController.satCollision(laserController.projectile.hitbox, laserController.angle, playerController.player.hitbox, 0)) {
//                System.out.println("Collide");
//            }
        }
    }

    // Draw component
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // Convert graphics to graphics2D

        tileM.draw(g2); // Draw tile set background
        // Draw Laser Projectile
        laserController.draw(g2);

        playerController.draw(g2); // Draw player
        // Draw Ball Projectile
//        for (BallController pt : projectileList) {
//            pt.draw(g2);
//        }


        itemController.draw(g2);// Draw item
        score.draw(g2); // Draw score
        ui.draw(g2);
        g2.dispose(); // Release system resource that is using
    }

    // Check if ball can spawn
    public boolean canSpawn() {
        return score.isPivotTime();
    }
}