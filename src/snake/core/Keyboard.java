package snake.core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Oisín on 12/10/2016.
 */
public class Keyboard extends KeyAdapter {
    // A = 37, S = 40, D = 39, W = 38
    static boolean[] keys = new boolean[210];
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    public static boolean up() {
        return keys[38];
    }

    public static boolean down() {
        return keys[40];
    }

    public static boolean left() {
        return keys[37];
    }

    public static boolean right() {
        return keys[39];
    }
}
