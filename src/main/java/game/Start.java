package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JPanel{

    public static JFrame game;

    /**
     * This is the Frame that holds the game
     * @param frame gets called by Frame
     */
    public Start(final JFrame frame){

        //intialize both start and quit buttons
        JButton startButton = new JButton("");
        JButton exitButton = new JButton("");

        //give start button action to open main game frame
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game = new JFrame();

                game.setSize(1200,800);
                game.setLocationRelativeTo(null);
                game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                game.setVisible(true);
                game.add(new Play());
                game.setPreferredSize(new Dimension(1200,800));
                game.pack();

                //do not show start screen when main game is shown
                frame.dispose();
            }
        });

        //exit button closes the game
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        //start button attributes
        startButton.setBounds(280,260,250,80);
        startButton.setContentAreaFilled(false);
        startButton.setBorder(null);

        //exit button attributes
        exitButton.setBounds(280,377,250,80);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorder(null);

        //creates start screen background
        ImageIcon img = new ImageIcon("res/startScreen.png");
        JLabel background= new JLabel(img);
        background.add(startButton);
        background.add(exitButton);
        this.add(background);


    }


}
