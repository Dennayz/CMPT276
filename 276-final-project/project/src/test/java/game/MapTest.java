package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.logic.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This test class if for the class Map. This class tests almost all of the methods within the class to ensure that
 * all individual tests passes. These include single scenarios where a player lands on an item and the map checks if
 * they landed on it or not. These methods include everything on the game board including player positioning,
 * item positioning, win states, booleans to check if something is seen or landed on.
 */
public class MapTest {
    private Map map;

    /**
     * Initialize map class for testing each test methods
     */
    @BeforeEach
    public void setup(){
        map = new Map();
    }

    // CHECKING FOR WALL UP, DOWN, LEFT, AND RIGHT

    /**
     * Testing two cases for checkWallUp:
     * Case 1: Testing to see if the wall is properly detected when it is above the player
     * Case 2: Testing to see if a free space is properly detected when there is one above the player
     *  and not falsely detecting a wall when there isn't one.
     *
     *  The edges of the board are walls, so the starting position is at playerRow = 1 and playerColumn = 1
     */
    @Test
    public void checkWallUpTest() {
        // Case 1: checking if a wall is detected when there is a wall directly above
        // Initial player positioning is at playerRow = 1 and playerColumn = 1 so it should pass
        assertTrue(map.checkWallUp());

        // Moving player one row down. There is now a free space directly above
        map.setPlayerRowBoard(2);

        // Case 2: checking if the free space is detected.
        // This should pass because there isn't a wall above
        assertFalse(map.checkWallUp());
    }

    /**
     * Testing two cases for the checkWallLeft:
     * Case 1: Testing to see if the wall is properly detected when it is to the left of the player
     * Case 2: Testing to see if a free space is properly detected when there is one to the left of the player
     *  and not falsely detecting a wall when there isn't one.
     *
     *  The edges of the board are walls, so the starting position is at playerRow = 1 and playerColumn = 1
     */
    @Test
    public void checkWallLeftTest(){
        // Case 1: checking if a wall is detected when there is a wall to the left
        // Initial player positioning is at playerRow = 1 and playerColumn = 1 so it should pass
        assertTrue(map.checkWallLeft());

        // Moving player one column to the right.
        // There is now a free space to the left of the player
        map.setPlayerColumnBoard(2);

        // Case 2: checking if the free space is detected.
        // This should pass because there isn't a wall to the left
        assertFalse(map.checkWallLeft());
    }

    /**
     * Testing two cases for the checkWallRight:
     * Case 1: Testing to see if the wall is properly detected when it is to the right of the player
     * Case 2: Testing to see if a free space is properly detected when there is one to the right the player
     *  and not falsely detecting a wall when there isn't one.
     *
     *  The edges of the board are walls, so the starting position is at playerRow = 1 and playerColumn = 1
     */
    @Test
    public void checkWallRightTest(){
        // Case 2: checking if the free space is detected.
        // Initial player positioning is at playerRow = 1 and playerColumn = 1 so it should pass
        assertFalse(map.checkWallRight());

        // Moving player two columns to the right.
        // There is now a wall to the right of the player
        map.setPlayerColumnBoard(3);

        // Case 1: checking if the wall is detected.
        // This should pass because there is now a wall to the right
        assertTrue(map.checkWallRight());
    }

    /**
     * Testing two cases for the checkWallDown:
     * Case 1: Testing to see if the wall is properly detected when it is below the player
     * Case 2: Testing to see if a free space is properly detected when there is one below the player
     *  and not falsely detecting a wall when there isn't one.
     *
     *  The edges of the board are walls, so the starting position is at playerRow = 1 and playerColumn = 1
     */
    @Test
    public void checkWallDownTest() {
        // Case 2: checking if the free space is detected.
        // Initial player positioning is at playerRow = 1 and playerColumn = 1 so it should pass
        assertFalse(map.checkWallDown());

        // Moving player 5 columns to the right and 2 rows down
        // There is now a wall below the player
        map.setPlayerColumnBoard(6);
        map.setPlayerRowBoard(3);

        // Case 1: checking if the wall is detected.
        // This should pass because there is now a wall below
        assertTrue(map.checkWallDown());
    }


