package edu.tucn.pt.controller;

import edu.tucn.pt.view.SwingUI;

/**
 * Clock class
 */
public class Clock implements Runnable {

    private final Thread clockThread;
    private int time;
    private boolean isRunning;
    private final SwingUI swingUI;

    /**
     * @param swingUI SwingUI object
     */
    public Clock(SwingUI swingUI) {
        clockThread = new Thread(this);
        isRunning = false;
        time = 0;
        this.swingUI = swingUI;
    }

    /**
     * Starts the clock
     */
    public void startClock() {
        isRunning = true;
        time = 0;
        clockThread.start();
    }

    /**
     * Stops the clock
     */
    public void stopClock() {
        isRunning = false;
    }

    /**
     * @return the current time
     */
    public int getTime() {
        return time;
    }

    /**
     *
     */
    @Override
    public void run() {
        while (isRunning) {
            try {
                int currentTime = getTime();
                String currentTimeString = currentTime + "/" + swingUI.getTimeMax();
                swingUI.updateCurrentTime(currentTimeString);
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
    }
}
