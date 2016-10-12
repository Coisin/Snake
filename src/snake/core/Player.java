package snake.core;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Oisín on 12/10/2016.
 */
public class Player {

    boolean added = false;

    ArrayList<Cell> playerData = new ArrayList<Cell>();
    int dir = 0; // direction

    long renderTicker = 0;

    public Player() {
        playerData.add(Map.mapData[Map.ROWS / 2][Map.COLUMNS / 2]);
    }

    public void update() {
        for(int i = 0;i<playerData.size();i++) {
            playerData.get(i).update();
        }
        if(playerData.get(playerData.size() - 1).food != null) {
            playerData.get(playerData.size() - 1).consume();
        }
    }

    public boolean isMoving() {
        return Keyboard.up() || Keyboard.down() || Keyboard.left() || Keyboard.right() || dir != 0;
    }

    public void addCell() {
        added = true;
    }

    public void render(Graphics g) {
        for(int i = 0;i<playerData.size();i++) {
            playerData.get(i).renderPlayer(g);
        }
        if(System.currentTimeMillis() - renderTicker > 250) {
            traverse();
            renderTicker = System.currentTimeMillis();
        }
    }

    public void traverse() {
        Cell cell = playerData.get(playerData.size() - 1);
        Cell newCell = null;
        if(Keyboard.up()) {
            newCell = Map.mapData[clamp(cell.y, -1, Map.ROWS - 1, 0)][cell.x];
            dir = 1;
        } else if(Keyboard.down()) {
            newCell = Map.mapData[clamp(cell.y, 1, Map.ROWS - 1, 0)][cell.x];
            dir = 2;
        } else if(Keyboard.left()) {
            newCell = Map.mapData[cell.y][clamp(cell.x, -1, Map.COLUMNS - 1, 0)];
            dir = 3;
        } else if(Keyboard.right()) {
            newCell = Map.mapData[cell.y][clamp(cell.x, 1, Map.COLUMNS - 1, 0)];
            dir = 4;
        } else if(dir == 1) {
            newCell = Map.mapData[clamp(cell.y, -1, Map.ROWS - 1, 0)][cell.x];
        } else if(dir == 2) {
            newCell = Map.mapData[clamp(cell.y, 1, Map.ROWS - 1, 0)][cell.x];
        } else if(dir == 3) {
            newCell = Map.mapData[cell.y][clamp(cell.x, -1, Map.COLUMNS - 1, 0)];
        } else if(dir == 4) {
            newCell = Map.mapData[cell.y][clamp(cell.x, 1, Map.COLUMNS - 1, 0)];
        } else {
            return;
        }
        if(playerData.contains(newCell)) { // Already Contains This Cell
            // Lose Game
            Snake.panel.loseGame();
        }
        playerData.add(newCell);
        if(added) {
            added = false;
        } else {
            playerData.remove(0);
        }
    }

    public int clamp(int current, int increase, int max, int min) {
        current += increase;
        if(current > max) current = max;
        if(current < min) current = min;
        return current;
    }

}
