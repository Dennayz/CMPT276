package game.logic;

/**
 * The purpose of this class creates the game board on a 2-dimensional array placing the game pieces to their
 * initial positions. The class holds the backend structure of the game that is not seen on the user interface side
 * but acts as the stronghold that builds the game together. Everything inside the game is implanted onto the game
 * board with considerations with all other features. Methods includes setting up game pieces including, X, Y positions
 * of the boarders, walls, enemies spawned positions, player's spawning point and many more. While the other methods are
 * continually checking if those pieces are still present on the game board.
 * <p>
 * As the game goes on, the game components change, as in if a player lands on a trap or a reward, the game board
 * needs to recognize that and remove it or in this case, set it as a "FREE SPACE" so the player cannot go on it again.
 * The game board constantly changes, detecting each iteration of the game and ticks when the player moves checking
 * if there exists a wall on either side or above and below the player. The game board also creates an exit point
 * placing it on the opposite side of the player using the 2-dimensional array as our map creator. These are all
 * the functionalities that the Map class handles.
 */
public class Map {

    // Column and row size;
    private final int rowSize = 34;
    private final int columnSize = 60;

    // Board variables

    //private int board[][];



    private int board[][];

    private final int wall = 1;
    private final int freeSpace = 0;
    private final int exit = 3;
    private final int enemy = 4;
    private final int trap = 5;

    // FOR TESTING
    protected int board2[][];
    private boolean buff1Check;
    private boolean buff2Check;
    private boolean buff3Check;


    // Display bonus reward
    private final int megaBuff = 6;
    private boolean megaBuffCheck = true;

    // Attributes for detecting rewards
    private final int buff1 = 7;
    //    private boolean buff1Check = true;
    private final int buff2 = 8;
    //    private boolean buff2Check = true;
    private final int buff3 = 9;
//    private boolean buff3Check = true;

    // Player's position
    private final int player = 2;
    private int playerRow;
    private int playerColumn;

    public Map() {
        createMap();
    }