    /**
     * Testing two cases for the checkTrap
     * Case 1: if the trap is detected when the player is "on" it (the same coordinates)
     * Case 2: if the method doesn't falsely detect a trap or vice versa
     */
    @Test
    public void checkTrapTest() {
        // Case 2: checking for a trap when the player is on a free space
        assertFalse(map.checkTrap());

        // Changing player coordinates to a trap
        map.setPlayerRow(26);
        map.setPlayerColumn(45);

        // Case 1: checking for a trap when the player is on one
        assertTrue(map.checkTrap());
    }

    /**
     * Testing two cases for the checkExit
     * Case 1: if the exit is detected when the player is "on" it (the same coordinates)
     * Case 2: if the method doesn't falsely detect a exit or vice versa
     */
    @Test
    public void checkExitTest() {
        assertFalse(map.checkExit());

        // Changing player coordinates to exit
        map.setPlayerRow(30);
        map.setPlayerColumn(56);

        // Case 2: checking for exit when the player is on it
        assertTrue(map.checkExit());
    }

    /**
     * This tests the method showMega in two parts:
     * Part 1: showing that the specified element is a free space
     * Part 2: showing that the specified element has the mega reward
     */
    @Test
    public void showMegaTest(){
        map.hideMega();
        map.setPlayerColumn(55);
        map.setPlayerRow(2);
        int[][] board= map.getBoard();
        // First making sure that 2,55 is a free space
        // 0 corresponds to a free space
        assertEquals(0,board[map.getPlayerRow()][map.getPlayerColumn()]);

        map.showMega();

        // Proving that show mega is working
        // 6 corresponds to the mega buff
        assertEquals(6,board[map.getPlayerRow()][map.getPlayerColumn()]);
    }

    /**
     * This tests the method hideMega in two parts:
     * Part 1: showing that the specified element has the mega reward
     * Part 2: showing that the specified element is a free space
     */
    @Test
    public void hideMegaTest(){
        map.showMega();
        map.setPlayerColumn(55);
        map.setPlayerRow(2);
        int[][] board= map.getBoard();
        // First making sure that 2,55 has the mega reward
        // 6 corresponds to the mega buff
        assertEquals(6,board[map.getPlayerRow()][map.getPlayerColumn()]);

        map.hideMega();

        // Showing that hideMega works
        // 0 corresponds to a free space
        assertEquals(0,board[map.getPlayerRow()][map.getPlayerColumn()]);
    }

    /**
     * Testing changeMega in three parts:
     * Part 1: setting the player to a reward element and asserting to make sure it checks out
     * Part 2: calling the changeMega method and asserting to show that element has changed
     * Part 3: tests the method when player is on a free space
     */
    @Test
    public void changeMegaTest() {
        // Setting player to a reward
        map.setPlayerRow(2);
        map.setPlayerColumn(55);
        int[][] board= map.getBoard();
        // Making sure that the player is actually on a reward
        // 6 corresponds to the mega buff
        assertEquals(6,board[map.getPlayerRow()][map.getPlayerColumn()]);

        assertTrue(map.changeMega());

        // Showing that the spot is now all free spaces
        // 0 corresponds to a free space
        assertEquals(0,board[map.getPlayerRow()][map.getPlayerColumn()]);

        // Setting player to free space
        map.setPlayerRow(1);
        map.setPlayerColumn(1);

        // Testing to see that it returns false
        assertFalse(map.changeMega());
    }

    /**
     * Testing changeBuff1 in three parts:
     * Part 1: setting the player to a reward element and asserting to make sure it checks out
     * Part 2: calling the changeBuff1 method and asserting to show that the element has changed
     * Part 3: tests the method when player is on a free space
     */
    @Test
    public void changeBuff1Test() {
        map.setPlayerRow(11);
        map.setPlayerColumn(9);
        int[][] board= map.getBoard();

        // Making sure that the player is actually on a reward
        // 7 corresponds to Buff1
        assertEquals(7,board[map.getPlayerRow()][map.getPlayerColumn()]);

        assertTrue(map.changeBuff1());

        // Showing that the spot is now all free spaces
        // 0 corresponds to a free space
        assertEquals(0,board[map.getPlayerRow()][map.getPlayerColumn()]);

        // Setting player to a free space
        map.setPlayerRow(1);
        map.setPlayerColumn(1);

        // Testing to see that it returns false
        assertFalse(map.changeBuff1());

    }

