package game;

import game.logic.GameTime;
import game.ui.EndWinScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This test class will test the EndWinScreen. It will test for two main things:
 * 1) The Time label. Testing to see if it shows the actual time of death
 * 2) The Score label. Testing to see if the score is greater than 0
 */
public class EndWinScreenTest {

    private EndWinScreen ews;
    private GameTime gt;

    /**
     * Set up test environment for testing each test method using the EndWinScreen object
     */
    @BeforeEach
    public void setup(){
        ews = new EndWinScreen();
    }

    /**
     * This method tests that the proper time is being displayed.
     * Two cases will be tested:
     * 1) Time that is more than 59 seconds
     * 2) Time that is less than 1 minute
     */
    @Test
    public void createTimeLabelTest(){
        gt = new GameTime();

        // Testing for time that is more than 59 seconds
        // 67 seconds is 1 minute and 7 seconds
        gt.setSecondsPlay(67);
        assertEquals(1, gt.minute);
        assertEquals(7, gt.second);
        assertTrue(ews.createTimeLabel());

        // Testing for time that is less than 1 minute
        gt.setSecondsPlay(20);
        assertEquals(0,gt.minute);
        assertEquals(20, gt.second);
        assertTrue(ews.createTimeLabel());
    }

    /**
     * Testing that the proper score is being displayed
     * Two cases will be tested:
     * 1) When the score is 0
     * 2) When the score is greater than 0
     */
    @Test
    public void createScoreLabelTest(){
        // Testing for 0
        ews.setPlayerScore(0);
        assertFalse(ews.createScoreLabel());

        // Testing for a score > 0
        ews.setPlayerScore(50);
        assertTrue(ews.createScoreLabel());
    }

    /**
     * Testing getter function using the setter
     */
    @Test
    public void getPlayerScoreTest(){
        ews.setPlayerScore(20);
        assertEquals(20, ews.getPlayerScore());
    }
}
