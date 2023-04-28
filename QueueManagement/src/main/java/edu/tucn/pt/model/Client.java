package edu.tucn.pt.model;

/**
 * Client class
 */
public class Client {

    private int arrivalTime;
    private int serviceTime;
    private final int clientId;

    /**
     * @param arrivalTime - the time when the client arrives
     * @param serviceTime - the time needed to serve the client
     * @param clientId   - the id of the client
     */
    public Client(int arrivalTime, int serviceTime, int clientId) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.clientId = clientId;
    }

    /**
     * @return the arrival time of the client
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @param arrivalTime - the arrival time of the client
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @return the service time of the client
     */
    public int getServiceTime() {
        return serviceTime;
    }

    /**
     * @param serviceTime - the service time of the client
     */
    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * @return the id of the client
     */
    public int getClientId() {
        return clientId;
    }
}