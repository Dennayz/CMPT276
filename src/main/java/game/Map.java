package game;

public class Map {

    //column and row size;
    private final int ROW_SIZE = 34;
    private final int COLUMN_SIZE = 60;

    //board variables
    protected int board[][];
    private final int WALL = 1;
    protected final int FREE_SPACE = 0;
    private final int EXIT = 3;
    private final int ENEMY = 4;
    private final int TRAP = 5;

    //display bonus reward
    private final int MEGA_BUFF = 6;
    private boolean megaBuffCheck = true;

    //attributes for detecting rewards
    private final int BUFF1 = 7;
    private boolean buff1Check = true;
    private final int BUFF2 = 8;
    private boolean buff2Check = true;
    private final int BUFF3 = 9;
    private boolean buff3Check = true;

    //player's position
    private final int PLAYER = 2;
    private int playerRow;
    private int playerColumn;

    public Map() {
        createMap();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////GETTERS AND SETTERS///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerColumn() {
        return playerColumn;
    }

    /**
     * Checks if the element directly above the current element is a WALL
     * @return true if the specified element is a WALL
     * @return false if the specified element is not a WALL, changes playerRow to check element
     */
    public boolean checkWallUp() {
        if (board[getPlayerRow() - 1][getPlayerColumn()] == WALL) {
            return true;
        } else {
            playerRow--;
            return false;
        }
    }

    /**
     * Checks if the element directly below the current element is a WALL
     * @return true if the specified element is a WALL
     * @return false if the specified element is not a WALL, changes playerRow to check element
     */
    public boolean checkWallDown() {
        if (board[getPlayerRow() + 1][getPlayerColumn()] == WALL) {
            return true;
        } else {
            playerRow++;
            return false;
        }
    }

    /**
     * Checks if the element directly to the left of current element is a WALL
     * @return true if the specified element is a WALL
     * @return false if the specified element is not a WALL, changes playerColumn to check element
     */
    public boolean checkWallLeft() {
        if (board[getPlayerRow()][getPlayerColumn() - 1] == WALL) {
            return true;
        } else {
            playerColumn--;
            return false;
        }
    }

    /**
     * Checks if the element directly to the right of current element is a WALL
     * @return true if the specified element is a WALL
     * @return false if the specified element is not a WALL, changes playerColumn to check element
     */
    public boolean checkWallRight() {
        if (board[getPlayerRow()][getPlayerColumn() + 1] == WALL) {
            return true;
        } else {
            playerColumn++;
            return false;
        }
    }

