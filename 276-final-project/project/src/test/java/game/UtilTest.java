package game;

import org.junit.jupiter.api.Test;

import game.logic.Util;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the Util class method which only includes the dist method. The dist method uses the distance formula
 * to compare and find the distance between two objects. In this case, the player X and Y coordinates are compared with
 * the enemy's X and Y coordinates. It will return a value and that value will be used to check if it is less or greater
 * than the set range that the enemy has.
 * <p>
 * The test will individually evaluate if the dist method will output the correct values when inputting them into the
 * distance formula. Using JUnit 5, we are able to use AssertAll to check different coordinate values for both player
 * and enemies at the same time. This time we will not be using @BeforeEach since there is no setup method. This is
 * because we do not need to initialize an object of the class as a result of the original method in Util Class being
 * a static method.
 *
 * @author Dennis Zhang
 */
public class UtilTest {

    /** Test to see if the inputted coordinates return the correct value */
    @Test
    public void dist() {
        assertAll("Correctly comparing player distance with enemy's",
                () -> assertEquals(22, Util.dist(10,20,30,30)),
                () -> assertEquals(608, Util.dist(0,0,600,100)),
                () -> assertEquals(492, Util.dist(300,200,500,650)),
                () -> assertEquals(1019, Util.dist(100,500,1100,300))
        );
    }
}