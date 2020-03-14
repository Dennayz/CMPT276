package game;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    //initialize all timer components
    public int secondsRight;
    public int secondsLeft;
    public int minutes;
    public Timer timer;
    public TimerTask timerTask;

    private void runTimer() {
        secondsRight = 0;
        secondsLeft = 0;
        minutes = 0;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                secondsRight++;
                //changing each time components to correctly format a timer
                if (secondsRight < 10) {
                    System.out.println("Time passed: " + minutes + " : " + secondsLeft + secondsRight);
                }
                else {
                    secondsRight = 0;
                    secondsLeft++;
                    System.out.println("Time passed: " + minutes + " : " + secondsLeft + secondsRight);
                }
                if (secondsLeft > 4 && secondsRight > 8) {
                    secondsRight = 0;
                    secondsLeft = 0;
                    minutes++;
                    System.out.println("Time passed: " + minutes + " : " + secondsLeft + secondsRight);
                }

            }
        };
        //timer execution at a fixed rate again and again
        timer.scheduleAtFixedRate(timerTask, 1000,1000);
    }

    public void start() {
        runTimer();
    }
    //Timer needs to be recreated as another instance because timer.cancel kills the timer for good
    public void stop() {
        timer.cancel();
    }

    public void restart() {
        start();
    }
}
