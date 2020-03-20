package game;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

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
