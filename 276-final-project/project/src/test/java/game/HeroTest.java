package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.logic.Hero;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * This class tests the Hero class which inherits the Character Class. This class mainly holds the logic for how the
 * hero moves on the map. The methods are constantly being updated and called when the player moves around the map using
 * the KeyPressed method in the Graphics class. Essentially we are testing if the values are increasing or decreasing
 * along the X and Y axis. This means that the player is travelling forward or backwards using speed to update the
 * player's current position.
 * <p>
 * Initial setup will use the @BeforeEach api to initialize the hero class object for each tests. Each test method
 * will check the X and Y values of the player corresponding to the instances of the object when called. If the methods
 * are done correctly, they should be decreasing in value for X and Y when the moveUp and moveLeft methods are called
 * and increasing in value for X and Y when moveDown and moveRight are called.
 *
 * @author Dennis Zhang
 */
public class HeroTest {

    // Initialize hero instance
    private Hero hero;

    /** Set up test environment using the hero object to test each methods below */
    @BeforeEach
    public void setUp() {
        hero = new Hero(50, 50, 10, 20);
    }

    /** Check if moving up on the map decreases player Y position meaning the player is moving upwards. */
    @Test
    public void moveUp() {
        hero.moveUp();
        assertEquals(30, hero.getY());
    }

    /** Check if moving down on the map increases player Y position meaning the player is moving downwards. */
    @Test
    public void moveDown() {
        hero.moveDown();
        assertEquals(70, hero.getY());
    }

    /** Check if moving left on the map decreases player X position meaning the player is moving leftwards. */
    @Test
    public void moveLeft() {
        hero.moveLeft();
        assertEquals(30, hero.getX());
    }

    /** Check if moving right on the map increases player X position meaning the player is moving rightwards. */
    @Test
    public void moveRight() {
        hero.moveRight();
        assertEquals(70, hero.getX());
    }

    /** Test if method recognizes player ID tag */
    @Test
    public void getType() {
        assertEquals(2, hero.getType());
    }
}