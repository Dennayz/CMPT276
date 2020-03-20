package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import static game.Start.game;

public class Graphic extends JPanel implements KeyListener {

    // bonus reward timer
    private int megaCounter =0;
    private boolean show=false;

    //score
    private int score = 10;

    //initialize end and win screens
    private EndWinScreen endWinScreen = new EndWinScreen();
    private EndLoseScreen endLoseScreen = new EndLoseScreen();

    //draw trap 1 and trap 2 if true
    private boolean drawTrap = true;
    private boolean drawTrapTwo = true;

    //draw enemies
    private boolean drawEnemies = true;

    //initialize new map object
    private Map map = new Map();

    //detecting players keypress to display proper view point
    private boolean switchDown = false;
    private boolean switchUp = false;
    private boolean switchLeft = false;
    private boolean switchRight = false;

    //hero components
    private Hero hero;
    private int playerX = 0;
    private int playerY = 0;
    private int speed = 20;

    //enemy components
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

    //enemy's range
    private int radius = 512;
    private float MELEE_RANGE = 42f;


    //import sprites
    private BufferedImage bufferedBackground = null;
    private BufferedImage bufferedWallsVertical = null;
    private BufferedImage bufferedWallsHorizontal = null;
    private BufferedImage bufferedCheese = null;

    private BufferedImage bufferedPlayer = null;
    private BufferedImage bufferedPlayerDown = null;
    private BufferedImage bufferedPlayerUp = null;
    private BufferedImage bufferedPlayerRight = null;
    private BufferedImage bufferedPlayerLeft = null;

    private BufferedImage bufferedTrap = null;
    private BufferedImage bufferedTrapSecond = null;
    private BufferedImage bufferedBirds = null;

    //import sprite animation
    private Image owl = null;
    private Image bonusReward = null;

