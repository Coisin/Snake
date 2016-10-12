package snake.core;

import java.awt.*;

/**
 * Created by Oisín on 12/10/2016.
 */
public class Cell {

    Color cellColor;
    int x, y;

    Consumable food = null;

    public Cell(int x, int y) {
        cellColor = Color.cyan;
        this.x=x;
        this.y=y;
    }

    public void update() {
        if(food != null) {
            food.update();
        }
    }

    public void render(Graphics g) {
        g.setColor(cellColor);
        g.fillRect(x * Map.cellSize, y * Map.cellSize, Map.cellSize, Map.cellSize);
        if(food != null) {
            food.render(g, x * Map.cellSize, y * Map.cellSize);
        }
    }

    public void renderPlayer(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x * Map.cellSize, y * Map.cellSize, Map.cellSize, Map.cellSize);
    }

    public void addConsumable(Consumable consumable) {
        if(food != null) return;
        this.food = consumable;
    }

    public void destroyFood() {
        food = null;
    }

    public void consume() {
        destroyFood();
        Map.player.addCell();
    }

}
