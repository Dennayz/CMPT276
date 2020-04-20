package game;

import game.ui.Frame;

/**
 * This class initializes the game frame and starts the game. The Main class will call the Frame class as it
 * makes an object of the Frame class, so it can be called upon to create the start screen for the users to start
 * interacting with the game.
 */
public class Main {
    public static void main(String[] args) {
        /**
         * Creates the game.
         */

        Frame frame = new Frame();
        frame.create();

    }



}
