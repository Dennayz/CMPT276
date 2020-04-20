package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import game.ui.ImageLoader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * This class holds all the sprites and entities in the user interface of the game. It is testing if the ImageLoader
 * class actually sets each of the instances to the correct images that should be loaded into the game. The purpose is
 * to make sure that all generated image files are done properly.
 * <p>
 * All sprites are initialized to null and that will be tested first. The setUp method will test and set all images
 * to their corresponding instances which can be found in the resource folder under test directory. Once the images are
 * set, assertNotNull all variables to see if they are still null or not. This will give the user an idea that the
 * images are no longer appointed to the null pointer and may be possibly set to an image file. For BufferedImages,
 * using manual testing was enough to see if the each instances had at least one or two correct attributes.
 * Manual testing if the each image matches the correspondent width and height.
 *
 * @author Dennis Zhang
 */
public class ImageLoaderTest {

    /** Assign all images to correct image file for testing */
    @BeforeEach
    public void setUp() throws IOException {

        ImageLoader.endWinScreen = new ImageIcon("src/test/resources/winScreen.png");
        ImageLoader.endLoseScreen = new ImageIcon("src/test/resources/GameOver.png");
        ImageLoader.startScreen = new ImageIcon("src/test/resources/startScreen.png");

        ImageLoader.bufferedBackground = ImageIO.read(new File("src/test/resources/grassBackground.png"));
        ImageLoader.bufferedWallsVertical = ImageIO.read(new File("src/test/resources/vwall.png"));
        ImageLoader.bufferedWallsHorizontal = ImageIO.read(new File("src/test/resources/hwall.png"));
        ImageLoader.bufferedCheese = ImageIO.read(new File("src/test/resources/cheese_sprite.png"));
        ImageLoader.bufferedPlayer = ImageIO.read((new File("src/test/resources/mouse.png")));
        ImageLoader.bufferedTrap = ImageIO.read(new File("src/test/resources/trap.png"));
        ImageLoader.bufferedTrapSecond =  ImageIO.read(new File("src/test/resources/trap.png"));

        ImageLoader.bufferedPlayerDown = ImageIO.read(new File("src/test/resources/mouseDown.png"));
        ImageLoader.bufferedPlayerUp = ImageIO.read(new File("src/test/resources/mouseUp.png"));
        ImageLoader.bufferedPlayerRight = ImageIO.read(new File("src/test/resources/mouseRight.png"));
        ImageLoader.bufferedPlayerLeft = ImageIO.read(new File("src/test/resources/mouseLeft.png"));

        ImageLoader.owl = new ImageIcon(new URL("https://2.bp.blogspot.com/-W8eX-eT-q8w/W4vHy5WQ7tI/AAAAAAAav1I/W7IwxgzMHW0iBw_uXXoBcja5bVhirShTQCLcBGAs/s1600/AW1686559_17.gif")).getImage();
        ImageLoader.bonusReward = new ImageIcon(new URL("https://gifimage.net/wp-content/uploads/2017/11/gold-stars-gif-6.gif")).getImage();
        ImageLoader.bufferedBirds = new ImageIcon(new URL("https://webstockreview.net/images/collaboration-clipart-animated-gif-19.gif")).getImage();
        ImageLoader.openPortal = new ImageIcon(new URL("https://thumbs.gfycat.com/SickEnchantingAdamsstaghornedbeetle-small.gif")).getImage();
        ImageLoader.closePortal = new ImageIcon(new URL("https://thumbs.gfycat.com/GlossySmoggyEland-small.gif")).getImage();
    }

