package edu.tucn.pt.model;

import edu.tucn.pt.view.SwingUI;
import edu.tucn.pt.controller.Clock;

import java.util.ArrayList;
import java.util.List;

public class ServiceQueue implements Runnable {

    private final List<Client> clientsList;
    private final int queueIndex;
    private boolean isRunning;
    private int totalClientsEver;
    private int deadQueueTime;
    private int totalWaitingTime;
    private final Clock clock;
    private final Thread queueThread;
    private final SwingUI userInterface;

    public ServiceQueue(int index, Clock timer, SwingUI userInterface) {
        this.clientsList = new ArrayList<>();
        this.queueIndex = index;
        this.isRunning = false;
        this.totalClientsEver = 0;
        this.deadQueueTime = 0;
        this.totalWaitingTime = 0;
        this.clock = timer;
        this.queueThread = new Thread(this);
        this.userInterface = userInterface;

        start();
    }

    public int getNumClientsInQueue() {
        return clientsList.size();
    }

    public int getQueueIndex() {
        return queueIndex;
    }

    public void addClientToQueue(Client myClient) {
        totalClientsEver++;
        clientsList.add(myClient);
        String logMessage = "Client" + myClient.getClientId() + " arrived at queue " + queueIndex;
        userInterface.updateLogs(logMessage);
        userInterface.updateQueue(queueIndex, getNumClientsInQueue());
    }

    public void removeClientFromQueue(Client myClient) {
        clientsList.remove(myClient);
        String logMessage = "Client" + myClient.getClientId() + " left queue " + queueIndex;
        userInterface.updateLogs(logMessage);
        userInterface.updateQueue(queueIndex, getNumClientsInQueue());
    }

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
            userInterface.updateMinimumOutput(
                    "Average waiting time for queue " + queueIndex + " : " + totalWaitingTime / (totalClientsEver * 1.0));
        }
        userInterface.updateMinimumOutput("Queue " + queueIndex + " is empty, total dead time =" + deadQueueTime);

    }

    public void start() {
        isRunning = true;
        queueThread.start();
    }

    public void stop() {
        isRunning = false;
    }
}
