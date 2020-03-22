package game;

import static game.Hero.PLAYER_ID;

public class Enemy extends Character{

    //enemies ID
    public static final int ENEMY_ID = 1;

    //enemies direction and speed adjustment
    private int enemyDirX = 0;
    private int enemyDirY = 0;
    private float DAMPING = 0.1f;

    public Enemy(int x, int y, int health, int speed) {
        super(x, y, health, speed);
    }

    //attacks player if it detects it is a player
    public void target(int playerPosX, int playerPosY, int x, int y, int type, int speed) {
        if (type == PLAYER_ID) {
            enemyDirX = playerPosX - x;
            enemyDirY = playerPosY - y;

            //controls the speed of the enemy's movement
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

    //getters functions for returning current enemy's speed and direction
    public int getEnemyDirX() {
        return this.enemyDirX;
    }

    public int getEnemyDirY() {
        return this.enemyDirY;
    }

    //return enemy type
    public int getType() {
        return ENEMY_ID;
    }




}