    public int[][] getBoard() {
        return board;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////GETTERS AND SETTERS///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Grabs the player's row position on the game board
     * @return Player's Row position
     */
    public int getPlayerRow() {
        return playerRow;
    }

    /**
     * Grabs the player's column position on the game board
     * @return Player's Column position
     */
    public int getPlayerColumn() {
        return playerColumn;
    }

    /**
     * Checks if the element directly above the current element is a WALL
     * @return True if the specified element is a WALL, false if element is not a WALL and decreases playerRow
     */
    public boolean checkWallUp() {
        if (board[getPlayerRow() - 1][getPlayerColumn()] == wall) {
            return true;
        } else {
            playerRow--;
            return false;
        }
    }

    /**
     * Checks if the element directly below the current element is a WALL
     * @return True if the specified element is a WALL, false if element is not a WALL and increases playerRow
     */
    public boolean checkWallDown() {
        if (board[getPlayerRow() + 1][getPlayerColumn()] == wall) {
            return true;
        } else {
            playerRow++;
            return false;
        }
    }

    /**
     * Checks if the element directly to the left of current element is a WALL
     * @return True if the specified element is a WALL, false if element is not a WALL and decreases playerColumn
     */
    public boolean checkWallLeft() {
        if (board[getPlayerRow()][getPlayerColumn() - 1] == wall) {
            return true;
        } else {
            playerColumn--;
            return false;
        }
    }

    /**
     * Checks if the element directly to the right of current element is a WALL
     * @return True if the specified element is a WALL, false if element is not a WALL and increases playerColumn
     */
    public boolean checkWallRight() {
        if (board[getPlayerRow()][getPlayerColumn() + 1] == wall) {
            return true;
        } else {
            playerColumn++;
            return false;
        }
    }

    /**
     * Checks to see if the player's current position on the grid is a TRAP
     * @return True if the current position is a TRAP, false if it is not a TRAP
     */
    public boolean checkTrap() {
        if (board[getPlayerRow()][getPlayerColumn()] == trap) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to see if the player's current position on the grid is a EXIT
     * @return True if the current position is a EXIT, false if it is not an EXIT
     */
    public boolean checkExit() {
        if (board[getPlayerRow()][getPlayerColumn()] == exit) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Displays the bonus reward onto the game board
     */
    public void showMega() {
        board[2][55] = megaBuff;
        board[2][56] = megaBuff;
        board[3][55] = megaBuff;
        board[3][56] = megaBuff;

    }

    /**
     * Hides the bonus reward from the player and game board
     */
    public void hideMega() {
        board[2][55] = freeSpace;
        board[2][56] = freeSpace;
        board[3][55] = freeSpace;
        board[3][56] = freeSpace;

    }

    /**
     * Hides the bonus reward when the player lands on it
     * @return True if player lands on buff, false if player did not land on buff
     */
    public boolean changeMega() {
        if (board[playerRow][playerColumn] == megaBuff) {
            megaBuffCheck = false;

            board[2][55] = freeSpace;
            board[2][56] = freeSpace;
            board[3][55] = freeSpace;
            board[3][56] = freeSpace;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the bonus reward is still there
     * @return True if it is, false if it is not
     */
    public boolean checkMega() {
        return megaBuffCheck;
    }

    /**
     * Reset the first reward properties to a free space if player lands on it
     * @return True if player has landed on it, false if player did not land on it
     */
    public boolean changeBuff1() {
        if (board[playerRow][playerColumn] == buff1) {
            buff1Check = false;
            board[11][9] = 0;
            board[11][10] = 0;
            board[12][9] = 0;
            board[12][10] = 0;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Reset the second reward properties to a free space if player lands on it
     * @return True if player has landed on it, false if player has not landed on it
     */
    public boolean changeBuff2() {
        if (board[playerRow][playerColumn] == buff2) {
            buff2Check = false;
            board[19][30] = 0;
            board[19][31] = 0;
            board[20][30] = 0;
            board[20][31] = 0;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Reset the third reward properties to a free space if player lands on it
     * @return True if player has landed on it, false if player has not landed on it
     */
    public boolean changeBuff3() {
        if (board[playerRow][playerColumn] == buff3) {
            buff3Check = false;
            board[3][20] = 0;
            board[3][21] = 0;
            board[4][20] = 0;
            board[4][21] = 0;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks constantly if the first reward is on the map
     * @return True if it is, false if it is not
     */
    public boolean buff1Check() {
        return buff1Check;
    }

    /**
     * Checks constantly if the second reward is on the map
     * @return True if it is, false if it is not
     */
    public boolean buff2Check() {
        return buff2Check;
    }

    /**
     * Checks constantly if the third reward is on the map
     * @return True if it is, false if it is not
     */
    public boolean buff3Check() {
        return buff3Check;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////GETTERS AND SETTERS END///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Setting up player and enemy positions on the board
     * Separates all enemy and player's distances away from each other at the beginning
     */
    public void placePlayerAndEnemy() {
        // PLAYER STARTING POSITION
        playerRow = 1;
        playerColumn = 1;
        board[playerRow][playerColumn] = player;


        /**
         * ENEMIES
         */
        // Enemy 1
        board[9][columnSize - 2] = enemy;
        // Enemy 2
        board[9][1] = enemy;
        // Enemy 3
        board[rowSize - 2][5] = enemy;
    }

    /**
     * Sets up all walls and exit point onto the game board
     */
    public void placeWallsAndExit() {
        // EXIT 3 high 4 wide
        board[rowSize - 4][columnSize - 2] = exit;
        board[rowSize - 4][columnSize - 3] = exit;
        board[rowSize - 4][columnSize - 4] = exit;
        board[rowSize - 4][columnSize - 5] = exit;

        board[rowSize - 3][columnSize - 2] = exit;
        board[rowSize - 3][columnSize - 3] = exit;
        board[rowSize - 3][columnSize - 4] = exit;
        board[rowSize - 3][columnSize - 5] = exit;

        board[rowSize - 2][columnSize - 2] = exit;
        board[rowSize - 2][columnSize - 3] = exit;
        board[rowSize - 2][columnSize - 4] = exit;
        board[rowSize - 2][columnSize - 5] = exit;


        for (int i = 0; i < 8; i++) {
            board[1 + i][4] = wall;
        }
        for (int i = 0; i < 8; i++) {
            board[1 + i][5] = wall;
        }

        for (int i = 0; i < 10; i++) {
            board[15 + i][20] = wall;
        }
        for (int i = 0; i < 10; i++) {
            board[15 + i][21] = wall;
        }

        for (int i = 0; i < 10; i++) {
            board[15][22 + i] = wall;
        }
        for (int i = 0; i < 10; i++) {
            board[16][22 + i] = wall;
        }

        for (int i = 0; i < 10; i++) {
            board[27][1 + i] = wall;
        }
        for (int i = 0; i < 10; i++) {
            board[28][1 + i] = wall;
        }


        for (int i = 0; i < 19; i++) {
            board[5][40 + i] = wall;
        }
        for (int i = 0; i < 19; i++) {
            board[6][40 + i] = wall;
        }

        for (int i = 0; i < 10; i++) {
            board[33 - i][39] = wall;
        }
        for (int i = 0; i < 10; i++) {
            board[33 - i][40] = wall;
        }

        for (int i = 0; i < 10; i++) {
            board[7 + i][47] = wall;
        }
        for (int i = 0; i < 10; i++) {
            board[7 + i][48] = wall;
        }

        for (int i = 0; i < 10; i++) {
            board[4][6 + i] = wall;
        }
        for (int i = 0; i < 10; i++) {
            board[5][6 + i] = wall;
        }


    }

    /**
     * Sets up traps and rewards onto the game board
     */
    public void placeBuffsAndTraps() {
        /**
         * BUFFS
         */
        board[11][9] = buff1;
        board[11][10] = buff1;
        board[12][9] = buff1;
        board[12][10] = buff1;
        buff1Check = true; // FOR TESTING

        board[19][30] = buff2;
        board[19][31] = buff2;
        board[20][30] = buff2;
        board[20][31] = buff2;
        buff2Check = true; // FOR TESTING

        board[3][20] = buff3;
        board[3][21] = buff3;
        board[4][20] = buff3;
        board[4][21] = buff3;
        buff3Check = true; // FOR TESTING


        /**
         * TRAPS
         */
        // Trap 1
        board[20][12] = trap;
        board[20][13] = trap;
        board[21][12] = trap;
        board[21][13] = trap;

        // Trap 2
        board[26][45] = trap;
        board[26][46] = trap;
        board[27][45] = trap;
        board[27][46] = trap;

    }

    /**
     * When this method is called, the first trap is removed from the board
     * Player can no longer walk by it and still decrease player score
     */
    public void setTrapOneToFreeSpace() {
        board[20][12] = freeSpace;
        board[20][13] = freeSpace;
        board[21][12] = freeSpace;
        board[21][13] = freeSpace;
    }

    /**
     * When this method is called, the second trap is removed from the board
     * Player can no longer walk by it and still decrease player score
     */
    public void setTrapTwoToFreeSpace() {
        board[26][45] = freeSpace;
        board[26][46] = freeSpace;
        board[27][45] = freeSpace;
        board[27][46] = freeSpace;
    }

    /**
     * Creates map with 2D array populating with four walls
     * This method produces the base board with buffs, player, enemies, and exit
     */
    public void createMap() {
        board = new int[rowSize][columnSize];
        // Create initial board with outer wall and free spaces
        // Only accounting for walls because empty integer array elements are defaulted to 0
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (i == 0 || i == rowSize - 1)
                    board[i][j] = wall;
                else if (j == 0 || j == columnSize - 1)
                    board[i][j] = wall;
            }
        }

        // Call methods
        placePlayerAndEnemy();
        placeWallsAndExit();
        placeBuffsAndTraps();

        // Testing
        showMega();
    }

    ////////////////////////////// FOR TESTING //////////////////////////////
    /**
     * Created for testing
     * This is used in the test methods that checks for walls
     * Sets playerRow to the parameter and updates the board
     * @param row the specified row that the user will be on
     */
    public void setPlayerRowBoard(int row) {
        board[playerRow][playerColumn] = freeSpace;

        playerRow = row;

        board[playerRow][playerColumn] = player;
    }

    /**
     * Created for testing
     * This is used in the test methods that checks for walls
     * Sets playerColumn to the parameter and updates the board
     * @param column the specified column that the user will be on
     */
    public void setPlayerColumnBoard(int column){
        board[playerRow][playerColumn] = freeSpace;

        playerColumn = column;

        board[playerRow][playerColumn] = player;
    }

    /**
     * Created for testing
     * Sets playerRow to the parameter
     * @param row the specified row that the user will be on
     */
    public void setPlayerRow(int row){
        playerRow = row;
    }

    /**
     * Created for testing
     * Sets playerColumn to the parameter
     * @param column the specified column that the user will be on
     */
    public void setPlayerColumn(int column){
        playerColumn = column;
    }

    /**
     * Created for testing.
     * Gets the total number of rows on the board
     * @return the number of rows on the board
     */
    public int getRowSize(){
        return rowSize;
    }

    /**
     * Created for testing
     * Gets the total number of columns on the board
     * @return the number of columns on the board
     */
    public int getColumnSize(){
        return columnSize;
    }

}
