package edu.tucn.pt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.JOptionPane;

import edu.tucn.pt.model.Client;
import edu.tucn.pt.model.ClientGenerator;
import edu.tucn.pt.model.ServiceQueue;
import edu.tucn.pt.view.SwingUI;


public class ApplicationController implements Runnable {

    private SwingUI userInterface;

    private int arrivalMin;
    private int arrivalMax;
    private int serviceMin;
    private int serviceMax;
    private int numQueues;
    private int simulationDuration;

    private int extraTimeMin;
    private int extraTimeMax;
    private int clientsInterval = 0;
    private int serviceInterval = 0;

    private Clock clock;
    private Thread controllerThread;
    private ServiceQueue[] queues;

    private int maxClientsAtOnce = 0;
    private int numClients = 0;
    private int peakHour = 0;
    private int totalServiceTime = 0;

    public void start() {
        userInterface = new SwingUI();
        userInterface.setVisible(true);
        initializeSimulation();

        clock = new Clock(userInterface);
        controllerThread = new Thread(this);
    }

    public boolean isInputDataValid() {
        String arrivalMin = userInterface.getTimeMin();
        String arrivalMax = userInterface.getTimeMax();
        String serviceMin = userInterface.getServiceTimeMin();
        String serviceMax = userInterface.getServiceTimeMax();
        String numberOfQueues = userInterface.getNumberOfQueues();
        String simulationTime = userInterface.getSimulationTime();
        String extraTimeMin = userInterface.getExtraTimeMin();
        String extraTimeMax = userInterface.getExtraTimeMax();

        try {
            int arrMin = Integer.parseInt(arrivalMin);
            int arrMax = Integer.parseInt(arrivalMax);
            int serMin = Integer.parseInt(serviceMin);
            int serMax = Integer.parseInt(serviceMax);
            int numQueues = Integer.parseInt(numberOfQueues);
            int simTime = Integer.parseInt(simulationTime);
            int extraMin = Integer.parseInt(extraTimeMin);
            int extraMax = Integer.parseInt(extraTimeMax);

            if (arrMin > 0 && arrMax > 0 && serMax > 0 && serMin > 0 && numQueues > 0 && numQueues <= 5 && extraMin > 0 && extraMax > 0 && extraMin < extraMax && extraMax < simTime) {
                if (arrMin < arrMax && serMin < serMax) {
                    setData(arrMin, arrMax, serMin, serMax, numQueues, simTime, extraMin, extraMax);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input data! Check arrival and service times.");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input data! Check constraints.");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public void setData(int arrMin, int arrMax, int serMin, int serMax, int numQueues, int simTime, int extraMin, int extraMax) {
        this.arrivalMin = arrMin;
        this.arrivalMax = arrMax;
        this.serviceMin = serMin;
        this.serviceMax = serMax;
        this.numQueues = numQueues;
        this.simulationDuration = simTime;
        this.extraTimeMin = extraMin;
        this.extraTimeMax = extraMax;
    }

    public void initializeSimulation() {
        userInterface.addStartButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputDataValid()) {
                    queues = new ServiceQueue[numQueues];

                    for (int i = 0; i < numQueues; i++) {
                        queues[i] = new ServiceQueue(i + 1, clock, userInterface);
                    }
                    clock.startClock();
                    controllerThread.start();
                }
            }
        });
    }

    public int findShortestQueue() {
        int minIndex = 0;
        int minQueueLength = queues[0].getNumClientsInQueue();
        for (int i = 1; i < queues.length; i++) {
            if (queues[i].getNumClientsInQueue() < minQueueLength) {
                minQueueLength = queues[i].getNumClientsInQueue();
                minIndex = i;
            }
        }
        return minIndex;
    }

    public int getTotalNumClients() {
        int numClients = 0;
        for (ServiceQueue queue : queues) {
            numClients += queue.getNumClientsInQueue();
        }
        return numClients;
    }

    @Override
    public void run() {
        while (clock.getTime() < simulationDuration) {
            Random random = new Random();
            int timeBetweenClients = arrivalMin + random.nextInt((arrivalMax - arrivalMin) + 1);
            try {
                Thread.sleep(1000L * timeBetweenClients);
            } catch (InterruptedException ignored) {
            }
            int randomServiceTime = serviceMin + random.nextInt((serviceMax - serviceMin) + 1);

            totalServiceTime += randomServiceTime;
            Client newClient = new Client(clock.getTime(), randomServiceTime, ClientGenerator.getNextId());

            numClients++;
            if (clock.getTime() > extraTimeMin && clock.getTime() < extraTimeMax) {
                clientsInterval++;
                serviceInterval += randomServiceTime;
            }

            int shortestQueueIndex = findShortestQueue();
            queues[shortestQueueIndex].addClientToQueue(newClient);

            int totalClients = getTotalNumClients();
            if (maxClientsAtOnce < totalClients) {
                maxClientsAtOnce = totalClients;
                peakHour = clock.getTime();
            }
        }

        System.out.println("Stopping simulation:");
        for (ServiceQueue queue : queues) {
            queue.stop();
        }

        userInterface.updateMinimumOutput("Total clients served: " + numClients);
        userInterface.updateMinimumOutput("Average service time: " + (float) totalServiceTime / numClients);
        userInterface.updateMinimumOutput("Peak hour: " + peakHour + " with " + maxClientsAtOnce + " clients.");
        userInterface.updateMinimumOutput("In the interval " + extraTimeMin + "->" + extraTimeMax + " we served " + clientsInterval + " clients and the average service time was " + (float) serviceInterval / clientsInterval);
        clock.stopClock();
    }

}