package game.logic;

import static game.logic.Hero.PLAYER_ID;

/**
 * This class inherits the Character class and adds attributes specific to the Enemy.
 * Enemy class mainly consists of each enemy's attack ability which each enemy entity possess.
 * While having all the traits that a classified character has that exists in the game, the enemy carries its
 * own type so it is still considered as a designated character on screen but it distinguishes as an enemy type.
 * <p>
 * The methods inside this class is immediately used when the player enters the range of the enemy's line of sight.
 * They will match the player's movements and follow them until they get close enough to attack the player. The range
 * is not set in this class.
 */
public class Enemy extends Character {

    /**
     * Creates enemy initial values.
     */
    public static final int ENEMY_ID = 1;

    // Enemy's direction and speed adjustment
    private int enemyDirX = 0;
    private int enemyDirY = 0;
    private float DAMPING = 0.1f;

    /**
     * Enemies attributes extended by the character class.
     * Inherit getters and setters for health, speed, X and Y positions for enemy to have.
     * @param x enemy's x position
     * @param y enemy's y position
     * @param health enemy's health
     * @param speed enemy's moving speed when chasing player
     *              different enemies have different speeds
     */
    public Enemy(final int x, final int y, final int health, final int speed) {
        super(x, y, health, speed);
    }

    /**
     * Used for enemies to determine which object is a player.
     * Follow and attack player after recognizing that it is a player.
     * @param playerPosX give the player's X position
     * @param playerPosY give the player's Y position
     * @param x enemy's X position
     * @param y enemy's Y position
     * @param type which object enemy is tracking, usually player
     * @param speed sets the enemy's speed at which they are chasing the player
     */
    public void target(final int playerPosX, final int playerPosY, final int x, final int y, final int type, final int speed) {
        // Enemy matches players movements and follows them
        if (type == PLAYER_ID) {
            enemyDirX = playerPosX - x;
            enemyDirY = playerPosY - y;

            // Controls the speed of the enemy's movement
            float maxSpeed = (speed - 50) * DAMPING;

            if (enemyDirX > maxSpeed) {
                enemyDirX = (int) maxSpeed;
            }
            if (enemyDirX < -maxSpeed) {
                enemyDirX = (int) -maxSpeed;
            }

            if (enemyDirY > maxSpeed) {
                enemyDirY = (int) maxSpeed;
            }
            if (enemyDirY < -maxSpeed) {
                enemyDirY = (int) -maxSpeed;
            }
        }

    }

    /**
     * Getter method tracks the enemy's X direction it is moving towards.
     * @return the enemy's X speed direction
     */
    public int getEnemyDirX() {
        return this.enemyDirX;
    }

    /**
     * Getter method tracks the enemy's Y direction it is moving towards.
     * @return the enemy's Y speed direction
     */
    public int getEnemyDirY() {
        return this.enemyDirY;
    }

    /**
     * Getter method used to detect if it is a type enemy.
     * @return enemy's ID
     */
    public int getType() {
        return ENEMY_ID;
    }




}
