package com.czeczotka.timer;

/**
 * Thread used for waiting particular amount of time. 
 * 
 * @author Jakub Czeczotka
 */
public class WaitingThread extends Thread {

    private final int time;
    private final TimerFrame frame;

    public WaitingThread (int time, TimerFrame frame) {
        this.time = time;
        this.frame = frame;
    }

    @Override
    public void run ( ) {
        long firstTime = System.currentTimeMillis ();
        long secondTime;
        int now;
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