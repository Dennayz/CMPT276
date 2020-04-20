package game.logic;

/**
 * This class handles the distance between two objects. Each enemy will be equipped with this dist method within this
 * class to determine if the object in this case the player, is in reach of the enemy's distance or range. The method
 * will calculate the X, Y position of their target (player), with their own X, Y position (enemy) and consider if it is
 * in range of their line of sight. This method will output a number with each tick in the game and will be compared
 * with a set value that the user gives the enemy to ensure a proper radius around the enemy. The fixed radius that the
 * enemy has will be declared in the Graphic's class.
 */
public class Util {

    /**
     * Method calculates the distance between two objects.
     * @param x1 x position of the first object
     * @param y1 y position of the first object
     * @param x2 x position of the second object
     * @param y2 y position of the second object
     * @return returns the distance between the two objects using distance equation
     */
    public static int dist(final int x1, final int y1, final int x2, final int y2) {
        float x = x2 - x1;
        float y = y2 - y1;

        return (int) Math.sqrt((x * x) + (y * y));
    }

}
