package game;

public class Util {

    //calculate the distance between the player and an enemy
    public static int dist(int x1, int y1, int x2, int y2) {
        float x = x2 - x1;
        float y = y2 - y1;

        return (int)Math.sqrt((x * x) + (y * y));
    }
}