    /**
     * Checks to see if the player's current position on the grid is a TRAP
     * @return true if the currrent position is a TRAP
     * @return false if the current position is not a TRAP
     */
    public boolean checkTrap() {
        if (board[getPlayerRow()][getPlayerColumn()] == TRAP) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to see if the player's current position on the grid is a EXIT
     * @return true if the currrent position is a EXIT
     * @return false if the current position is not a EXIT
     */
    public boolean checkExit() {
        if (board[getPlayerRow()][getPlayerColumn()] == EXIT) {
            return true;
        } else {
            return false;
        }
    }

    //show special buffs
    public void showMega() {
        board[2][55] = MEGA_BUFF;
        board[2][56] = MEGA_BUFF;
        board[3][55] = MEGA_BUFF;
        board[3][56] = MEGA_BUFF;

    }

    //hides special buffs
    public void hideMega() {
        board[2][55] = FREE_SPACE;
        board[2][56] = FREE_SPACE;
        board[3][55] = FREE_SPACE;
        board[3][56] = FREE_SPACE;

    }

    //changes bonus rewards properties when it disappears
    public boolean changeMega() {
        if (board[playerRow][playerColumn] == MEGA_BUFF) {
            megaBuffCheck = false;

            board[2][55] = FREE_SPACE;
            board[2][56] = FREE_SPACE;
            board[3][55] = FREE_SPACE;
            board[3][56] = FREE_SPACE;


            return true;
        } else {
            return false;
        }
    }

    //check if bonus reward is there
    public boolean checkMega() {
        return megaBuffCheck;
    }

    //reset buff1's properties to a free space if player lands on it
    public boolean changeBuff1() {
        if (board[playerRow][playerColumn] == BUFF1) {
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

    //reset buff2's properties to a free space if player lands on it
    public boolean changeBuff2() {
        if (board[playerRow][playerColumn] == BUFF2) {
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

    //reset buff3's properties to a free space if player lands on it
    public boolean changeBuff3() {
        if (board[playerRow][playerColumn] == BUFF3) {
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

    //check if buff1 is on board
    public boolean buff1Check() {
        return buff1Check;
    }

    //check if buff2 is on board
    public boolean buff2Check() {
        return buff2Check;
    }

    //check if buff3 is on board
    public boolean buff3Check() {
        return buff3Check;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////GETTERS AND SETTERS END///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * This method creates the base board, places the character, exit, buffs and enemies
     */


    //intialize player and enemy position
    public void placePlayerAndEnemy() {
        // PLAYER STARTING POSITION
        playerRow = 1;
        playerColumn = 1;
        board[playerRow][playerColumn] = PLAYER;

        /**
         * ENEMIES
         */
        // Enemy 1
        board[9][COLUMN_SIZE - 2] = ENEMY;
        // Enemy 2
        board[9][1] = ENEMY;
        // Enemy 3
        board[ROW_SIZE - 2][5] = ENEMY;
    }

    //initialize walls and exit positions
    public void placeWallsAndExit() {
        // EXIT 2 high 3 wide
        board[ROW_SIZE - 4][COLUMN_SIZE - 2] = EXIT;
        board[ROW_SIZE - 4][COLUMN_SIZE - 3] = EXIT;
        board[ROW_SIZE - 4][COLUMN_SIZE - 4] = EXIT;
        board[ROW_SIZE - 4][COLUMN_SIZE - 5] = EXIT;

        board[ROW_SIZE - 3][COLUMN_SIZE - 2] = EXIT;
        board[ROW_SIZE - 3][COLUMN_SIZE - 3] = EXIT;
        board[ROW_SIZE - 3][COLUMN_SIZE - 4] = EXIT;
        board[ROW_SIZE - 3][COLUMN_SIZE - 5] = EXIT;

        board[ROW_SIZE - 2][COLUMN_SIZE - 2] = EXIT;
        board[ROW_SIZE - 2][COLUMN_SIZE - 3] = EXIT;
        board[ROW_SIZE - 2][COLUMN_SIZE - 4] = EXIT;
        board[ROW_SIZE - 2][COLUMN_SIZE - 5] = EXIT;


        for (int i = 0; i < 8; i++) {
            board[1 + i][4] = WALL;
        }
        for (int i = 0; i < 8; i++) {
            board[1 + i][5] = WALL;
        }

        for (int i = 0; i < 10; i++) {
            board[15 + i][20] = WALL;
        }
        for (int i = 0; i < 10; i++) {
            board[15 + i][21] = WALL;
        }

        for (int i = 0; i < 10; i++) {
            board[15][22 + i] = WALL;
        }
        for (int i = 0; i < 10; i++) {
            board[16][22 + i] = WALL;
        }

        for (int i = 0; i < 10; i++) {
            board[27][1 + i] = WALL;
        }
        for (int i = 0; i < 10; i++) {
            board[28][1 + i] = WALL;
        }


        for (int i = 0; i < 19; i++) {
            board[5][40 + i] = WALL;
        }
        for (int i = 0; i < 19; i++) {
            board[6][40 + i] = WALL;
        }

        for (int i = 0; i < 10; i++) {
            board[33 - i][39] = WALL;
        }
        for (int i = 0; i < 10; i++) {
            board[33 - i][40] = WALL;
        }

        for (int i = 0; i < 10; i++) {
            board[7 + i][47] = WALL;
        }
        for (int i = 0; i < 10; i++) {
            board[7 + i][48] = WALL;
        }

        for (int i = 0; i < 10; i++) {
            board[4][6 + i] = WALL;
        }
        for (int i = 0; i < 10; i++) {
            board[5][6 + i] = WALL;
        }


    }

    //initialized buffs and traps positions
    public void placeBuffsAndTraps() {
        /**
         * BUFFS
         */

        board[11][9] = BUFF1;
        board[11][10] = BUFF1;
        board[12][9] = BUFF1;
        board[12][10] = BUFF1;

        board[19][30] = BUFF2;
        board[19][31] = BUFF2;
        board[20][30] = BUFF2;
        board[20][31] = BUFF2;

        board[3][20] = BUFF3;
        board[3][21] = BUFF3;
        board[4][20] = BUFF3;
        board[4][21] = BUFF3;


        /**
         * TRAPS
         */
        // Trap 1
        board[26][45] = TRAP;
        board[26][46] = TRAP;
        board[27][45] = TRAP;
        board[27][46] = TRAP;

        board[20][12] = TRAP;
        board[20][13] = TRAP;
        board[21][12] = TRAP;
        board[21][13] = TRAP;


    }

    /**
     * Creates map with 2D array populating with four walls
     * This method produces the base board with buffs, player, enemies, and exit
     */
    public void createMap() {
        board = new int[ROW_SIZE][COLUMN_SIZE];
        // Create initial board with outer wall and free spaces
        // Only accounting for walls because empty integer array elements are defaulted to 0
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                if (i == 0 || i == ROW_SIZE - 1)
                    board[i][j] = WALL;
                else if (j == 0 || j == COLUMN_SIZE - 1)
                    board[i][j] = WALL;
            }
        }

        //call methods
        placePlayerAndEnemy();
        placeWallsAndExit();
        placeBuffsAndTraps();

    }
}