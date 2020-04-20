package game.ui;

import game.logic.Enemy;
import game.logic.Hero;
import game.logic.Map;
import game.logic.Util;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static game.ui.Start.game;

/**
 * The purpose of this class holds all of the game logic for the graphic interface of the game. This class operates
 * closely with the Map class. With this class, everything will be brought to life for the user to see
 * within the Map class, where everything is set on the game board. This class is the main powerhouse for how the game
 * actually works and how each game object interact with each other.
 * <p>
 * The Graphic class consists of the players movement on the screen and displaying the direction of where the player is
 * looking at every tick of the game. All animations and image objects are implemented and checked with various other
 * classes to determine game collision and other connections between the game pieces. From this class on, the next couple
 * states after the main game state is either if the player decides to quit, or the player wins or loose the game. With
 * each factor, they are either exited from the game or led to each of two screens, the EndLoseScreen or EndWinScreen.
 * <p>
 * Use this class to alter player or enemies abilities and basic attributes including health and speed. Users can determine
 * how far the enemy can see aka the range of each enemy so if the player is within its range, they can start attacking
 * the player while it is still within the range. Also, users can adjust how much the basic rewards can give and the bonus
 * reward as well. This includes revamping the time period where the bonus reward appears and disappears from the map.
 * The user is able to manipulate the type of sprites that are shown on to the game and add more movement and actions
 * to the KeyPressed Method. Overall, this class is the where all the main features function together to build the game.
 */
public class Graphic extends JPanel implements KeyListener {

    // Width and height
    private static final int ENTITY_WIDTH = 50;
    private static final int ENTITY_HEIGHT = 50;

    // Bonus reward timer
    private int megaCounter = 0;
    private boolean show = false;

    // Score
    private int score = 10;

    // Initialize end and win screens
    private EndWinScreen endWinScreen = new EndWinScreen();
    private EndLoseScreen endLoseScreen = new EndLoseScreen();

    // Draw open and close portal
    private boolean drawOpenPortal = false;
    private boolean drawClosePortal = true;

    // Draw trap 1 and trap 2 if true
    private boolean drawTrap = true;
    private boolean drawTrapTwo = true;

    // Draw enemies
    private boolean drawEnemies = true;

    // Initialize new map object
    private Map map = new Map();

    // Detecting players key press to display proper view point
    private boolean switchDown = false;
    private boolean switchUp = false;
    private boolean switchLeft = false;
    private boolean switchRight = false;

    // Hero components
    private Hero hero;
    private int playerX = 0;
    private int playerY = 0;
    private int speed = 20;

    // Enemy components
    private Enemy enemy;
    private int enemySpeed = speed + 40;
    private int enemyX = 600;
    private int enemyY = 100;

    private Enemy enemyTwo;
    private int enemyTwoX = 500;
    private int enemyTwoY = 650;

    private Enemy hardEnemy;
    private int hardEnemyX = 1100;
    private int hardEnemyY = 300;

    // Enemy's range
    private int radius = 512;
    private float MELEE_RANGE = 38f;


