package edu.tucn.pt.model;

import edu.tucn.pt.view.SwingUI;
import edu.tucn.pt.controller.Clock;

import java.util.ArrayList;
import java.util.List;

/**
 * ServiceQueue class
 */
public class ServiceQueue implements Runnable {

    private final List<Client> clientsList;
    private final int queueIndex;
    private boolean isRunning;
    private int totalClientsEver;
    private int deadQueueTime;
    private int totalWaitingTime;
    private final Clock clock;
    private final Thread queueThread;
    private final SwingUI swingUI;

    /**
     * @param index queue index
     * @param timer Clock object
     * @param swingUI SwingUI object
     */
    public ServiceQueue(int index, Clock timer, SwingUI swingUI) {
        this.clientsList = new ArrayList<>();
        this.queueIndex = index;
        this.isRunning = false;
        this.totalClientsEver = 0;
        this.deadQueueTime = 0;
        this.totalWaitingTime = 0;
        this.clock = timer;
        this.queueThread = new Thread(this);
        this.swingUI = swingUI;

        start();
    }

    /**
     * @return the total number of clients that arrived at this queue
     * during the simulation.
     */
    public int getNumClientsInQueue() {
        return clientsList.size();
    }

    public int getQueueIndex() {
        return queueIndex;
    }

    /**
     * @param myClient Client object
     *                 Adds a client to the queue
     *                 and updates the logs and the queue
     *                 in the SwingUI.
     */
    public void addClientToQueue(Client myClient) {
        totalClientsEver++;
        clientsList.add(myClient);
        String logMessage = "Client" + myClient.getClientId() + " arrived at queue " + queueIndex;
        swingUI.updateLogs(logMessage);
        swingUI.updateQueue(queueIndex, getNumClientsInQueue());
    }

    /**
     * @param myClient Client object
     *                 Removes a client from the queue
     *                 and updates the logs and the queue
     *                 in the SwingUI.
     */
    public void removeClientFromQueue(Client myClient) {
        clientsList.remove(myClient);
        String logMessage = "Client" + myClient.getClientId() + " left queue " + queueIndex;
        swingUI.updateLogs(logMessage);
        swingUI.updateQueue(queueIndex, getNumClientsInQueue());
    }

    /**
     * the total number of clients that arrived at this queue
     * during the simulation.
     * This method is used in the SwingUI.
     */
    @Override
    public void run() {
        while (isRunning || !clientsList.isEmpty()) {
            if (!clientsList.isEmpty()) {
                Client currentClient = clientsList.get(0);
                int serviceTime = currentClient.getServiceTime();
                totalWaitingTime += clock.getTime() - currentClient.getArrivalTime();

                try {
                    Thread.sleep(serviceTime * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                removeClientFromQueue(currentClient);

            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                deadQueueTime++;
            }
        }
        if (totalClientsEver > 0) {
            swingUI.updateMinimumOutput(
                    "Average waiting time for queue " + queueIndex + " : " + totalWaitingTime / (totalClientsEver * 1.0));
        }
        swingUI.updateMinimumOutput("Queue " + queueIndex + " is empty, total dead time =" + deadQueueTime);

    }

    /**
     * Starts the queue
     */
    public void start() {
        isRunning = true;
        queueThread.start();
    }

    /**
     * Stops the queue
     */
    public void stop() {
        isRunning = false;
    }
}
