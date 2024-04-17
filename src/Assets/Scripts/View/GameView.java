package View;

import Controller.Event.CollisionManager;
import Controller.Event.KeyHandler;
import Controller.Character.PlayerController;
import Controller.Item.ItemController;
import Controller.Monster.MonsterController;
import Controller.Monster.MonsterPool;
import Controller.Monster.ProjectileController;
import Controller.TileSet.TileManager;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

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

    // Instantiate
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    public Thread gameThread;

//    public AssetsSetup asset = new AssetsSetup(this);
    public CollisionManager collisionManager = new CollisionManager(this);
    public PlayerController playerController = new PlayerController(this, keyH);
    public ItemController itemController = new ItemController(this);
    public ProjectileController projectileController = new ProjectileController(this);
    public ArrayList<MonsterController> monsterList = new ArrayList<>();
    public MonsterPool monsterPool = new MonsterPool(this);

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

//    "DELTA" method
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

    // Set up default entity
    public void setUpGame() {
        for (int i = 0; i < 5; i++) {
            monsterList.add(monsterPool.getMonster());
        }
    }

    // Update is called 60 per frame
    public void update() {
        playerController.update(); // Update player

        projectileController.update(); // Update projectile

        // Check if collide
        if (collisionManager.checkCollide(playerController.player, projectileController.projectile))
            System.exit(1);

        // Update monster
        if (monsterList.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                monsterList.add(monsterPool.getMonster());
            }
        }

        for (MonsterController mons : monsterList) {
            mons.update();
            if (collisionManager.checkCollide(playerController.player, mons.ms)) {
                System.exit(1);
            }
            if (!mons.ms.alive) {
                mons.setDefaultValue();
            }
        }
    }

    // Draw component
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // Convert graphics to graphics2D

        tileM.draw(g2); // Draw tile set background
        playerController.draw(g2); // Draw player
        itemController.draw(g2);// Draw item
        projectileController.draw(g2); // Draw projectile

        // Draw monster
        for (MonsterController mons : monsterList) {
            mons.draw(g2);
            if (!mons.ms.alive) {
                mons.setDefaultValue();
            }
        }

        g2.dispose(); // Release system resource that is using
    }
}
