package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndLoseScreen extends JPanel{

    //initialize all screen components
    private JFrame winFrame;
    private JPanel panel;

    //gameTime is created so the time can be acquired
    private GameTime gameTime = new GameTime();

    private JButton quit;
    private JLabel timeLabel;
    private  JLabel scoreLabel;
    private ImageIcon img = null;
    private JLabel background;

    /**
     * Creates the frame that holds the score and time
     * @param score this is the player's score
     */
    public void create(int score){
        // Creates new frame with loosing message
        winFrame = new JFrame("Game Over");
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setVisible(true);

        //launch game over screen image
        img = new ImageIcon("src/main/resources/res/GameOver.png");
        panel = new JPanel(new GridLayout(1,1));
        background = new JLabel(img);

        //Creates quit button to exit the game
        quit = new JButton("");

        //once button is pressed, exit the whole game
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);

            }
        });

        //Refining button attributes
        quit.setBounds(282,348,250,82);
        quit.setContentAreaFilled(false);
        quit.setBorder(null);
        quit.setBackground(Color.GREEN);

        //Time label will display the time of completion
        timeLabel = new JLabel("Time: ");
        timeLabel.setBounds(320,190,250,82);
        timeLabel.setFont(new Font("arial black", Font.BOLD, 30));
        if (gameTime.second < 10) {
            timeLabel.setText("Time: " + gameTime.minute + ":" + "0" + gameTime.second);
        }
        else {
            timeLabel.setText("Time: " + gameTime.minute + ":" + gameTime.second);
        }

        //score label will display the player's score
        scoreLabel = new JLabel();
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
