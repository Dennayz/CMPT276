package game;

public class Hero extends Character {

    //Player's ID
    public static final int PLAYER_ID = 2;

    public Hero(int x, int y, int health, int speed) {
        super(x, y, health, speed);
    }

    //move up
    protected void moveUp() {
        playerY -= speed;
    }

    //move down
    protected void moveDown() {
        playerY += speed;
    }

    //move left
    protected void moveLeft() {
        playerX -= speed;
    }

    //move right
    protected void moveRight() {
        playerX += speed;
    }

    //grab the player's type
    public int getType() {
        return PLAYER_ID;
    }


}