    /**
     * Testing changeBuff2 in three parts:
     * Part 1: setting the player to a reward element and asserting to make sure it checks out
     * Part 2: calling the changeBuff2 method and asserting to show that the element has changed
     * Part 3: tests the method when player is on a free space
     */
    @Test
    public void changeBuff2() {
        map.setPlayerRow(19);
        map.setPlayerColumn(30);
        int[][] board= map.getBoard();
        // Making sure that the player is actually on a reward
        // 8 corresponds to Buff2
        assertEquals(8,board[map.getPlayerRow()][map.getPlayerColumn()]);

        map.changeBuff2();

        // Showing that the spot is now all free spaces
        // 0 corresponds to a free space
        assertEquals(0,board[map.getPlayerRow()][map.getPlayerColumn()]);

        // Setting player to a free space
        map.setPlayerRow(1);
        map.setPlayerColumn(1);

        // Testing to see that it returns false
        assertFalse(map.changeBuff2());

    }

    /**
     * Testing changeBuff3 in three parts:
     * Part 1: setting the player to a reward element and asserting to make sure it checks out
     * Part 2: calling the changeBuff3 method and asserting to show that the element has changed
     * Part 3: tests the method when player is on a free space
     */
    @Test
    public void changeBuff3() {
        map.setPlayerRow(3);
        map.setPlayerColumn(20);
        int[][] board= map.getBoard();
        // Making sure that the player is actually on a reward
        // 9 corresponds to Buff3
        assertEquals(9,board[map.getPlayerRow()][map.getPlayerColumn()]);

        map.changeBuff3();

        // Showing that the spot is now all free spaces
        // 0 corresponds to a free space
        assertEquals(0,board[map.getPlayerRow()][map.getPlayerColumn()]);

        // Setting player to a free space
        map.setPlayerRow(1);
        map.setPlayerColumn(1);

        // Testing to see that it returns false
        assertFalse(map.changeBuff3());}

    /**
     * Testing to confirm that the player and enemies are on the board and in their
     * initial specified elements.
     */
    @Test
    public void placePlayerAndEnemyTest(){
        // Testing for initial player position
        // 2 corresponds to the player
        int[][] board= map.getBoard();
        assertEquals(2, board[map.getPlayerRow()][map.getPlayerColumn()]);

        // Testing for Enemy 1
        // 4 corresponds to the enemy
        assertEquals(4, board[9][map.getColumnSize()-2]);

        // Testing for Enemy 2
        // 4 corresponds to the enemy
        assertEquals(4, board[9][1]);

        // Testing for Enemy 3
        // 4 corresponds to the enemy
        assertEquals(4, board[map.getRowSize()-2][5]);
    }

    /**
     * This tests if the buffs and traps have been successfully placed on the board
     */
    @Test
    public void placeBuffsAndTrapsTest(){
        // Testing for buff 1
        // 7 corresponds to buff 1
        int[][] board= map.getBoard();
        assertEquals(7,board[11][9]);
        assertEquals(7,board[11][10]);
        assertEquals(7,board[12][9]);
        assertEquals(7,board[12][10]);

        // Testing for buff 2
        // 8 corresponds to buff 2
        assertEquals(8,board[19][30]);
        assertEquals(8,board[19][31]);
        assertEquals(8,board[20][30]);
        assertEquals(8,board[20][31]);

        // Testing for buff 3
        // 9 corresponds to buff 3
        assertEquals(9,board[3][20]);
        assertEquals(9,board[3][21]);
        assertEquals(9,board[4][20]);
        assertEquals(9,board[4][21]);

        //Testing for trap 1
        // 5 corresponds to a trap
        assertEquals(5,board[20][12]);
        assertEquals(5,board[20][13]);
        assertEquals(5,board[21][12]);
        assertEquals(5,board[21][13]);

        //Testing for trap 2
        // 5 corresponds to a trap
        assertEquals(5,board[26][45]);
        assertEquals(5,board[26][46]);
        assertEquals(5,board[27][45]);
        assertEquals(5,board[27][46]);
    }

