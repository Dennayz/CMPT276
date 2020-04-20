package game.ui;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * The purpose of this class is to load in all images for the Graphics of the game. Any changes in this class will be
 * updated in the Graphics class as to which images are changed. If adding additional Images to this class, user needs
 * to draw it in the Graphics class to ensure it gets displayed into the game. This class is solely on importing all
 * required images and checks if each image loads by using a try and catch block. Make sure all new images and
 * updated images are thrown into the resources folder.
 */
public class ImageLoader {
    /**
     * Set up all images.
     */

    // Load Screens
    public static ImageIcon startScreen = null;
    public static ImageIcon endLoseScreen = null;
    public static ImageIcon endWinScreen = null;

    // Import sprites
    public static BufferedImage bufferedBackground = null;
    public static BufferedImage bufferedWallsVertical = null;
    public static BufferedImage bufferedWallsHorizontal = null;
    public static BufferedImage bufferedCheese = null;

    public static BufferedImage bufferedPlayer = null;
    public static BufferedImage bufferedPlayerDown = null;
    public static BufferedImage bufferedPlayerUp = null;
    public static BufferedImage bufferedPlayerRight = null;
    public static BufferedImage bufferedPlayerLeft = null;

    public static BufferedImage bufferedTrap = null;
    public static BufferedImage bufferedTrapSecond = null;

    // Import sprite animation
    public static Image owl = null;
    public static Image bonusReward = null;
    public static Image bufferedBirds = null;
    public static Image openPortal = null;
    public static Image closePortal = null;

    /**
     * Initialize all images and check if they are found within the resource folder.
     * If not, throw an exception.
     */
    public static void init() {
        /**
         * Add in all image sprites to game when game loads
         * @exception IOException When Input file is not found
         */
        try {

            startScreen = new ImageIcon("src/main/resources/startScreen.png");
            endLoseScreen = new ImageIcon("src/main/resources/gameOver.png");
            endWinScreen = new ImageIcon("src/main/resources/WinScreen.png");

            bufferedBackground = ImageIO.read(new File("src/main/resources/grassBackground.png"));
            bufferedWallsVertical = ImageIO.read(new File("src/main/resources/vwall.png"));
            bufferedWallsHorizontal = ImageIO.read(new File("src/main/resources/hwall.png"));
            bufferedCheese = ImageIO.read(new File("src/main/resources/cheese_sprite.png"));
            bufferedPlayer = ImageIO.read((new File("src/main/resources/mouse.png")));
            bufferedTrap = ImageIO.read(new File("src/main/resources/trap.png"));
            bufferedTrapSecond =  ImageIO.read(new File("src/main/resources/trap.png"));

            bufferedPlayerDown = ImageIO.read(new File("src/main/resources/mouseDown.png"));
            bufferedPlayerUp = ImageIO.read(new File("src/main/resources/mouseUp.png"));
            bufferedPlayerRight = ImageIO.read(new File("src/main/resources/mouseRight.png"));
            bufferedPlayerLeft = ImageIO.read(new File("src/main/resources/mouseLeft.png"));

            owl = new ImageIcon(new URL("https://2.bp.blogspot.com/-W8eX-eT-q8w/W4vHy5WQ7tI/AAAAAAAav1I/W7IwxgzMHW0iBw_uXXoBcja5bVhirShTQCLcBGAs/s1600/AW1686559_17.gif")).getImage();
            bonusReward = new ImageIcon(new URL("https://gifimage.net/wp-content/uploads/2017/11/gold-stars-gif-6.gif")).getImage();
            bufferedBirds = new ImageIcon(new URL("https://webstockreview.net/images/collaboration-clipart-animated-gif-19.gif")).getImage();
            openPortal = new ImageIcon(new URL("https://thumbs.gfycat.com/SickEnchantingAdamsstaghornedbeetle-small.gif")).getImage();
            closePortal = new ImageIcon(new URL("https://thumbs.gfycat.com/GlossySmoggyEland-small.gif")).getImage();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}