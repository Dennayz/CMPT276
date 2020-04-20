package game;

import game.logic.Enemy;
import game.logic.Hero;
import game.logic.Map;
import game.ui.Graphic;
import game.ui.Play;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * This class is for testing the Graphics class. Graphics class holds all the interactions of the UI and game logic.
 * The tests includes integrations testing and individual tests in isolation. The integration testing mainly consists
 * of testing the player's ability to move around the map and checking if the UI reflects the movement and making sure
 * the game is still functioning. The individual testing included player positioning with respect to items and certain
 * locations such as the exit zone. Once player lands on any of the items, the tests will check if all conditions are
 * met and satisfied. The purpose of testing these methods is to check if all functions classified as UI components
 * are properly interacting with the game board, the logic and game conditions.
 */
public class GraphicTest {

    // Graphic components for testing
    private int score;
    private Map map;
    private Graphic graphic;
    public static JFrame game;
    private Hero hero;
    private Enemy enemy;
    private Enemy enemyTwo;
    private Enemy hardEnemy;
    private static final int ENTITY_WIDTH = 50, ENTITY_HEIGHT = 50;

    // Detecting players key press to display proper view point
    private boolean switchDown = false;
    private boolean switchUp = false;
    private boolean switchLeft = false;
    private boolean switchRight = false;

    /**
     * Set up test environment using the objects to test each methods below
     */
    @BeforeEach
    public void setUp() {
        score = 10;
        graphic = new Graphic();
        map = new Map();

    }

    /**
     * Develop a smaller version of the game frame for testing size.
     * Tests the size of the temporary screen to see check if the size matches.
     * This basically ensures that the bigger screen, the main game frame works as well
     */
    @Test
    public void showGraphic(){
        game = new JFrame();
        game.setSize(600,400);
        game.setLocationRelativeTo(null);
        game.add(new Graphic());
        game.setVisible(true);
        game.setResizable(false);
        game.setPreferredSize(new Dimension(600,400));
        game.pack();
        assertAll("comparing Graphic size correctly  ",
                () -> assertEquals(new Dimension(600,400),game.getSize())
        );
    }

    /**
     * Test if the game over screen comes out after the game is over.
     */
    @Test
    public void testShowGameOver(){
        game = new JFrame();
        game.setSize(600,400);
        game.setLocationRelativeTo(null);
        game.add(new Graphic());
        game.setVisible(true);
        game.setResizable(false);
        game.setPreferredSize(new Dimension(600,400));
        game.pack();
        game.dispose();
    }
    /**
     * Set player and enemy one positions.
     * Test collision with player and enemy one.
     */
    @Test
    public void heroToEnemy(){
        hero = new Hero(560, 100, 10, 20);
        enemy = new Enemy(600, 100, 10, 60);
        assertAll("Correctly comparing heroToEnemy01 ",
                () -> assertEquals(true,new Rectangle(hero.getX(), hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT).intersects
                        (new Rectangle(600, 100, ENTITY_WIDTH, ENTITY_HEIGHT)))
        );
    }

    /**
     * Set player and enemy two positions.
     * Test collision with player and enemy two.
     */
    @Test
    public void heroToEnemyTwo(){
        hero = new Hero(550, 150, 10, 20);
        enemyTwo = new Enemy(600, 150, 10, 60);
        assertAll("Correctly comparing heroToEnemy02 ",
                () -> assertEquals(false,new Rectangle(enemyTwo.getX(), enemyTwo.getY(), ENTITY_WIDTH, ENTITY_HEIGHT).intersects
                        (new Rectangle(600, 100, ENTITY_WIDTH, ENTITY_HEIGHT)))
        );
    }

    /**
     * Set player and owl enemy positions.
     * Test collision with player and owl enemy.
     */
    @Test
    public void heroToHardEnemy(){
        hero = new Hero(801, 110, 10, 20);
        hardEnemy = new Enemy(750, 100, 10, 60);
        assertAll("Correctly comparing heroToHardEnemy03 ",
                () -> assertEquals(false,new Rectangle(hardEnemy.getX(), hardEnemy.getY(), ENTITY_WIDTH, ENTITY_HEIGHT).intersects
                        (new Rectangle(600, 100, ENTITY_WIDTH, ENTITY_HEIGHT)))
        );
    }

    /**
     * Simulating an DOWN key press when launching the game to test if player moves according to the key press.
     * Once player moves right, map should reflect the movement making sure the game is still running properly and all the
     * methods that check the positioning of the player is working.
     * @throws AWTException throws this exception when trying to simulate click but its not appropriate to do so yet
     * @throws InterruptedException throws this when thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity.
     */
    @Test
    public void keyPressedDown() throws AWTException, InterruptedException {
        // Class for simulating a key press
        Robot rob = new Robot();

        // Generate the game frame for a few seconds to let simulation run on the game frame
        game = new JFrame();
        game.setSize(1200,800);
        game.add(new Play());
        game.setVisible(true);
        game.setResizable(false);
        game.setPreferredSize(new Dimension(1200,800));

        // Simulate the DOWN key press
        if(!map.checkWallDown()) {
            rob.keyPress(KeyEvent.VK_S);

            // Sleep the program for three seconds to see the affect that the key simulation has done to the game
            Thread.sleep(3000);
        }

    }

