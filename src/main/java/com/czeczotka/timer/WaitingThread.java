package com.czeczotka.timer;


/**
 * Thread used for waiting particular amount of time. 
 * 
 * @author Jakub Czeczotka
 */
public class WaitingThread extends Thread {

    private int time;
    private TimerFrame frame;

    public WaitingThread (int time, TimerFrame frame) {
        this.time = time;
        this.frame = frame;
    }

    public void run ( ) {
        long firstTime = System.currentTimeMillis ();
        long secondTime = 0;
        int now = 0;
        try {
            for (int i = 0; i < time; i++) {
                if (isInterrupted ()) {
                    return;
                }
                secondTime = System.currentTimeMillis ();
                now = (int) ((secondTime - firstTime) / 1000);
                frame.setProgress (now);
                if (now > time) {
                    break;
                }
                Thread.sleep (1000);
            }
            frame.finished ();			
        } catch (InterruptedException ignore) {
        }
    }
}