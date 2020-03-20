package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Play extends JPanel {

    //initialize JPanel and JFrame
    private JPanel redo;
    private JFrame game;

    /**
     * Creates the visual game board
     */
    public Play(){
        game = new JFrame();

        //grab game timer
        GameTime gameTime=new GameTime();

        this.setLayout(new BorderLayout());

        Graphic graphic=new Graphic();

        //use bots to divide panels
        JPanel bot= new JPanel();
        bot.setBackground(Color.LIGHT_GRAY);

        //split buttom into two
        JPanel botDivided= new JPanel();
        botDivided.setBackground(Color.LIGHT_GRAY);
        botDivided.setLayout(new GridLayout(1,2,400,0));

        //quit button to quit the game
        JButton quit= new JButton("Quit Game");

        //quit button closes the game after clicked on
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        //quit button attributes
        quit.setBackground(Color.DARK_GRAY);
        quit.setForeground(Color.BLACK);
        quit.setFont(quit.getFont().deriveFont(30.0f));

        //left panel for the time component
        JPanel botLeft= new JPanel();
        botLeft.setBackground(Color.LIGHT_GRAY);

        //setting time onto buttom left panel
        JLabel time = new JLabel();
        time.setForeground(Color.white);
        time.setFont(time.getFont().deriveFont(50.0f));
        Timer timer= gameTime.PlayTime(time);

        //add the time component onto the left panel
        botLeft.add(time);

        botDivided.add(botLeft);
        botDivided.add(quit);

        bot.add(botDivided);

        this.add(graphic,BorderLayout.CENTER);
        this.add(bot,BorderLayout.SOUTH);


        redo=this;

    }


}