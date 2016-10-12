package snake.core;

import java.awt.*;

/**
 * Created by Oisín on 12/10/2016.
 */
public class Map {

    static int COLUMNS = 20, ROWS = 20;
    static int cellSize = 30;

    static Cell[][] mapData;

    static Player player;

    public Map() {
        mapData = new Cell[ROWS][COLUMNS];
        for(int row = 0;row < ROWS;row++) {
            for(int column = 0;column < COLUMNS;column++) {
                Cell mapCell = new Cell(column, row);
                mapData[row][column] = mapCell;
            }
        }

        player = new Player();

    }

    long fruitSpawnTimer = 0;

    public void update() {

        if(System.currentTimeMillis() - fruitSpawnTimer > 10000) {
            int fruitSpawnX = (int)(Math.random() * COLUMNS);
            int fruitSpawnY = (int)(Math.random() * ROWS);
            mapData[fruitSpawnY][fruitSpawnX].addConsumable(new Fruit(mapData[fruitSpawnY][fruitSpawnX]));

            fruitSpawnTimer = System.currentTimeMillis();
        }

        for(int row = 0;row < ROWS;row++) {
            for(int column = 0;column < COLUMNS;column++) {
                mapData[row][column].update();
            }
        }
        player.update();

    }

    public void render(Graphics g) {
        for(int row = 0;row < ROWS;row++) {
            for(int column = 0;column < COLUMNS;column++) {
                mapData[row][column].render(g);
            }
        }

        player.render(g);

        g.setColor(Color.black);

        for(int row = 0;row < ROWS;row++) {
            g.drawLine(0, row * cellSize, getMapWidthInPixels(), row * cellSize);
        }

        for(int column = 0;column < COLUMNS;column++) {
            g.drawLine(column * cellSize, 0, column * cellSize, getMapHeightInPixels());
        }

    }

    public static int getMapWidthInPixels() {
        return COLUMNS * cellSize;
    }

    public static int getMapHeightInPixels() {
        return ROWS * cellSize;
    }

    public static Dimension getMapSizeInPixels() {
        return new Dimension(getMapWidthInPixels(), getMapHeightInPixels());
    }

}
