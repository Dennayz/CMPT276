package game.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class holds the Start screen as it also creates the game frame for the next state when the user hits the start
 * button on the Start screen, also includes a Quit button if the user decides the leave right away.
 * The nature of this class is giving the user a more practical and structural look of the game requiring a start menu
 * before the actual game starts. The Start screen in this class will also provide the user with instructional
 * explanation of the game and how it works, the goal, and how to win. The constructor method in this class will be
 * called in the Frame Class, assembling this class's JPanel onto the Frame's class JFrame since that class is a member
 * of the JFrame API.
 */
public class Start extends JPanel{

    /**initialize all variables */
    protected static JFrame game;
    private JButton startButton;
    private JButton exitButton;
    private JLabel background;

    /**
     * This is the Frame that holds the game
     * @param frame gets called by Frame
     */
    public Start(final JFrame frame){

        // Initialize screens
        ImageLoader.init();

        // Intialize both start and quit buttons
        startButton = new JButton("");
        startButton.setName("start");
        exitButton = new JButton("");
        exitButton.setName("exit");

        // Give start button action to open main game frame
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game = new JFrame();

                game.setSize(1200,800);
                game.setLocationRelativeTo(null);
                game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                game.setVisible(true);
                game.add(new Play());
                game.setResizable(false);
                game.setPreferredSize(new Dimension(1200,800));
                game.pack();

                // Do not show start screen when main game is shown
                frame.dispose();

            }
        });

        // Exit button closes the game
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        // Start button attributes
        startButton.setBounds(280, 260, 250, 80);
        startButton.setName("start");
        startButton.setContentAreaFilled(false);
        startButton.setBorder(null);

        // Exit button attributes
        exitButton.setBounds(280,377,250,80);
        exitButton.setName("exit");
        exitButton.setContentAreaFilled(false);
        exitButton.setBorder(null);

        // Creates start screen background
        background= new JLabel(ImageLoader.startScreen);
        background.add(startButton);
        background.add(exitButton);
        this.add(background);


    }




}
