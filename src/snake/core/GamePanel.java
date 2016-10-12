package snake.core;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oisín on 12/10/2016.
 */
public class GamePanel extends JPanel implements Runnable{

    static int WIDTH = 700, HEIGHT = WIDTH / 14 * 9; // Default Values
    static Dimension SIZE = new Dimension(WIDTH, HEIGHT); // Default Value

    Thread loop;
    boolean running = false, paused = false, gameOver = false;

    Image buffer;
    Graphics screenGraphics, bufferGraphics;

    // Game Variables
    Map gameMap = new Map();

    public GamePanel() {

        SIZE = Map.getMapSizeInPixels();
        WIDTH = (int)SIZE.getWidth();
        HEIGHT = (int)SIZE.getHeight();
        setPreferredSize(SIZE);

        setFocusable(true);
        requestFocus();

        addKeyListener(new Keyboard());

        start();

    }

    public void start() {

        if(loop == null) {
            loop = new Thread(this);
        }

        running = true;
        loop.start();

    }

    public void stop() {
        running = false;
        try {
            loop.join();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void run() {
        while (running) {
            update();
            try {
                loop.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
            render();
            paintScreen();
        }
    }

    public void update() {
        gameMap.update();
    }

    public void render() {
        if(buffer == null) {
            buffer = this.createImage(WIDTH, HEIGHT);
            if(buffer != null) {
                bufferGraphics = buffer.getGraphics();
            } else {
                return;
            }
        }

        bufferGraphics.setColor(Color.black);
        bufferGraphics.fillRect(0, 0, WIDTH, HEIGHT);

        if(!gameOver) {
            gameMap.render(bufferGraphics);
        }

    }

    public void paintScreen() {
        if(screenGraphics == null) {
            screenGraphics = getGraphics();
        }
        if(screenGraphics != null && buffer != null) {
            screenGraphics.drawImage(buffer, 0, 0, WIDTH, HEIGHT, null);
        }
    }

    public void loseGame() {
        gameOver = true;
    }

}