    /**
     * Check if images are still appointed to null pointer.
     * Check if expected image width and height are equal to the actual image file width and height.
     */
    @Test
    public void init() {
        assertAll("Checking if all images are not set to null anymore",
                () -> assertNotNull(ImageLoader.bufferedWallsHorizontal),
                () -> assertNotNull(ImageLoader.bufferedBackground),
                () -> assertNotNull(ImageLoader.bufferedWallsVertical),
                () -> assertNotNull(ImageLoader.bufferedCheese),
                () -> assertNotNull(ImageLoader.bufferedPlayer),
                () -> assertNotNull(ImageLoader.bufferedTrap),
                () -> assertNotNull(ImageLoader.bufferedTrapSecond),
                () -> assertNotNull(ImageLoader.bufferedPlayerDown),
                () -> assertNotNull(ImageLoader.bufferedPlayerUp),
                () -> assertNotNull(ImageLoader.bufferedPlayerRight),
                () -> assertNotNull(ImageLoader.bufferedPlayerLeft),
                () -> assertNotNull(ImageLoader.owl),
                () -> assertNotNull(ImageLoader.bonusReward),
                () -> assertNotNull(ImageLoader.bufferedBirds),
                () -> assertNotNull(ImageLoader.openPortal),
                () -> assertNotNull(ImageLoader.closePortal),
                () -> assertNotNull(ImageLoader.startScreen),
                () -> assertNotNull(ImageLoader.endLoseScreen),
                () -> assertNotNull(ImageLoader.endWinScreen)
        );

        assertAll("Manual testing if the image size is correct",
                () ->  assertEquals(1200, ImageLoader.bufferedWallsHorizontal.getWidth()),
                () ->  assertEquals(626, ImageLoader.bufferedWallsHorizontal.getHeight()),

                () -> assertEquals(512, ImageLoader.bufferedCheese.getWidth()),
                () -> assertEquals(410, ImageLoader.bufferedCheese.getHeight()),

                () -> assertEquals(1200, ImageLoader.bufferedBackground.getWidth()),
                () -> assertEquals(800, ImageLoader.bufferedBackground.getHeight()),

                () -> assertEquals(20, ImageLoader.bufferedWallsVertical.getWidth()),
                () -> assertEquals(160, ImageLoader.bufferedWallsVertical.getHeight()),

                () -> assertEquals(712, ImageLoader.bufferedTrap.getWidth()),
                () -> assertEquals(712, ImageLoader.bufferedTrap.getHeight()),

                () -> assertEquals(848, ImageLoader.bufferedPlayer.getWidth()),
                () -> assertEquals(972, ImageLoader.bufferedPlayer.getHeight()),

                () -> assertEquals(848, ImageLoader.bufferedPlayerDown.getWidth()),
                () -> assertEquals(972, ImageLoader.bufferedPlayerDown.getHeight()),

                () -> assertEquals(972, ImageLoader.bufferedPlayerLeft.getWidth()),
                () -> assertEquals(848, ImageLoader.bufferedPlayerLeft.getHeight()),

                () -> assertEquals(972, ImageLoader.bufferedPlayerRight.getWidth()),
                () -> assertEquals(848, ImageLoader.bufferedPlayerRight.getHeight()),

                () -> assertEquals(848, ImageLoader.bufferedPlayerUp.getWidth()),
                () -> assertEquals(972, ImageLoader.bufferedPlayerUp.getHeight()),

                () -> assertEquals(370, ImageLoader.owl.getWidth(null)),
                () -> assertEquals(300, ImageLoader.owl.getHeight(null)),

                () -> assertEquals(518, ImageLoader.bonusReward.getWidth(null)),
                () -> assertEquals(507, ImageLoader.bonusReward.getHeight(null)),

                () -> assertEquals(625, ImageLoader.bufferedBirds.getWidth(null)),
                () -> assertEquals(625, ImageLoader.bufferedBirds.getHeight(null)),

                () -> assertEquals(264, ImageLoader.openPortal.getWidth(null)),
                () -> assertEquals(266, ImageLoader.openPortal.getHeight(null)),

                () -> assertEquals(200, ImageLoader.closePortal.getWidth(null)),
                () -> assertEquals(200, ImageLoader.closePortal.getHeight(null)),

                () -> assertEquals(800, ImageLoader.endLoseScreen.getIconWidth()),
                () -> assertEquals(600, ImageLoader.endLoseScreen.getIconHeight()),

                () -> assertEquals(800, ImageLoader.endWinScreen.getIconWidth()),
                () -> assertEquals(600, ImageLoader.endWinScreen.getIconHeight()),

                () -> assertEquals(800, ImageLoader.startScreen.getIconWidth()),
                () -> assertEquals(600, ImageLoader.startScreen.getIconHeight())

        );
    }

}