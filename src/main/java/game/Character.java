package game;

public class Character {
    protected int health;
    protected int speed;
    protected int playerX;
    protected int playerY;

    public Character(int x, int y, int health, int speed) {
        this.health = health;
        this.speed = speed;
        this.playerX = x;
        this.playerY = y;
    }

    ////////////////////////////////////GETTERS AND SETTERS METHODS////////////////////////////////////
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getX() {
        return this.playerX;
    }

    public int getY() {
        return this.playerY;
    }

    public void setX(int x) {
        this.playerX = x;
    }

    public void setY(int y) {
        this.playerY = y;
    }



}
