package edu.tucn.pt.controller;

import edu.tucn.pt.view.SwingUI;

public class Clock implements Runnable {

    private final Thread clockThread;
    private int time;
    private boolean isRunning;
    private final SwingUI userInterface;

    public Clock(SwingUI userInterface) {
        clockThread = new Thread(this);
        isRunning = false;
        time = 0;
        this.userInterface = userInterface;
    }

    public void startClock() {
        isRunning = true;
        time = 0;
        clockThread.start();
    }

    public void stopClock() {
        isRunning = false;
    }

    public int getTime() {
        return time;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                int currentTime = getTime();
                String currentTimeString = currentTime + "/" + userInterface.getTimeMax();
                userInterface.updateCurrentTime(currentTimeString);
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
    }
}
