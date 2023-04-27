package edu.tucn.pt.model;

public class Client {

    private int arrivalTime;
    private int serviceTime;
    private final int clientId;

    public Client(int arrivalTime, int serviceTime, int clientId) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.clientId = clientId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getClientId() {
        return clientId;
    }
}