    /**
     * Simulating an RIGHT key press when launching the game to test if player moves according to the key press.
     * Once player moves right, map should reflect the movement making sure the game is still running properly and all the
     * methods that check the positioning of the player is working.
     * @throws AWTException throws this exception when trying to simulate click but its not appropriate to do so yet
     * @throws InterruptedException throws this when thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity.
     */
    @Test
    public void keyPressedRight() throws AWTException, InterruptedException {
        // Class for simulating a key press
        Robot rob = new Robot();

        // Generate the game frame for a few seconds to let simulation run on the game frame
        game = new JFrame();
        game.setSize(1200,800);
        game.add(new Play());
        game.setVisible(true);
        game.setResizable(false);
        game.setPreferredSize(new Dimension(1200,800));

        // Simulate the RIGHT key press
        rob.keyPress(KeyEvent.VK_D);

        // Sleep the program for three seconds to see the affect that the key simulation has done to the game
        Thread.sleep(3000);
    }

    /**
     * Simulating an LEFT key press when launching the game to test if player moves according to the key press.
     * Once player moves left, map should reflect the movement making sure the game is still running properly and all the
     * methods that check the positioning of the player is working.
     * @throws AWTException throws this exception when trying to simulate click but its not appropriate to do so yet
     * @throws InterruptedException throws this when thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity.
     */
    @Test
    public void keyPressedLeft() throws AWTException, InterruptedException {
        // Class for simulating a key press
        Robot rob = new Robot();

        // Generate the game frame for a few seconds to let simulation run on the game frame
        game = new JFrame();
        game.setSize(1200,800);
        game.add(new Play());
        game.setVisible(true);
        game.setResizable(false);
        game.setPreferredSize(new Dimension(1200,800));

        // Simulate the LEFT key press
        rob.keyPress(KeyEvent.VK_A);

        // Sleep the program for three seconds to see the affect that the key simulation has done to the game
        Thread.sleep(3000);

    }

    /**
     * Simulating an UP key press when launching the game to test if player moves according to the key press.
     * Once player moves up, map should reflect the movement making sure the game is still running properly and all the
     * methods that check the positioning of the player is working.
     * @throws AWTException throws this exception when trying to simulate click but its not appropriate to do so yet
     * @throws InterruptedException throws this when thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity.
     */
    @Test
    public void keyPressedUp() throws AWTException, InterruptedException {
        // Class for simulating a key press
        Robot rob = new Robot();
        // Generate the game frame for a few seconds to let simulation run on the game frame
        game = new JFrame();
        game.setSize(1200,800);
        game.add(new Play());
        game.setVisible(true);
        game.setResizable(false);
        game.setPreferredSize(new Dimension(1200,800));

        // Simulate the UP key press
        rob.keyPress(KeyEvent.VK_W);

        // Sleep the program for three seconds to see the affect that the key simulation has done to the game
        Thread.sleep(3000);
    }

