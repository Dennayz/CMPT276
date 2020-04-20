package game.logic;

import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * This class holds the in game timer that is displayed at the left corner of the game screen.
 * While it shows the generated timer, this class does not show the timer in game, but holds the logic
 * for how a game timer works. This is the behind the scenes, as how we manage to update the timer as we increase the
 * seconds and minutes checking and resetting each units, making sure it functions like an actual timer.
 * <P>
 * With this class, the player can see how much time has passed while trying to complete the game. This is also used
 * in both the EndLoseScreen class and EndWinScreen class to present the amount of ime that the user took to finish the
 * game. The player can keep track for each time they play, trying to beat their best times!
 */
public class GameTime {
    /**
     * Initialize variables.
     */

    private int secondsPlay;
    public static long minute;
    public static long second;
    private Timer time;

    /**ssss
     * Game timer that will be displayed on the game screen
     * @param label timer is set on the label and will be added to the frame
     * @return the time that it shows in game
     */
    public Timer playTime(final JLabel label){
        secondsPlay = 0;

        time= new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                secondsPlay++;
                minute = TimeUnit.SECONDS.toMinutes(secondsPlay) - (TimeUnit.SECONDS.toHours(secondsPlay) * 60);
                second = TimeUnit.SECONDS.toSeconds(secondsPlay) - (TimeUnit.SECONDS.toMinutes(secondsPlay) * 60);


                if(second < 10){
                    label.setText("TIME: " +minute + ":0" + second);
                }
                else {
                    label.setText("TIME: " + minute + ":" + second);
                }
            }
        });
        time.start();

        return time;
    }

    /**
     * Created for testing
     * This method allows for testing of time conversion
     * @param time user specified time for conversion
     */
    public void setSecondsPlay(int time){
        secondsPlay = time;

        minute = TimeUnit.SECONDS.toMinutes(secondsPlay) - (TimeUnit.SECONDS.toHours(secondsPlay) * 60);
        second = TimeUnit.SECONDS.toSeconds(secondsPlay) - (TimeUnit.SECONDS.toMinutes(secondsPlay) * 60);
    }

    // Testing Method
    public void stopTime() {
        time.stop();
    }


}
