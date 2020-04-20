package game;

import game.logic.GameTime;
import game.ui.EndLoseScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This test class will test the EndLoseScreen. It will test for two main things:
 * 1) The Time label. Testing to see if it shows the actual time of death
 * 2) The Score label. Testing to see if it shows 0 as the final score
 */
public class EndLoseScreenTest {

    private EndLoseScreen els;
    private GameTime gt;

    /**
     * Set up test environment for testing each test method using the EndLooseScreen object
     */
    @BeforeEach
    public void setup(){
        els = new EndLoseScreen();
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
        // 89 seconds is 1 minute and 29 seconds
        gt.setSecondsPlay(89);

        assertEquals(1, gt.minute);
        assertEquals(29, gt.second);
        assertTrue(els.createTimeLabel());

        // Testing for time that is less than 1 minute
        gt.setSecondsPlay(45);
        assertEquals(0, gt.minute);
        assertEquals(45, gt.second);
        assertTrue(els.createTimeLabel());
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
        els.setPlayerScore(0);
        assertTrue(els.createScoreLabel());

        // Testing for a score > 0
        els.setPlayerScore(2);
        assertFalse(els.createScoreLabel());
    }

    /**
     * Testing getter
     */
    @Test
    public void getPlayerScoreTest(){
        els.setPlayerScore(89);
        assertEquals(89,els.getPlayerScore());
    }
}
