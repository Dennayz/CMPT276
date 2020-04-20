package game.logic;

/**
 * This class inherits the Character class and adds attributes specific to the Hero.
 * Hero class mainly consists of the hero's ability to move around the game map. This means adjusting the direction
 * speed of the hero so it moves in the specific direction that the user allows it to move and the game itself.
 * Though the Hero class has all the traits that a classified character has that exists in the game, the hero carries its
 * own type so it is still considered as a designated character on screen but it distinguishes as a hero type.
 * <p>
 * The methods inside this class is immediately used when the player uses the KeyPressed method to detect user's
 * keystrokes. This all happens in the Graphics class and will be further documented their. For each tick in the game,
 * the hero will increase or decrease its speed or in other sense, the direction, meaning negative speed is in the
 * opposite direction of the positive speed for both X and Y directions.
 */
public class Hero extends Character {

    // Player's ID
    public static final int PLAYER_ID = 2;

    /**
     *
     * Player's attributes extended by the character class
     * @param x player x position
     * @param y player y position
     * @param health player's health mirrored with score
     * @param speed players speed when moving across the game board
     */
    public Hero(final int x, final int y, final int health, final int speed) {
        super(x, y, health, speed);
    }

    /**
     * moves player up.
     */
    public void moveUp() {
        playerY -= speed;
    }

    /**
     * moves player down.
     */
    public void moveDown() {
        playerY += speed;
    }

    /**
     * moves player left.
     */
    public void moveLeft() {
        playerX -= speed;
    }

    /**
     * moves player right.
     */
    public void moveRight() {
        playerX += speed;
    }

    /**
     * Get players type.
     * @return PLAYER_ID
     */
    public int getType() {
        return PLAYER_ID;
    }


}
