package game;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * This class holds the in game timer
 */
public class GameTime {
    private int secondsPlay;
    protected static long minute;
    protected static long second;

    public Timer PlayTime(final JLabel label){
        secondsPlay = 0;

        Timer time= new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                secondsPlay++;
                minute = TimeUnit.SECONDS.toMinutes(secondsPlay) - (TimeUnit.SECONDS.toHours(secondsPlay) * 60);
                second = TimeUnit.SECONDS.toSeconds(secondsPlay) - (TimeUnit.SECONDS.toMinutes(secondsPlay) * 60);


                if(second<10){
                    label.setText("TIME: " +minute + ":0" + second);
                }
                else {
                    label.setText("TIME: " + minute + ":" + second);
                }
            }
        });
        time.start();

        return time;
    }





}
