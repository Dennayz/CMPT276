package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndWinScreen extends JPanel {
    private JFrame winFrame;
    private JPanel panel;

    //gameTime is created so the time can be acquired
    private GameTime gameTime = new GameTime();

    /**
     * Creates the frame that holds the score and time
     * @param score this is the player's score
     */
    public void create(int score){
        // Creates new frame with winning message
        winFrame = new JFrame("You Won");
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setVisible(true);

        //launch win screen image
        ImageIcon img = new ImageIcon("res/winScreen.png");
        panel = new JPanel(new GridLayout(1,1));
        JLabel background = new JLabel(img);

        //initialize quit button
        JButton quit = new JButton("");

        //exit game after pressed
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);

            }
        });

        //Refined button attributes
        quit.setBounds(280,350,250,82);
        quit.setContentAreaFilled(true);
        quit.setBorder(null);

        //Time label will display the time of completion
        JLabel timeLabel = new JLabel("Time: ");
        timeLabel.setBounds(320,190,250,82);
        timeLabel.setFont(new Font("arial black", Font.BOLD, 30));
        if (gameTime.second < 10) {
            timeLabel.setText("Time: " + gameTime.minute + ":" + "0" + gameTime.second);
        }
        else {
            timeLabel.setText("Time: " + gameTime.minute + ":" + gameTime.second);
        }

        //score label will display the player's score
        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("SCORE: " + score);
        scoreLabel.setBounds(320,240,250,82);
        scoreLabel.setFont(new Font("arial black", Font.BOLD, 30));

        //add all panels to frame
        background.add(quit);
        background.add(scoreLabel);
        background.add(timeLabel);
        panel.add(background);
        winFrame.add(panel);
        winFrame.pack();
        winFrame.setLocationRelativeTo(null);
    }



}
