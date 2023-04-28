package edu.tucn.pt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.JOptionPane;

import edu.tucn.pt.model.Client;
import edu.tucn.pt.model.ClientGenerator;
import edu.tucn.pt.model.ServiceQueue;
import edu.tucn.pt.view.SwingUI;


/**
 * This class is the main controller of the application. It is responsible for
 * initializing the simulation, starting the clock and the queues, and
 * controlling the simulation flow.
 */
public class ApplicationController implements Runnable {

    private SwingUI swingUI;

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

    /**
     * This method initializes the simulation by reading the input data from the
     * SwingUI and creating the queues.
     */
    public void start() {
        swingUI = new SwingUI();
        swingUI.setVisible(true);
        initializeSimulation();

        clock = new Clock(swingUI);
        controllerThread = new Thread(this);
    }

    /**
     * @return the number of clients that arrived in the simulation
     */
    public boolean isInputDataValid() {
        String arrivalMin = swingUI.getTimeMin();
        String arrivalMax = swingUI.getTimeMax();
        String serviceMin = swingUI.getServiceTimeMin();
        String serviceMax = swingUI.getServiceTimeMax();
        String numberOfQueues = swingUI.getNumberOfQueues();
        String simulationTime = swingUI.getSimulationTime();
        String extraTimeMin = swingUI.getExtraTimeMin();
        String extraTimeMax = swingUI.getExtraTimeMax();

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

    /**
     * @param arrMin the arrivalMin to set
     * @param arrMax the arrivalMax to set
     * @param serMin the serviceMin to set
     * @param serMax the serviceMax to set
     * @param numQueues the numQueues to set
     * @param simTime the simTime to set
     * @param extraMin the extraMin to set
     * @param extraMax the extraMax to set
     */
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

    /**
     * This method initializes the simulation by reading the input data from the
     * SwingUI and creating the queues.
     */
    public void initializeSimulation() {
        swingUI.addStartButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputDataValid()) {
                    queues = new ServiceQueue[numQueues];

                    for (int i = 0; i < numQueues; i++) {
                        queues[i] = new ServiceQueue(i + 1, clock, swingUI);
                    }
                    clock.startClock();
                    controllerThread.start();
                }
            }
        });
    }

    /**
     * @return the maxClientsAtOnce
     */
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

    /**
     * @return the maxClientsAtOnce
     */
    public int getTotalNumClients() {
        int numClients = 0;
        for (ServiceQueue queue : queues) {
            numClients += queue.getNumClientsInQueue();
        }
        return numClients;
    }

    /**
     * This method is called when the controllerThread is started. It generates
     * clients and sends them to the shortest queue.
     */
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

        swingUI.updateMinimumOutput("Total clients served: " + numClients);
        swingUI.updateMinimumOutput("Average service time: " + (float) totalServiceTime / numClients);
        swingUI.updateMinimumOutput("Peak hour: " + peakHour + " with " + maxClientsAtOnce + " clients.");
        swingUI.updateMinimumOutput("In the interval " + extraTimeMin + "->" + extraTimeMax + " we served " + clientsInterval + " clients and the average service time was " + (float) serviceInterval / clientsInterval);
        clock.stopClock();
    }

}