    public Graphic(){

        //JLabel imageLabel = new JLabel();
        setSize(new Dimension(40, 40));

        //add in all image sprites to game when game loads
        try {
            bufferedBackground = ImageIO.read(new File("res/grassBackground.png"));
            bufferedWallsVertical = ImageIO.read(new File("res/vwall.png"));
            bufferedWallsHorizontal = ImageIO.read(new File("res/hwall.png"));
            bufferedCheese = ImageIO.read(new File("res/cheese_sprite.png"));
            bufferedPlayer = ImageIO.read((new File("res/mouse.png")));
            bufferedTrap = ImageIO.read(new File("res/trap.png"));
            bufferedTrapSecond =  ImageIO.read(new File("res/trap.png"));
            bufferedBirds = ImageIO.read(new File("res/birds.png"));

            bufferedPlayerDown = ImageIO.read(new File("res/mouseDown.png"));
            bufferedPlayerUp = ImageIO.read(new File("res/mouseUp.png"));
            bufferedPlayerRight = ImageIO.read(new File("res/mouseRight.png"));
            bufferedPlayerLeft = ImageIO.read(new File("res/mouseLeft.png"));

            owl = new ImageIcon(new URL("https://2.bp.blogspot.com/-W8eX-eT-q8w/W4vHy5WQ7tI/AAAAAAAav1I/W7IwxgzMHW0iBw_uXXoBcja5bVhirShTQCLcBGAs/s1600/AW1686559_17.gif")).getImage();
            bonusReward = new ImageIcon(new URL("https://gifimage.net/wp-content/uploads/2017/11/gold-stars-gif-6.gif")).getImage();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //intialize all objects
        hero = new Hero(playerX, playerY, score, speed);
        enemy = new Enemy(enemyX, enemyY, score, enemySpeed);
        enemyTwo = new Enemy(enemyTwoX, enemyTwoY, score, enemySpeed);
        hardEnemy = new Enemy(hardEnemyX, hardEnemyY, score, enemySpeed);

        //swing components
        addKeyListener(this); //have keylistener attached to jpanel window
        setFocusable(true); // lets u use keylistener?
        setFocusTraversalKeysEnabled(false); //set tab keys and stuff to not do weird stuff

        //generate random time intervals for bonus reward to appear
        Random random= new Random();
        final int rand= random.nextInt(270) + 30;
        System.out.println(rand/30 + " seconds for bonus buff");


        /**
         * bonus reward pops up within a random time limit
         */
        Timer timer=new Timer(33, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                megaCounter++;
                if(megaCounter ==rand){

                    show=!show;
                    megaCounter =0;
                }

            /**
             * detecting collison among player and enemies
             * decrease player score when colliding with enemy
             */
                if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(enemyX, enemyY,50,50))) {
                    drawEnemies = false;
                    hero.setHealth(0);
                    removeEnemies();
                    game.dispose();
                    endLoseScreen.create(hero.getHealth());

                }
                if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(enemyTwoX, enemyTwoY,50,50))) {
                    drawEnemies = false;
                    removeEnemies();
                    hero.setHealth(0);
                    game.dispose();
                    endLoseScreen.create(hero.getHealth());
                }
                if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(hardEnemyX, hardEnemyY,50,50))) {
                    drawEnemies = false;
                    removeEnemies();
                    hero.setHealth(0);
                    game.dispose();
                    endLoseScreen.create(hero.getHealth());
                }

                /**
                 * first enemy's line of sight
                 * keeps its distance so it doesn't overlap player
                 */
                if (Util.dist(hero.getX(), hero.getY(), enemyX, enemyY) < radius) {
                    if (Util.dist(hero.getX(), hero.getY(), enemyX, enemyY) >= MELEE_RANGE) {

                        //attack the player once player is seen
                        enemy.target(hero.getX(), hero.getY(), enemyX, enemyY, hero.getType(), enemy.getSpeed());
                        enemyX += enemy.getEnemyDirX();
                        enemyY += enemy.getEnemyDirY();
                        //enemy cannot see player through walls or move through them
                        if (new Rectangle(enemyX, enemyY, 40, 40).intersects(new Rectangle(780, 480,20,200))) {
                            enemyX -= enemy.getEnemyDirX();
                            enemyY -= enemy.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyX, enemyY, 40, 40).intersects(new Rectangle(80,0,20,160))) {
                            enemyX -= enemy.getEnemyDirX();
                            enemyY -= enemy.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyX, enemyY, 40, 40).intersects(new Rectangle(400,300,20,180))) {
                            enemyX -= enemy.getEnemyDirX();
                            enemyY -= enemy.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyX, enemyY, 40, 40).intersects(new Rectangle(420,300,200,20))) {
                            enemyX -= enemy.getEnemyDirX();
                            enemyY -= enemy.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyX, enemyY, 40, 40).intersects(new Rectangle(0,540,200,20))) {
                            enemyX -= enemy.getEnemyDirX();
                            enemyY -= enemy.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyX, enemyY, 40, 40).intersects(new Rectangle(800,100,400,20))) {
                            enemyX -= enemy.getEnemyDirX();
                            enemyY -= enemy.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyX, enemyY, 40, 40).intersects(new Rectangle(940,120,20,200))) {
                            enemyX -= enemy.getEnemyDirX();
                            enemyY -= enemy.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyX, enemyY, 40, 40).intersects(new Rectangle(100,80,200,20))) {
                            enemyX -= enemy.getEnemyDirX();
                            enemyY -= enemy.getEnemyDirY();
                        }

                    }
                }

                /**
                 * second enemy's line of sight
                 * keeps its distance so it doesn't overlap player
                 */
                if (Util.dist(hero.getX(), hero.getY(), enemyTwoX, enemyTwoY) < radius) {
                    if (Util.dist(hero.getX(), hero.getY(), enemyTwoX, enemyTwoY) >= MELEE_RANGE) {

                        //attack the player once player is seen
                        enemyTwo.target(hero.getX(), hero.getY(), enemyTwoX, enemyTwoY, hero.getType(), enemyTwo.getSpeed());
                        enemyTwoX += enemyTwo.getEnemyDirX();
                        enemyTwoY += enemyTwo.getEnemyDirY();

                        //enemy two cannot see player through walls or move through them
                        if (new Rectangle(enemyTwoX, enemyTwoY, 40, 40).intersects(new Rectangle(780, 480,20,200))) {
                            enemyTwoX -= enemyTwo.getEnemyDirX();
                            enemyTwoY -= enemyTwo.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyTwoX, enemyTwoY, 40, 40).intersects(new Rectangle(80,0,20,160))) {
                            enemyTwoX -= enemyTwo.getEnemyDirX();
                            enemyTwoY -= enemyTwo.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyTwoX, enemyTwoY, 40, 40).intersects(new Rectangle(400,300,20,180))) {
                            enemyTwoX -= enemyTwo.getEnemyDirX();
                            enemyTwoY -= enemyTwo.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyTwoX, enemyTwoY, 40, 40).intersects(new Rectangle(420,300,200,20))) {
                            enemyTwoX -= enemyTwo.getEnemyDirX();
                            enemyTwoY -= enemyTwo.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyTwoX, enemyTwoY, 40, 40).intersects(new Rectangle(0,540,200,20))) {
                            enemyTwoX -= enemyTwo.getEnemyDirX();
                            enemyTwoY -= enemyTwo.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyTwoX, enemyTwoY, 40, 40).intersects(new Rectangle(800,100,400,20))) {
                            enemyTwoX -= enemyTwo.getEnemyDirX();
                            enemyTwoY -= enemyTwo.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyTwoX, enemyTwoY, 40, 40).intersects(new Rectangle(940,120,20,200))) {
                            enemyTwoX -= enemyTwo.getEnemyDirX();
                            enemyTwoY -= enemyTwo.getEnemyDirY();
                        }
                        else if (new Rectangle(enemyTwoX, enemyTwoY, 40, 40).intersects(new Rectangle(100,80,200,20))) {
                            enemyTwoX -= enemyTwo.getEnemyDirX();
                            enemyTwoY -= enemyTwo.getEnemyDirY();
                        }
                    }
                }

                /**
                 * owl's line of sight
                 * keeps its distance so it doesn't overlap player
                 */
                if (Util.dist(hero.getX(), hero.getY(), hardEnemyX, hardEnemyY) < radius + 50) {
                    if (Util.dist(hero.getX(), hero.getY(), hardEnemyX, hardEnemyY) >= MELEE_RANGE) {

                        //attack the player once player is seen
                        hardEnemy.target(hero.getX(), hero.getY(), hardEnemyX, hardEnemyY, hero.getType(), hardEnemy.getSpeed());
                        hardEnemyX += hardEnemy.getEnemyDirX();
                        hardEnemyY += hardEnemy.getEnemyDirY();

                        //owl cannot see player through walls or move through them
                        if (new Rectangle(hardEnemyX, hardEnemyY, 40, 40).intersects(new Rectangle(780, 480,20,200))) {
                            hardEnemyX -= hardEnemy.getEnemyDirX();
                            hardEnemyY -= hardEnemy.getEnemyDirY();
                        }
                        else if (new Rectangle(hardEnemyX, hardEnemyY, 40, 40).intersects(new Rectangle(80,0,20,160))) {
                            hardEnemyX -= hardEnemy.getEnemyDirX();
                            hardEnemyY -= hardEnemy.getEnemyDirY();
                        }
                        else if (new Rectangle(hardEnemyX, hardEnemyY, 40, 40).intersects(new Rectangle(400,300,20,180))) {
                            hardEnemyX -= hardEnemy.getEnemyDirX();
                            hardEnemyY -= hardEnemy.getEnemyDirY();
                        }
                        else if (new Rectangle(hardEnemyX, hardEnemyY, 40, 40).intersects(new Rectangle(420,300,200,20))) {
                            hardEnemyX -= hardEnemy.getEnemyDirX();
                            hardEnemyY -= hardEnemy.getEnemyDirY();
                        }
                        else if (new Rectangle(hardEnemyX, hardEnemyY, 40, 40).intersects(new Rectangle(0,540,200,20))) {
                            hardEnemyX -= hardEnemy.getEnemyDirX();
                            hardEnemyY -= hardEnemy.getEnemyDirY();
                        }
                        else if (new Rectangle(hardEnemyX, hardEnemyY, 40, 40).intersects(new Rectangle(800,100,400,20))) {
                            hardEnemyX -= hardEnemy.getEnemyDirX();
                            hardEnemyY -= hardEnemy.getEnemyDirY();
                        }
                        else if (new Rectangle(hardEnemyX, hardEnemyY, 40, 40).intersects(new Rectangle(940,120,20,200))) {
                            hardEnemyX -= hardEnemy.getEnemyDirX();
                            hardEnemyY -= hardEnemy.getEnemyDirY();
                        }
                        else if (new Rectangle(hardEnemyX, hardEnemyY, 40, 40).intersects(new Rectangle(100,80,200,20))) {
                            hardEnemyX -= hardEnemy.getEnemyDirX();
                            hardEnemyY -= hardEnemy.getEnemyDirY();
                        }
                    }
                }

                repaint();

                }

        });
        timer.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;

        //background
        g.drawImage(bufferedBackground, 0,0,1200,800,null);

        //create buff1 for player to collect
        if(map.buff1Check()) {
            g.drawImage(bufferedCheese,180,220,50,50,this);
        }

        //create buff2 for player to collect
        if(map.buff2Check()) {
            g.drawImage(bufferedCheese,600,380,50,50,this);
        }

        //create buff3 for player to collect
        if(map.buff3Check()) {
            g.drawImage(bufferedCheese,400,60,50,50,this);
        }

        //set traps
        if (drawTrap)
            g.drawImage(bufferedTrap,900,540,50,50,this);
        if (drawTrapTwo)
            g.drawImage(bufferedTrapSecond,240,420,50,50,this);

        //create bonus reward
        if(map.checkMega() && show){
            map.showMega();
            g.drawImage(bonusReward,1100,40,50,50, this);
        }
        else {
            map.hideMega();

        }

        //creates the exit location
        Rectangle2D exit= new Rectangle2D.Double(1080,580,100,100);
        g2d.setPaint(Color.YELLOW);
        g2d.fill(exit);


        //creates the player and rotations due to key press
        if (drawUp()) {
            g.drawImage(bufferedPlayerUp, hero.getX(),hero.getY(),50,50,this);
        }
        else if (drawDown()) {
            g.drawImage(bufferedPlayerDown, hero.getX(),hero.getY(),50,50,this);
        }
        else if (drawRight()) {
            g.drawImage(bufferedPlayerRight, hero.getX(),hero.getY(),50,50,this);
        }
        else if (drawLeft()) {
            g.drawImage(bufferedPlayerLeft, hero.getX(),hero.getY(),50,50,this);
        }
        else {
            g.drawImage(bufferedPlayer,hero.getX(),hero.getY(), 50,50,this);
        }

        //draw enemy if it does not collide with player
        if (drawEnemies) {
            //enemy
            g.drawImage(bufferedBirds,enemyX,enemyY,50,50,this);

            //enemy two
            g.drawImage(bufferedBirds,enemyTwoX,enemyTwoY,50,50,this);

            //Hard enemy
            g.drawImage(owl,hardEnemyX,hardEnemyY,60,60,this);
        }




        //generate walls
        g.drawImage(bufferedWallsVertical,80,0,20,160,this);

        g.drawImage(bufferedWallsVertical,400,300,20,180,this);

        g.drawImage(bufferedWallsHorizontal,420,300,200,20,this);

        g.drawImage(bufferedWallsHorizontal,0,540,200,20,this);

        g.drawImage(bufferedWallsHorizontal,800,100,400,20,this);

        g.drawImage(bufferedWallsVertical,780,480,20,200,this);

        g.drawImage(bufferedWallsVertical,940,120,20,200,this);

        g.drawImage(bufferedWallsHorizontal,100,80,200,20,this);

        //draw score board onto game
        g2d.setColor(Color.BLACK);
        Font font = new Font("Verdana", Font.BOLD, 25);
        g2d.setFont(font);
        g2d.drawString("SCORE: "+ hero.getHealth(),0,660);



    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    /**
     * checks WASD keys per tick by player
     * @param e detects player key pressed
     */
    @Override
    public void keyPressed (KeyEvent e){
        //checks player's UP location if there is a wall between player
        if(e.getKeyCode() == KeyEvent.VK_W){
            switchUp = true;
            switchLeft = false;
            switchRight = false;
            switchDown = false;
            if(!map.checkWallUp()) {
                hero.moveUp();
            }
            if (map.checkTrap()){
                System.out.println("trap");
                hero.setHealth(score -= 5);

                //if player lands on trap, erase it from the board
                if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(900, 540,50,50))) {
                    drawTrap = false;
                    setTrapTwoToFreeSpace();
                }
                else if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(240, 420,50,50))) {
                    drawTrapTwo = false;
                    setTrapOneToFreeSpace();
                }

            }
            if(map.changeMega()){
                System.out.println("MEGA");
                hero.setHealth(score +=50);
            }
            if(map.changeBuff1()){
                System.out.println("buff1");
                hero.setHealth(score +=10);
            }
            if(map.changeBuff2()){
                System.out.println("buff2");
                hero.setHealth(score +=10);
            }
            if(map.changeBuff3()){
                System.out.println("buff3");
                hero.setHealth(score +=10);
            }
            if(map.checkExit()){
                if(!map.buff1Check() && !map.buff2Check() && !map.buff3Check()) {
                    System.out.println("exit");
                    //YOU WIN SCREEN
                    removeEnemies();
                    game.dispose();
                    endWinScreen.create(hero.getHealth());
                }
            }
        }

        //checks player's DOWN location if there is a wall between player
        if(e.getKeyCode() == KeyEvent.VK_S){
            switchDown = true;
            switchUp = false;
            switchLeft = false;
            switchRight = false;
            if(!map.checkWallDown()) {
                hero.moveDown();
            }
            if (map.checkTrap()){
                System.out.println("trap");
                hero.setHealth(score -= 5);

                //if player lands on trap, erase it from the board
                if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(900, 540,50,50))) {
                    drawTrap = false;
                    setTrapTwoToFreeSpace();
                }
                else if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(240, 420,50,50))) {
                    drawTrapTwo = false;
                    setTrapOneToFreeSpace();
                }
            }
            if(map.changeMega()){
                System.out.println("MEGA");
                hero.setHealth(score += 50);
            }
            if(map.changeBuff1()){
                System.out.println("buff1");
                hero.setHealth(score +=10);
            }
            if(map.changeBuff2()){
                System.out.println("buff2");
                hero.setHealth(score +=10);
            }
            if(map.changeBuff3()){
                System.out.println("buff3");
                hero.setHealth(score +=10);

            }
            if(map.checkExit()){
                if(!map.buff1Check() && !map.buff2Check() && !map.buff3Check()) {
                    System.out.println("exit");
                    //YOU WIN SCREEN
                    removeEnemies();
                    game.dispose();
                    endWinScreen.create(hero.getHealth());
                }

            }
        }

        //checks player's LEFT location if there is a wall between player
        if(e.getKeyCode() == KeyEvent.VK_A){
            switchLeft = true;
            switchDown = false;
            switchUp = false;
            switchRight = false;
            if(!map.checkWallLeft()) {
                hero.moveLeft();
            }
            if (map.checkTrap()){
                System.out.println("trap");
                hero.setHealth(score -= 5);

                //if player lands on trap, erase it from the board
                if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(900, 540,50,50))) {
                    drawTrap = false;
                    setTrapTwoToFreeSpace();
                }
                else if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(240, 420,50,50))) {
                    drawTrapTwo = false;
                    setTrapOneToFreeSpace();
                }

            }
            if(map.changeMega()){
                System.out.println("MEGA");
                hero.setHealth(score += 50);
            }
            if(map.changeBuff1()){
                System.out.println("buff1");
                hero.setHealth(score +=10);
            }
            if(map.changeBuff2()){
                System.out.println("buff2");
                hero.setHealth(score +=10);
            }
            if(map.changeBuff3()){
                System.out.println("buff3");
                hero.setHealth(score +=10);
            }
            if(map.checkExit()){
                if(!map.buff1Check() && !map.buff2Check() && !map.buff3Check()) {
                    System.out.println("exit");
                    //YOU WIN SCREEN
                    removeEnemies();
                    game.dispose();
                    endWinScreen.create(hero.getHealth());

                }
            }

        }

        //checks player's RIGHT location if there is a wall between player
        if(e.getKeyCode() == KeyEvent.VK_D) {
            switchRight = true;
            switchDown = false;
            switchUp = false;
            switchLeft = false;
            if(!map.checkWallRight()) {
                hero.moveRight();
            }
            if (map.checkTrap()) {
                System.out.println("trap");
                hero.setHealth(score -= 5);

                //if player lands on trap, erase it from the board
                if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(900, 540, 50, 50))) {
                    drawTrap = false;
                    setTrapTwoToFreeSpace();
                } else if (new Rectangle(hero.getX(), hero.getY(), 50, 50).intersects(new Rectangle(240, 420, 50, 50))) {
                    drawTrapTwo = false;
                    setTrapOneToFreeSpace();
                }
            }
            if(map.changeMega()){
                System.out.println("MEGA");
                hero.setHealth(score +=50);
            }

            if(map.changeBuff1()){
                System.out.println("buff1");
                hero.setHealth(score +=10);
            }
            if(map.changeBuff2()){
                System.out.println("buff2");
                hero.setHealth(score +=10);
            }
            if(map.changeBuff3()){
                System.out.println("buff3");
                hero.setHealth(score +=10);
            }
            if(map.checkExit()){
                if(!map.buff1Check() && !map.buff2Check() && !map.buff3Check()) {
                    System.out.println("exit");
                    //YOU WIN SCREEN
                    removeEnemies();
                    game.dispose();
                    endWinScreen.create(hero.getHealth());
                }
            }

        }
        if(hero.getHealth() == 0){
            //game over screen
            removeEnemies();
            game.dispose();
            endLoseScreen.create(hero.getHealth());
        }

    }

    //remove enemies after game is done
    public void removeEnemies() {
        enemyX = -20;
        enemyY = -20;
        enemyTwoX = -20;
        enemyTwoY = -20;
        hardEnemyX = -20;
        hardEnemyY = -20;
    }

    //remove trap one when player walks on it
    public void setTrapOneToFreeSpace() {
        map.board[20][12] = map.FREE_SPACE;
        map.board[20][13] = map.FREE_SPACE;
        map.board[21][12] = map.FREE_SPACE;
        map.board[21][13] = map.FREE_SPACE;
    }

    //remove trap two when player walks on it
    public void setTrapTwoToFreeSpace() {
        map.board[26][45] = map.FREE_SPACE;
        map.board[26][46] = map.FREE_SPACE;
        map.board[27][45] = map.FREE_SPACE;
        map.board[27][46] = map.FREE_SPACE;
    }

    /**
     * created method to determine where the player is looking after each tick
     * @return returns boolean values after detecting if a certain key was pressed
     */
    public boolean drawUp() {
        return switchUp;
    }
    public boolean drawDown() {
        return switchDown;
    }
    public boolean drawRight() {
        return switchRight;
    }
    public boolean drawLeft() {
        return switchLeft;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

}
