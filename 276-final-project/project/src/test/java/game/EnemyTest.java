package game;

import game.logic.Enemy;
import game.logic.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * This class tests the Enemy class which inherits the Character Class. This class mainly holds the logic for how the
 * enemy tracks and attacks the player. The main method being called every tick in the game is the target method. This
 * method takes in inputted parameters and output the X and Y direction of where the enemy needs to go but does not
 * return it. The getter methods will grab the current X and Y direction of the enemy, hence we need to check if the
 * getter methods return the correct current values.
 * <p>
 * Since the target method is a void method and it constantly changes, we can not test all the iterations, but we just
 * test a couple iterations to see if it is actually working.
 *
 * @author Dennis Zhang
 */
public class EnemyTest {

    // Initialize test variables
    private Enemy enemy;
    private Hero hero;

    /** Run this method to initialize enemy class object before every test */
    @BeforeEach
    public void setUp() {
        enemy = new Enemy(50,50,20,60);
    }

    /**
     * Tests the target method to see if the inputted parameters output the correct X direction and Y direction of Enemy.
     */
    @Test
    public void target() {
        hero = new Hero(100, 40, 10, 20);
        enemy.target(hero.getX(), hero.getY(), 200, 200, hero.getType(), enemy.getSpeed());
        assertAll("Checking if enemyDirX and enemyDirY is set after the target method",
                () -> assertEquals(-1, enemy.getEnemyDirX()),
                () -> assertEquals(-1, enemy.getEnemyDirY())
        );

    }

    /**
     * Test the getter method for grabbing the X direction for enemy after several iterations.
     * Testing three different iterations  covering three different cases.
     */
    @Test
    public void getEnemyDirX() {
        hero = new Hero(25,25, 10, 20);

        enemy.target(hero.getX(), hero.getY(), 150, 390, hero.getType(), enemy.getSpeed() - 10);
        assertEquals(0, enemy.getEnemyDirX());

        enemy.target(hero.getX(),hero.getY(),40,40, hero.getType(), enemy.getSpeed());
        assertEquals(-1, enemy.getEnemyDirX());

        enemy.target(hero.getX(), hero.getY(), 5, 15, hero.getType(), enemy.getSpeed() + 20);
        assertEquals(3, enemy.getEnemyDirX());

    }

    /**
     * Test the getter method for grabbing the Y direction for enemy after several iterations.
     * Testing three different iterations  covering three different cases.
     */
    @Test
    public void getEnemyDirY() {
        hero = new Hero(300,150, 10, 20);
        enemy.target(hero.getX(),hero.getY(), 600, 420, hero.getType(), enemy.getSpeed() - 10);
        assertEquals(0, enemy.getEnemyDirY());

        enemy.target(hero.getX(), hero.getY(), 200, 50, hero.getType(), enemy.getSpeed());
        assertEquals(1, enemy.getEnemyDirY());

        enemy.target(hero.getX(), hero.getY(), 550, 800, hero.getType(), enemy.getSpeed() +20);
        assertEquals(-3, enemy.getEnemyDirY());
    }

    /**
     * Test to see if enemy type gets returned properly
     */
    @Test
    public void getType() {
        assertEquals(1, enemy.getType());
    }

}