    /**
     * Tests to see if exit and walls have been successfully placed on the board
     */
    @Test
    public void placeWallsAndExitTest(){
        // Testing for exit
        // 3 corresponds to the exit
        int[][] board= map.getBoard();
        assertEquals(3, board[map.getRowSize()-4][map.getColumnSize()-2]);
        assertEquals(3, board[map.getRowSize()-4][map.getColumnSize()-3]);
        assertEquals(3, board[map.getRowSize()-4][map.getColumnSize()-4]);
        assertEquals(3, board[map.getRowSize()-4][map.getColumnSize()-5]);

        assertEquals(3, board[map.getRowSize()-3][map.getColumnSize()-2]);
        assertEquals(3, board[map.getRowSize()-3][map.getColumnSize()-3]);
        assertEquals(3, board[map.getRowSize()-3][map.getColumnSize()-4]);
        assertEquals(3, board[map.getRowSize()-3][map.getColumnSize()-5]);

        assertEquals(3, board[map.getRowSize()-2][map.getColumnSize()-2]);
        assertEquals(3, board[map.getRowSize()-2][map.getColumnSize()-3]);
        assertEquals(3, board[map.getRowSize()-2][map.getColumnSize()-4]);
        assertEquals(3, board[map.getRowSize()-2][map.getColumnSize()-5]);

    }

    /**
     * Checks to see if setTrapTwoToFreeSpace changes the trap spaces to free spaces
     * First checks to make sure that the trap exists, and then calls the method.
     * Check again to make sure that the previous trap spaces are now free spaces
     */
    @Test
    public void setTrapOneToFreeSpaceTest(){
        // 5 corresponds to a trap
        int[][] board= map.getBoard();
        assertEquals(5,board[20][12]);
        assertEquals(5,board[20][13]);
        assertEquals(5,board[21][12]);
        assertEquals(5,board[21][13]);

        map.setTrapOneToFreeSpace();

        // 0 corresponds to a free space
        assertEquals(0,board[20][12]);
        assertEquals(0,board[20][13]);
        assertEquals(0,board[21][12]);
        assertEquals(0,board[21][13]);
    }

    /**
     * Checks to see if setTrapTwoToFreeSpace changes the trap spaces to free spaces
     * First checks to make sure that the trap exists, and then calls the method.
     * Check again to make sure that the previous trap spaces are now free spaces
     */
    @Test
    public void setTrapTwoToFreeSpaceTest(){
        // 5 corresponds to a trap
        int[][] board= map.getBoard();
        assertEquals(5,board[26][45]);
        assertEquals(5,board[26][46]);
        assertEquals(5,board[27][45]);
        assertEquals(5,board[27][46]);

        map.setTrapTwoToFreeSpace();

        // 0 corresponds to a free space
        assertEquals(0,board[26][45]);
        assertEquals(0,board[26][46]);
        assertEquals(0,board[27][45]);
        assertEquals(0,board[27][46]);
    }


    /**
     * Checks to see if the buff's condition changes when the buff gets removed from the board
     */
    @Test
    public void buff1CheckTest(){
        // Tests that the buff is on the board
        assertTrue(map.buff1Check());

        // Places player on a buff before method call
        map.setPlayerRow(11);
        map.setPlayerColumn(9);
        map.changeBuff1();

        // Tests that the buff is off the board
        assertFalse(map.buff1Check());

    }

    /**
     * Checks to see if the buff's condition changes when the buff gets removed from the board
     */
    @Test
    public void buff2CheckTest(){
        // Tests that the buff is on the board
        assertTrue(map.buff2Check());

        // Places player on a buff before method call
        map.setPlayerRow(19);
        map.setPlayerColumn(30);
        map.changeBuff2();

        // Tests that the buff is off the board
        assertFalse(map.buff2Check());
    }

    /**
     * Checks to see if the buff's condition changes when the buff gets removed from the board
     */
    @Test
    public void buff3CheckTest() {
        // Tests that the buff is on the board
        assertTrue(map.buff3Check());

        // Places player on a buff before method call
        map.setPlayerRow(3);
        map.setPlayerColumn(20);
        map.changeBuff3();

        // Tests that the buff is off the board
        assertFalse(map.buff3Check());
    }

    /**
     * Checks to see if the bonus reward's condition changes when
     * the buff gets removed from the board
     */
    @Test
    public void checkMegaTest(){
        // Tests that the bonus reward is on the board
        assertTrue(map.checkMega());

        // Setting player to be on the bonus reward
        map.setPlayerRow(2);
        map.setPlayerColumn(55);
        map.changeMega();

        // Tests that the bonus reward is off the board
        assertFalse(map.checkMega());
    }
}