    /**
     * Testing player positioning with items and locations on the game board. Depending on where they are, player can
     * loose score or gain score.
     * Checks:
     * 1) If player lands on any of the buffs; buff1, buff2, buff3, or bonus reward - gains score
     * 2) If player lands on any of the two traps - loose score
     * 3) If player lands on the exit position and all buffs are collected
     */
    @Test
    public void checkForItemsAndWinState() {
        graphic.checkForItemsAndWinState();

        Hero hero = new Hero(30,30,10,20);

        // Player not on trap, should return false
        map.setPlayerRow(0);
        map.setPlayerColumn(0);
        assertFalse(map.checkTrap());

        // Player on trap 2, should return true, player looses health
        map.setPlayerRow(27);
        map.setPlayerColumn(46);
        assertTrue(map.checkTrap());
        map.setTrapTwoToFreeSpace();
        hero.setHealth(score -= 5);
        assertEquals(5,hero.getHealth());

        // Player on trap 1, should return true, player looses health
        map.setPlayerRow(20);
        map.setPlayerColumn(13);
        assertTrue(map.checkTrap());
        map.setTrapOneToFreeSpace();
        hero.setHealth(score -= 5);
        assertEquals(0,hero.getHealth());

        // Player lands on bonus reward, should return true, give 50 health
        map.setPlayerRow(2);
        map.setPlayerColumn(55);
        assertTrue(map.changeMega());
        hero.setHealth(score += 50);
        assertEquals(50, hero.getHealth());

        // Player does not land on bonus reward, return false
        map.setPlayerRow(0);
        map.setPlayerColumn(10);
        assertFalse(map.changeMega());

        // Player lands on buff 1 (cheese), return true, give 10 health
        map.setPlayerRow(11);
        map.setPlayerColumn(10);
        assertTrue(map.changeBuff1());
        hero.setHealth(score += 10);
        assertEquals(60, hero.getHealth());

        // Player does not land on buff 1, return false
        map.setPlayerColumn(0);
        map.setPlayerRow(0);
        assertFalse(map.changeBuff1());

        // Player lands on buff 2 (cheese), return true, give 10 health
        map.setPlayerRow(20);
        map.setPlayerColumn(30);
        assertTrue(map.changeBuff2());
        hero.setHealth(score += 10);
        assertEquals(70, hero.getHealth());

        // Player does not land on buff 2, return false
        map.setPlayerColumn(0);
        map.setPlayerRow(0);
        assertFalse(map.changeBuff2());

        // Player lands on buff 3 (cheese), return true, give 10 health
        map.setPlayerRow(4);
        map.setPlayerColumn(21);
        assertTrue(map.changeBuff3());
        hero.setHealth(score += 10);
        assertEquals(80, hero.getHealth());

        // Player does not land on buff 3, return false
        map.setPlayerColumn(0);
        map.setPlayerRow(0);
        assertFalse(map.changeBuff3());

        // Check if all buffs are off the board when player lands on them
        assertEquals(false, map.buff1Check());
        assertEquals(false, map.buff2Check());
        assertEquals(false, map.buff3Check());

    }

    /**
     * Once Player lands on the exit position, the map check exit method should return true meaning the player is on
     * the exit position.
     * Should show win screen if player health is greater than 0.
     */
    @Test
    public void showWinGame() {
        map.setPlayerRow(map.getRowSize() - 4);
        map.setPlayerColumn(map.getColumnSize() - 2);
        assertEquals(true, map.checkExit());
        hero = new Hero(0, 0, 10, 20);
        hero.setHealth(20);
        assertAll("Correctly showWinGame ",
                () -> assertEquals(20,hero.getHealth())
        );


    }

    /**
     * Testing if game over logic is is working when player's health is set to 0.
     * Should run the game over method which opens up a new frame to show the game over screen
     */
    @Test
    public void showGameOver() {
        hero = new Hero(0, 0, 10, 20);
        hero.setHealth(0);
        assertAll("Correctly showGameOver ",
                () -> assertEquals(0,hero.getHealth())
        );

    }

    /**
     * Testing enemies being removed from the map after game finishes.
     */
    @Test
    public void removeEnemies() {
        graphic.removeEnemies();
        assertAll("Removing all enemies off map",
                () -> assertEquals(-20, graphic.getEnemyX()),
                () -> assertEquals(-20, graphic.getEnemyY()),
                () -> assertEquals(-20, graphic.getEnemyTwoX()),
                () -> assertEquals(-20, graphic.getEnemyTwoY()),
                () -> assertEquals(-20, graphic.getHardEnemyX()),
                () -> assertEquals(-20, graphic.getHardEnemyY())
        );

    }

    /**
     * Testing player looking up image.
     * Since it is set to false, it should not be drawing it
     */
    @Test
    public void drawUp() {
        assertEquals(switchUp, graphic.drawUp());
    }

    /**
     * Testing player looking down image.
     * Since it is set to false, it should not be drawing it
     */
    @Test
    public void drawDown() {
        assertEquals(switchDown,graphic.drawDown());
    }

    /**
     * Testing player looking right image.
     * Since it is set to false, it should not be drawing it
     */
    @Test
    public void drawRight() {
        assertEquals(switchRight,graphic.drawRight());
    }

    /**
     * Testing player looking left image
     * Since it is set to false, it should not be drawing it
     */
    @Test
    public void drawLeft() {
        assertEquals(switchLeft,graphic.drawLeft());
    }

    /**
     * Manual testing one iteration of enemy chasing player since enemy's X and Y direction keeps updating.
     * This does not get coverage when testing, but is used as a reassurance that it is working.
     */
    @Test
    public void hitDetection() {
        hero = new Hero(100, 40, 10, 20);
        enemy = new Enemy(200,200,10,60);
        enemy.target(hero.getX(), hero.getY(), enemy.getX(), enemy.getY(), hero.getType(), enemy.getSpeed());
        enemy.setX(enemy.getEnemyDirX());
        enemy.setY(enemy.getEnemyDirY());
        assertAll("Checking if enemyDirX and enemyDirY is set after the target method",
                () -> assertEquals(enemy.getEnemyDirX(), enemy.getX()),
                () -> assertEquals(enemy.getEnemyDirY(), enemy.getY())
        );
    }



}