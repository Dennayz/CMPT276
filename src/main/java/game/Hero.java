package game;

public class Hero extends Character {

    public static final int PLAYER_ID = 2;

    public Hero(int x, int y, int health, int speed) {
        super(x, y, health, speed);
    }

    protected void moveUp() {
        playerY -= speed;
    }

    protected void moveDown() {
        playerY += speed;
    }

    protected void moveLeft() {
        playerX -= speed;
    }

    protected void moveRight() {
        playerX += speed;
    }

    public int getType() {
        return PLAYER_ID;
    }


}
