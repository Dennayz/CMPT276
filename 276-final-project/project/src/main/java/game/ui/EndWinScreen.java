package game.ui;

import game.logic.GameTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class holds everything that is shown on the "You Win" screen. Every elemental feature that exists on the
 * Win screen will be present in this class. This includes the timer that is created as an object and the in game
 * score that keep tracks of the users overall score. The user does not need to touch or modify anything in this class,
 * as the constructor method will take in the score that the player finishes the game with and reveal it onto the screen.
 * This class makes one button that is used to exit the game after the Win screen is shown.
 */
public class EndWinScreen extends JPanel {

    /**
     * Initialize variables.
     */

    private JFrame winFrame;
    private JPanel panel;
    private JLabel background;
    private JButton quit;
    private JLabel timeLabel;
    private JLabel scoreLabel;

    // FOR TESTING
    private int playerScore;

    /**
     * Creates the frame that holds the score and time.
     * @param score this is the player's score
     */
    public void create(final int score){

        // FOR TESTINGsss
        playerScore = score;

        // Initialize screens
        ImageLoader.init();

        // Creates new frame with winning message
        winFrame = new JFrame("You Won");
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setVisible(true);

        // Launch win screen image
        panel = new JPanel(new GridLayout(1, 1));
        background = new JLabel(ImageLoader.endWinScreen);

        createExitButton();

        createTimeLabel();

        createScoreLabel();

        // Add all panels to frame
        background.add(quit);
        background.add(scoreLabel);
        background.add(timeLabel);
        panel.add(background);
        winFrame.add(panel);
        winFrame.pack();
        winFrame.setLocationRelativeTo(null);
    }

    /**
     * Created for testing
     * This method creates the JLabel that displays the time.
     * @return true if method operates
     */
    public boolean createTimeLabel(){
        // Time label will display the time of completion
        timeLabel = new JLabel("Time: ");
        timeLabel.setBounds(320, 190, 250, 82);
        timeLabel.setFont(new Font("arial black", Font.BOLD, 30));
        if (GameTime.second < 10) {
            timeLabel.setText("Time: " + GameTime.minute + ":" + "0" + GameTime.second);
        }
        else {
            timeLabel.setText("Time: " + GameTime.minute + ":" + GameTime.second);
        }

        return true;

    }

    /**
     * Created for testing
     * This method creates the JLabel that displays the score.
     * @return returns true if the score is 0 and returns false if not 0
     */
    public boolean createScoreLabel(){
        scoreLabel = new JLabel();

        // Score label will display the player's score

        if(playerScore > 0){
            scoreLabel.setText("SCORE: " + playerScore);
            scoreLabel.setBounds(320,240,250,82);
            scoreLabel.setFont(new Font("arial black", Font.BOLD, 30));
            return true;
        }
        else{
            scoreLabel.setText("SCORE: NULL");
            scoreLabel.setBounds(320,240,250,82);
            scoreLabel.setFont(new Font("arial black", Font.BOLD, 30));
            return false;
        }

    }

    /**
     * Created for testing
     * This method creates a JButton to exit/quit the game.
     */
    public void createExitButton(){
        // Creates quit button to exit the game
        quit = new JButton("");

        // Once button is pressed, exit the whole game
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Exit pressed");
                System.exit(0);

            }
        });

        // Refined button attributes
        quit.setBounds(280,350,250,82);
        quit.setContentAreaFilled(false);
        quit.setBorder(null);
    }

    /**
     * Created for testing.
     * This returns the player's score
     * @return this is the player's score
     */
    public int getPlayerScore(){
        return playerScore;
    }

    /**
     * Created for testing
     * Sets the playerScore variable
     * @param score the player's score
     */
    public void setPlayerScore(int score){
        playerScore = score;
    }
}
