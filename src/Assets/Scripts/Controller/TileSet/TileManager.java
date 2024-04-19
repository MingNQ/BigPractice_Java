package Controller.TileSet;

import Model.Tile;
import View.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GameView gp;
    public Tile[] tile; // Grass, Tree, Small grass
    public int[][] layerMapTileFirst; // Store base map tile with grass and small grass

    public TileManager(GameView gp) {
        this.gp = gp;
        tile = new Tile[20];
        layerMapTileFirst = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/Map/map_01.txt");
    }

    // Upload image
    public void getTileImage() {
        try {
            // Import tile set
            // Grass
            for (int i = 0; i < 4; i++) {
                tile[i] = new Tile();
                tile[i].setImage(ImageIO.read(getClass().getResourceAsStream("/Background/TileSet/grass_0" + (i + 1) + ".png")));
            }
            // Tree
            tile[5] = new Tile();
            tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/Background/TileSet/Tree_01.png")));
            tile[5].setCollision(true);

            tile[6] = new Tile();
            tile[6].setImage(ImageIO.read(getClass().getResourceAsStream("/Background/TileSet/Shadow_tree_01.png")));
            // Small grass
            tile[7] = new Tile();
            tile[7].setImage(ImageIO.read(getClass().getResourceAsStream("/Background/TileSet/small_grass_01.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load map tile
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Read file

            int col = 0, row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    layerMapTileFirst[col][row] = num;
                    col++;
                }

                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Draw tile set
    public void draw(Graphics2D g2) {
        int col = 0, row = 0;
        int x = 0, y = 0;

        // Draw map with grass
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = layerMapTileFirst[col][row];
            g2.drawImage(tile[tileNum].getImage(), x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

        // Draw something else
//        g2.drawImage(tile[6].getImage(), -12, 0, gp.tileSize * 3, gp.tileSize * 3, null);
//        g2.drawImage(tile[5].getImage(), -24, -48, gp.tileSize * 3, gp.tileSize * 3, null);
    }

    /* MAP
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     */
}