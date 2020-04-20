package game.logic;

/**
 * This class defines the basic attributes of a Character on screen.
 * Designed to hold all necessary components that a potential sprite will have in the game.
 * When controlling the objects in the game, the user should be able to mess around with player's health, speed they
 * are moving at, and where they can be placed onto the game board.
 * <p>
 * Each subclass extending through this parent class will inherit these characteristics as they are the elemental factors
 * within the nature of each object when interacting with them while playing.
 *
 */
public class Character {
    protected int health;
    protected int speed;
    protected int playerX;
    protected int playerY;

    /**
     * Entity's basic attributes in the game
     * @param x object's x position
     * @param y object's y position
     * @param health object's health
     * @param speed object's speed
     */
    public Character(int x, int y, int health, int speed) {
        this.health = health;
        this.speed = speed;
        this.playerX = x;
        this.playerY = y;
    }

    ////////////////////////////////////GETTERS AND SETTERS METHODS////////////////////////////////////

    /**
     * Setter method sets object's health
     * @param health sets current health to health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Getter method grabs object's health
     * @return current health of object
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Setter method sets object's speed
     * @param speed set current speed to speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Getter method grabs object's speed
     * @return current speed
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Getter method grabs object's X position
     * @return current X position
     */
    public int getX() {
        return this.playerX;
    }

    /**
     * Getter method grabs object's Y position
     * @return current Y position
     */
    public int getY() {
        return this.playerY;
    }

    /**
     * Setter method sets current X position to new X position
     * @param x sets current X position to new X position
     */
    public void setX(int x) {
        this.playerX = x;
    }

    /**
     * Setter method sets current Y position to new Y position
     * @param y sets current Y position to new Y position
     */
    public void setY(int y) {
        this.playerY = y;
    }
}
