package snake.core;

import java.awt.*;

/**
 * Created by Oisín on 12/10/2016.
 */
public abstract class Consumable {
    Color color;
    Cell parentCell;

    long despawnTimer;

    public Consumable(Color color, Cell cell) {
        this.color = color;
        parentCell = cell;

        despawnTimer = System.currentTimeMillis();
    }

    public void update() {
        if(System.currentTimeMillis() - despawnTimer > 20000) {
            parentCell.destroyFood();
        }
    }
    public void render(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, Map.cellSize, Map.cellSize);
    }
}
