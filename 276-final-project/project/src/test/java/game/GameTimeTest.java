package game;

import game.logic.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JLabel;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class tests two things about the GameTime class:
 * 1) If the secondsPLay variable is properly getting converted into minutes and seconds
 * 2) The functionality of the timer. Whether it displays the correct time after being given
 * a time to run on
 */
public class GameTimeTest {

    private GameTime gt;

    /**
     * Set up test environment for each tests by initializing game time class
     */
    @BeforeEach
    public void setup(){
        gt = new GameTime();
    }


    /**
     * This tests whether the correct time is being converted properly.
     * Testing for when time is under 59 seconds and when time is above 1 minute
     */
    @Test
    public void setSecondsPlayTest() {
        // Testing for time under 59 seconds
        gt.setSecondsPlay(35);
        assertEquals(0,GameTime.minute);
        assertEquals(35,GameTime.second);

        // Testing for time above 1 minute
        // 200 seconds is 3 minutes and 20 seconds
        gt.setSecondsPlay(200);
        assertEquals(3,GameTime.minute);
        assertEquals(20,GameTime.second);
    }


    /**
     * Tests whether the timer functionality of GameTime works as intended
     */
    @Test
    public void timeTest() {
        JLabel testLabel = new JLabel();
        gt.playTime(testLabel);

        // Stopping the timer after 0 minute and 5 seconds
        // The delay in the timer initialization accounts for the second lost,
        // which is why it is set to 71000
        try {
            Thread.sleep(6000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        gt.stopTime();

        // Making sure the timer values are the correct values
        assertEquals(0, GameTime.minute);
        assertEquals(5, GameTime.second);
    }
}
