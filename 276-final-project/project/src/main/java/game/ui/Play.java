package game.ui;

import game.logic.GameTime;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the user interface of the game, meaning that every feature including the JPanels in the
 * bottom part of the game screen is constructed here. The Game timer is again called in here to be shown in the bottom
 * left corner of the screen with modified features to make the timer more visible and prominent.
 * <p>
 * This class is called by the Start class since everything in this class is a panel and a JPanel needs to be set
 * in a frame. The Start class will create a JFrame and run this panel when the user hits the start button in the Start
 * class. This panel will show up as a result. Hence, everything the user interacts with and see will be inside this panel.
 */
public class Play extends JPanel {

    /**
     * Initialize panel.
     */

    private JPanel redo;
    private GameTime gameTime;
    private Graphic graphic;
    private JPanel bot;
    private JPanel botDivided;
    private JButton quit;
    private JPanel botLeft;
    private JLabel time;

    /**
     * Creates the visual game board
     */
    public Play(){
        // Grab game timer
        gameTime= new GameTime();

        this.setLayout(new BorderLayout());

        graphic= new Graphic();

        // Use bots to divide panels
        bot= new JPanel();
        bot.setBackground(Color.LIGHT_GRAY);

        // Split bottom into two
        botDivided= new JPanel();
        botDivided.setBackground(Color.LIGHT_GRAY);
        botDivided.setLayout(new GridLayout(1,2,400,0));

        // Quit button to quit the game
        quit= new JButton("Quit Game");

        // Quit button closes the game after clicked on
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        // Quit button attributes
        quit.setBackground(Color.DARK_GRAY);
        quit.setForeground(Color.BLACK);
        quit.setFont(quit.getFont().deriveFont(30.0f));

        // Left panel for the time component
        botLeft = new JPanel();
        botLeft.setBackground(Color.LIGHT_GRAY);

        // Setting time onto bottom left panel
        time = new JLabel();
        time.setForeground(Color.white);
        time.setFont(time.getFont().deriveFont(50.0f));
        gameTime.playTime(time);

        // Add the time component onto the left panel
        botLeft.add(time);

        botDivided.add(botLeft);
        botDivided.add(quit);

        bot.add(botDivided);

        this.add(graphic,BorderLayout.CENTER);
        this.add(bot,BorderLayout.SOUTH);


        redo = this;

    }



}