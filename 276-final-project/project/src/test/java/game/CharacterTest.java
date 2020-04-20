package game;

import game.logic.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * This Class is a test class that checks if all Character class methods are outputting the appropriate values. We will
 * be using JUnit5 as our API to test the methods generated.
 * <p>
 * Use assertAll to detect multiply failures at once instead of detecting one failure and ending the test right away.
 * Three different tests includes, if it equals the correct value, if it is not the same object as the actual value,
 * and for additional testing, checking if it is actually outputting the correct value by checking if it is passing
 * when entered a wrong output.
 * <p>
 * Using @BeforeEach annotation before each test, so it can prepare the test environment for each of the methods,
 * in this case, initializing the class. The @Test annotation shows that the method is used as a testing method to
 * evaluate if a certain wanted output is occurring by using a pass or fail system.
 *
 * @author Dennis Zhang
 */
public class CharacterTest {
    private Character character;

    /**
     * Run this method before each test executes
     */
    @BeforeEach
    public void myTestSetUp() {
        character = new Character(30,60,10,20);
    }

    /**
     * Testing if player's health can be set to anything and returns the inputted value
     */
    @Test
    public void setHealth() {
        character.setHealth(20);
        assertAll("Correctly setting health of player",
                () -> assertEquals(20, character.getHealth()),
                () -> assertNotSame(20.0, character.getHealth())

        );
    }

    /**
     * Testing if player's health returns the inputted value from character object
     */
    @Test
    public void getHealth() {
        assertAll("Correctly getting the health of player",
                () -> assertEquals(10, character.getHealth()),
                () -> assertNotSame(10.0, character.getHealth())
        );
    }

    /**
     * Testing if player's speed can be set to anything and returns the inputted value from character object
     */
    @Test
    public void setSpeed() {
        character.setSpeed(50);
        assertAll("Correctly setting the speed of player",
                () -> assertEquals(50, character.getSpeed()),
                () -> assertNotSame(50.0, character.getSpeed())
        );
    }

    /**
     * Testing if player's speed returns the ideal inputted value from character object
     */
    @Test
    public void getSpeed() {
        assertAll("Correctly getting the speed of player",
                () -> assertEquals(20, character.getSpeed()),
                () -> assertNotSame(20.0, character.getSpeed())
        );
    }

    /**
     * Testing if player's X position returns the ideal inputted value from character object
     */
    @Test
    public void getX() {
        assertAll("Correctly getting the X position of player",
                () -> assertEquals(30, character.getX()),
                () -> assertNotSame(30.0, character.getX())
        );
    }

    /**
     * Testing if player's Y position returns the ideal inputted value from character object
     */
    @Test
    public void getY() {
        assertAll("Correctly getting the Y position of player",
                () -> assertEquals(60, character.getY()),
                () -> assertNotSame(60.0, character.getY())
        );
    }

    /**
     * Testing if player's X position can be set to anything and returns the ideal inputted value from character object
     */
    @Test
    public void setX() {
        character.setX(0);
        assertAll("Correctly setting the X position of player",
                () -> assertEquals(0, character.getX()),
                () -> assertNotSame(0.0, character.getX())
        );
    }

    /**
     * Testing if player's Y position can be set to anything and returns the ideal inputted value from character object
     */
    @Test
    public void setY() {
        character.setY(1);
        assertAll("Correctly setting the Y position of player",
                () -> assertEquals(1, character.getY()),
                () -> assertNotSame(1.0, character.getY())
        );
    }

}