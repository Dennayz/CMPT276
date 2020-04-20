package game.ui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * This class creates the initial start screen that the user sees.
 * This will be the first thing that the user will see when running the initial game state. The class holds the base
 * frame for the game and everything starts from here. This constructor method inside this class is called in the main
 * class at the beginning. The Frame class will be created as an object in the main class and will call the create method
 * to start the first state of the game which is the start screen that the users will see first.
 */
public class Frame {
    /**
     * Creates initial frame after user starts the game for the first time
     */
    public void create(){
        JFrame frame = new JFrame("Cheezy Nibbles Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(new Start(frame));

        frame.pack();
        frame.setLocationRelativeTo(null);

    }

}
