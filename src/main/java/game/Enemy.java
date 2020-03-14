package game;

import static game.Hero.PLAYER_ID;

public class Enemy extends Character{

    public static final int ENEMY_ID = 1;

    private int enemyDirX;
    private int enemyDirY;
    private float DAMPING = 0.5f;

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

    public int getEnemyDirX() {
        return this.enemyDirX;
    }

    public int getEnemyDirY() {
        return this.enemyDirY;
    }

    public int getType() {
        return ENEMY_ID;
    }




}
