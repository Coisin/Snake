package snake.core;

import javax.swing.*;

/**
 * Created by Oisín on 12/10/2016.
 */
public class Snake extends JFrame {

    static Snake snake = new Snake();
    static GamePanel panel = new GamePanel();

    public static void main(String args[]) {

        //Add To Frame
        snake.add(panel);

        snake.pack();

        snake.setTitle("Snake");
        snake.setLocationRelativeTo(null);
        snake.setVisible(true);
        snake.setDefaultCloseOperation(snake.EXIT_ON_CLOSE);
    }

}