    /**
     * Graphic constructor.
     * Initializes all images, timers, and key listener components with java swing
     */
    public Graphic(){

        // JLabel imageLabel = new JLabel();
        setSize(new Dimension(40, 40));

        // Call method to load all images
        ImageLoader.init();

        // Initialize all objects
        hero = new Hero(playerX, playerY, score, speed);
        enemy = new Enemy(enemyX, enemyY, score, enemySpeed);
        enemyTwo = new Enemy(enemyTwoX, enemyTwoY, score, enemySpeed);
        hardEnemy = new Enemy(hardEnemyX, hardEnemyY, score, enemySpeed);

        // Swing components
        addKeyListener(this); // Have keylistener attached to JPanel window
        setFocusable(true); // Use keylistener
        setFocusTraversalKeysEnabled(false); // Set tab keys and stuff to not do weird stuff

        // Generate random time intervals for bonus reward to appear
        Random random= new Random();
        final int rand= random.nextInt(270) + 30;
        System.out.println(rand/30 + " seconds for bonus buff");


        /**
         * Bonus reward pops up within a random time limit
         */
        Timer timer=new Timer(33, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                megaCounter++;
                if(megaCounter ==rand){

                    show=!show;
                    megaCounter =0;
                }

                // Calling game logic functions
                hitDetection();
                enemyOneSight();
                enemyTwoSight();
                hardEnemySight();

                repaint();
                }
        });
        timer.start();
    }

    /**
     * This method detects collision among player and enemies.
     * Kill player when colliding with enemy.
     * Gets called in the constructor.
     */
    public void hitDetection(){
        if (new Rectangle(hero.getX(), hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(enemyX, enemyY, ENTITY_WIDTH, ENTITY_HEIGHT))) {
            showGameOver();
        }
        if (new Rectangle(hero.getX(), hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(enemyTwoX, enemyTwoY, ENTITY_WIDTH, ENTITY_HEIGHT))) {
            showGameOver();
        }
        if (new Rectangle(hero.getX(), hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(hardEnemyX, hardEnemyY, ENTITY_WIDTH, ENTITY_HEIGHT))) {
            showGameOver();
        }
    }

    /**
     * This is the first enemy's line of sight.
     * Keeps its distance so it doesn't overlap player.
     * Gets called in the constructor.
     */
    public void enemyOneSight(){
        if (Util.dist(hero.getX(), hero.getY(), enemyX, enemyY) < radius) {
            if (Util.dist(hero.getX(), hero.getY(), enemyX, enemyY) >= MELEE_RANGE) {

                // Attack the player once player is seen
                enemy.target(hero.getX(), hero.getY(), enemyX, enemyY, hero.getType(), enemy.getSpeed());
                enemyX += enemy.getEnemyDirX();
                enemyY += enemy.getEnemyDirY();

                // Enemy cannot see player through walls or move through them
                if (new Rectangle(enemyX, enemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(780, 480,20,200))) {
                    enemyX -= enemy.getEnemyDirX();
                    enemyY -= enemy.getEnemyDirY();
                }
                else if (new Rectangle(enemyX, enemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(80,0,20,160))) {
                    enemyX -= enemy.getEnemyDirX();
                    enemyY -= enemy.getEnemyDirY();
                }
                else if (new Rectangle(enemyX, enemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(400,300,20,180))) {
                    enemyX -= enemy.getEnemyDirX();
                    enemyY -= enemy.getEnemyDirY();
                }
                else if (new Rectangle(enemyX, enemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(420,300,200,20))) {
                    enemyX -= enemy.getEnemyDirX();
                    enemyY -= enemy.getEnemyDirY();
                }
                else if (new Rectangle(enemyX, enemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(0,540,200,20))) {
                    enemyX -= enemy.getEnemyDirX();
                    enemyY -= enemy.getEnemyDirY();
                }
                else if (new Rectangle(enemyX, enemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(800,100,400,20))) {
                    enemyX -= enemy.getEnemyDirX();
                    enemyY -= enemy.getEnemyDirY();
                }
                else if (new Rectangle(enemyX, enemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(940,120,20,200))) {
                    enemyX -= enemy.getEnemyDirX();
                    enemyY -= enemy.getEnemyDirY();
                }
                else if (new Rectangle(enemyX, enemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(100,80,200,20))) {
                    enemyX -= enemy.getEnemyDirX();
                    enemyY -= enemy.getEnemyDirY();
                }
            }
        }
    }

    /**
     * This is the second enemy's line of sight.
     * Keeps its distance so it doesn't overlap player.
     * Gets called in the constructor.
     */
    public void enemyTwoSight(){
        if (Util.dist(hero.getX(), hero.getY(), enemyTwoX, enemyTwoY) < radius) {
            if (Util.dist(hero.getX(), hero.getY(), enemyTwoX, enemyTwoY) >= MELEE_RANGE) {

                // Attack the player once player is seen
                enemyTwo.target(hero.getX(), hero.getY(), enemyTwoX, enemyTwoY, hero.getType(), enemyTwo.getSpeed());
                enemyTwoX += enemyTwo.getEnemyDirX();
                enemyTwoY += enemyTwo.getEnemyDirY();

                // Enemy two cannot see player through walls or move through them
                if (new Rectangle(enemyTwoX, enemyTwoY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(780, 480,20,200))) {
                    enemyTwoX -= enemyTwo.getEnemyDirX();
                    enemyTwoY -= enemyTwo.getEnemyDirY();
                }
                else if (new Rectangle(enemyTwoX, enemyTwoY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(80,0,20,160))) {
                    enemyTwoX -= enemyTwo.getEnemyDirX();
                    enemyTwoY -= enemyTwo.getEnemyDirY();
                }
                else if (new Rectangle(enemyTwoX, enemyTwoY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(400,300,20,180))) {
                    enemyTwoX -= enemyTwo.getEnemyDirX();
                    enemyTwoY -= enemyTwo.getEnemyDirY();
                }
                else if (new Rectangle(enemyTwoX, enemyTwoY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(420,300,200,20))) {
                    enemyTwoX -= enemyTwo.getEnemyDirX();
                    enemyTwoY -= enemyTwo.getEnemyDirY();
                }
                else if (new Rectangle(enemyTwoX, enemyTwoY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(0,540,200,20))) {
                    enemyTwoX -= enemyTwo.getEnemyDirX();
                    enemyTwoY -= enemyTwo.getEnemyDirY();
                }
                else if (new Rectangle(enemyTwoX, enemyTwoY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(800,100,400,20))) {
                    enemyTwoX -= enemyTwo.getEnemyDirX();
                    enemyTwoY -= enemyTwo.getEnemyDirY();
                }
                else if (new Rectangle(enemyTwoX, enemyTwoY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(940,120,20,200))) {
                    enemyTwoX -= enemyTwo.getEnemyDirX();
                    enemyTwoY -= enemyTwo.getEnemyDirY();
                }
                else if (new Rectangle(enemyTwoX, enemyTwoY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(100,80,200,20))) {
                    enemyTwoX -= enemyTwo.getEnemyDirX();
                    enemyTwoY -= enemyTwo.getEnemyDirY();
                }
            }
        }
    }

    /**
     * This is the hard enemy's (owl) line of sight.
     * Keeps its distance so it doesn't overlap player.
     * Gets called in the constructor.
     */
    public void hardEnemySight(){
        if (Util.dist(hero.getX(), hero.getY(), hardEnemyX, hardEnemyY) < radius + 50) {
            if (Util.dist(hero.getX(), hero.getY(), hardEnemyX, hardEnemyY) >= MELEE_RANGE) {

                // Attack the player once player is seen
                hardEnemy.target(hero.getX(), hero.getY(), hardEnemyX, hardEnemyY, hero.getType(), hardEnemy.getSpeed() + 20);
                hardEnemyX += hardEnemy.getEnemyDirX();
                hardEnemyY += hardEnemy.getEnemyDirY();

                // Owl cannot see player through walls or move through them
                if (new Rectangle(hardEnemyX, hardEnemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(780, 480,20,200))) {
                    hardEnemyX -= hardEnemy.getEnemyDirX();
                    hardEnemyY -= hardEnemy.getEnemyDirY();
                }
                else if (new Rectangle(hardEnemyX, hardEnemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(80,0,20,160))) {
                    hardEnemyX -= hardEnemy.getEnemyDirX();
                    hardEnemyY -= hardEnemy.getEnemyDirY();
                }
                else if (new Rectangle(hardEnemyX, hardEnemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(400,300,20,180))) {
                    hardEnemyX -= hardEnemy.getEnemyDirX();
                    hardEnemyY -= hardEnemy.getEnemyDirY();
                }
                else if (new Rectangle(hardEnemyX, hardEnemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(420,300,200,20))) {
                    hardEnemyX -= hardEnemy.getEnemyDirX();
                    hardEnemyY -= hardEnemy.getEnemyDirY();
                }
                else if (new Rectangle(hardEnemyX, hardEnemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(0,540,200,20))) {
                    hardEnemyX -= hardEnemy.getEnemyDirX();
                    hardEnemyY -= hardEnemy.getEnemyDirY();
                }
                else if (new Rectangle(hardEnemyX, hardEnemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(800,100,400,20))) {
                    hardEnemyX -= hardEnemy.getEnemyDirX();
                    hardEnemyY -= hardEnemy.getEnemyDirY();
                }
                else if (new Rectangle(hardEnemyX, hardEnemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(940,120,20,200))) {
                    hardEnemyX -= hardEnemy.getEnemyDirX();
                    hardEnemyY -= hardEnemy.getEnemyDirY();
                }
                else if (new Rectangle(hardEnemyX, hardEnemyY, ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(100,80,200,20))) {
                    hardEnemyX -= hardEnemy.getEnemyDirX();
                    hardEnemyY -= hardEnemy.getEnemyDirY();
                }
            }
        }
    }





    /**
     * Method displays the graphical interface the user sees while playing the game.
     * Loads all images and paints all subjects accordingly.
     * @param g Draws the the images and objects in the game.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;

        // Background
        g.drawImage(ImageLoader.bufferedBackground, 0,0,1200,800,null);

        // Create buff1 for player to collect
        if(map.buff1Check()) {
            g.drawImage(ImageLoader.bufferedCheese,180,220, ENTITY_WIDTH, ENTITY_HEIGHT,this);
        }

        // Create buff2 for player to collect
        if(map.buff2Check()) {
            g.drawImage(ImageLoader.bufferedCheese,600,380, ENTITY_WIDTH, ENTITY_HEIGHT,this);
        }

        // Create buff3 for player to collect
        if(map.buff3Check()) {
            g.drawImage(ImageLoader.bufferedCheese,400,60, ENTITY_WIDTH, ENTITY_HEIGHT,this);
        }

        // Draw traps
        if (drawTrap)
            g.drawImage(ImageLoader.bufferedTrap,900,540, ENTITY_WIDTH, ENTITY_HEIGHT,this);
        if (drawTrapTwo)
            g.drawImage(ImageLoader.bufferedTrapSecond,240,420, ENTITY_WIDTH, ENTITY_HEIGHT,this);

        // Create bonus reward
        if(map.checkMega() && show){
            map.showMega();
            g.drawImage(ImageLoader.bonusReward,1100,40, ENTITY_WIDTH, ENTITY_HEIGHT, this);
        }
        else {
            map.hideMega();

        }

        // Creates the exit location
        if (drawOpenPortal)
            g.drawImage(ImageLoader.openPortal,1080,580,100,100,this);
        if (drawClosePortal)
            g.drawImage(ImageLoader.closePortal,1080,580,100,100,this);

        // Creates the player and rotations due to key press
        if (drawUp()) {
            g.drawImage(ImageLoader.bufferedPlayerUp, hero.getX(),hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT,this);
        }
        else if (drawDown()) {
            g.drawImage(ImageLoader.bufferedPlayerDown, hero.getX(),hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT,this);
        }
        else if (drawRight()) {
            g.drawImage(ImageLoader.bufferedPlayerRight, hero.getX(),hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT,this);
        }
        else if (drawLeft()) {
            g.drawImage(ImageLoader.bufferedPlayerLeft, hero.getX(),hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT,this);
        }
        else {
            g.drawImage(ImageLoader.bufferedPlayer,hero.getX(),hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT,this);
        }

        // Draw enemy if it does not collide with player
        if (drawEnemies) {
            // Enemy
            g.drawImage(ImageLoader.bufferedBirds,enemyX,enemyY, ENTITY_WIDTH + 10, ENTITY_HEIGHT + 10,this);

            // Enemy two
            g.drawImage(ImageLoader.bufferedBirds,enemyTwoX,enemyTwoY, ENTITY_WIDTH + 10, ENTITY_HEIGHT + 10,this);

            // Hard enemy
            g.drawImage(ImageLoader.owl,hardEnemyX,hardEnemyY,ENTITY_WIDTH + 10, ENTITY_HEIGHT + 10,this);
        }

        /* Draws all wall images onto the game board */
        g.drawImage(ImageLoader.bufferedWallsVertical,80,0,20,160,this);

        g.drawImage(ImageLoader.bufferedWallsVertical,400,300,20,180,this);

        g.drawImage(ImageLoader.bufferedWallsHorizontal,420,300,200,20,this);

        g.drawImage(ImageLoader.bufferedWallsHorizontal,0,540,200,20,this);

        g.drawImage(ImageLoader.bufferedWallsHorizontal,800,100,400,20,this);

        g.drawImage(ImageLoader.bufferedWallsVertical,780,480,20,200,this);

        g.drawImage(ImageLoader.bufferedWallsVertical,940,120,20,200,this);

        g.drawImage(ImageLoader.bufferedWallsHorizontal,100,80,200,20,this);

        /* Draws score board onto the game screen */
        g2d.setColor(Color.BLACK);
        Font font = new Font("Verdana", Font.BOLD, 25);
        g2d.setFont(font);
        g2d.drawString("SCORE: "+ hero.getHealth(),0,660);



    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    /**
     * Checks WASD keys per tick by player.
     * @param e Detects player key pressed.
     */
    @Override
    public void keyPressed (KeyEvent e){
        // Checks player's UP location if there is a wall between player
        // Direction of player will be up
        if(e.getKeyCode() == KeyEvent.VK_W){
            switchUp = true;
            switchLeft = false;
            switchRight = false;
            switchDown = false;
            if(!map.checkWallUp()) {
                hero.moveUp();
            }
            checkForItemsAndWinState();
        }

        // Checks player's DOWN location if there is a wall between player
        // Direction of player will be down
        if(e.getKeyCode() == KeyEvent.VK_S){
            switchDown = true;
            switchUp = false;
            switchLeft = false;
            switchRight = false;
            if(!map.checkWallDown()) {
                hero.moveDown();
            }
            checkForItemsAndWinState();

        }

        // Checks player's LEFT location if there is a wall between player
        // Direction of player will be left
        if(e.getKeyCode() == KeyEvent.VK_A){
            switchLeft = true;
            switchDown = false;
            switchUp = false;
            switchRight = false;
            if(!map.checkWallLeft()) {
                hero.moveLeft();
            }
            checkForItemsAndWinState();

        }

        // Checks player's RIGHT location if there is a wall between player
        // Direction of player will be right
        if(e.getKeyCode() == KeyEvent.VK_D) {
            switchRight = true;
            switchDown = false;
            switchUp = false;
            switchLeft = false;
            if(!map.checkWallRight()) {
                hero.moveRight();
            }
            checkForItemsAndWinState();

        }
        // Death by trap
        if(hero.getHealth() == 0){
            // Game over screen
            showGameOver();
        }

    }

    /////////////////////////////////////////////////////////////////////// ALL METHODS ///////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////// ALL METHODS ///////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////// ALL METHODS ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method stores all logic for checking if player has landed on different reward types.
     * Also checks if player has landed on a trap.
     * Traps and rewards are removed from the game board after being landed on by player.
     * Shows win screen if the next move made by the player is within the exit zone (yellow).
     */
    public void checkForItemsAndWinState() {
        if (map.checkTrap()) {
            System.out.println("trap");
            hero.setHealth(score -= 5);

            // If player lands on trap, erase it from the board.
            if (new Rectangle(hero.getX(), hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(900, 540, ENTITY_WIDTH, ENTITY_HEIGHT))) {
                drawTrap = false;
                map.setTrapTwoToFreeSpace();
            } else if (new Rectangle(hero.getX(), hero.getY(), ENTITY_WIDTH, ENTITY_HEIGHT).intersects(new Rectangle(240, 420, ENTITY_WIDTH, ENTITY_HEIGHT))) {
                drawTrapTwo = false;
                map.setTrapOneToFreeSpace();
            }
        }

        // Bonus reward points.
        if(map.changeMega()){
            System.out.println("MEGA");
            hero.setHealth(score +=50);
        }
        // First reward's points.
        if(map.changeBuff1()){
            System.out.println("buff1");
            hero.setHealth(score +=10);
        }
        // Second reward's points.
        if(map.changeBuff2()){
            System.out.println("buff2");
            hero.setHealth(score +=10);
        }
        // Third reward's points.
        if(map.changeBuff3()){
            System.out.println("buff3");
            hero.setHealth(score +=10);
        }
        // Open the portal and allow user to win the game.
        if(!map.buff1Check() && !map.buff2Check() && !map.buff3Check()) {
            drawClosePortal = false;
            drawOpenPortal = true;
        }
        // When player lands on exit zone and has collected all rewards.
        if(map.checkExit()){
            if(!map.buff1Check() && !map.buff2Check() && !map.buff3Check()) {
                System.out.println("exit");
                // YOU WIN SCREEN
                showWinGame();

            }
        }

    }

    /**
     * Call method when the player reaches the yellow void after collecting all rewards.
     * This method also removes all enemy drawings and remove them off the map.
     * Dispose the game frame and show the win screen right after.
     */
    public void showWinGame() {
        drawEnemies = false;
        removeEnemies();
        game.dispose();
        endWinScreen.create(hero.getHealth());
    }

    /**
     * Call method when the player lands on a trap or hit by a moving enemy.
     * Method removes all drawings of enemies and remove them off the map.
     * Dispose the game frame and and show the game over screen right after.
     */
    public void showGameOver() {
        drawEnemies = false;
        hero.setHealth(0);
        removeEnemies();
        game.dispose();
        endLoseScreen.create(hero.getHealth());
    }

    /**
     * When this method is called, all animated enemies are removed from the game.
     * Player can no longer die from enemies even after the game is finished.
     */
    public void removeEnemies() {
        enemyX = -20;
        enemyY = -20;
        enemyTwoX = -20;
        enemyTwoY = -20;
        hardEnemyX = -20;
        hardEnemyY = -20;
    }

    /**
     * Created method to determine if the player is looking UP after each tick.
     * @return Returns boolean true after detecting if a certain "W" was pressed.
     */
    public boolean drawUp() {
        return switchUp;
    }

    /**
     * Created method to determine if the player is looking DOWN after each tick.
     * @return Returns boolean true after detecting if a certain "S" was pressed.
     */
    public boolean drawDown() {
        return switchDown;
    }

    /**
     * Created method to determine if the player is looking RIGHT after each tick.
     * @return Returns boolean true after detecting if a certain "D" was pressed.
     */
    public boolean drawRight() {
        return switchRight;
    }

    /**
     * Created method to determine if the player is looking LEFT after each tick.
     * @return Returns boolean true after detecting if a certain "A" was pressed.
     */
    public boolean drawLeft() {
        return switchLeft;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }



    //////////////////////// TESTING ////////////////////////////////////////////////////////

    /**
     * Getter for the first enemy's X value.
     * @return this is the first enemy's X value.
     */
    public int getEnemyX() {
        return enemyX;
    }

    /**
     * Getter for the first enemy's Y value.
     * @return this is the first enemy's Y value.
     */
    public int getEnemyY() {
        return enemyY;
    }

    /**
     * Getter for the second enemy's X value.
     * @return this is the second enemy's X value.
     */
    public int getEnemyTwoX() {
        return enemyTwoX;
    }

    /**
     * Getter for the second enemy's Y value.
     * @return this is the second enemy's Y value.
     */
    public int getEnemyTwoY() {
        return enemyTwoY;
    }

    /**
     * Getter for the hard enemy's (owl) X value.
     * @return this is the hard enemy's (owl) X value.
     */
    public int getHardEnemyX() {
        return hardEnemyX;
    }

    /**
     * Getter for the hard enemy's (owl) Y value.
     * @return this is the hard enemy's (owl) Y value.
     */
    public int getHardEnemyY() {
        return hardEnemyY;
    }

}
