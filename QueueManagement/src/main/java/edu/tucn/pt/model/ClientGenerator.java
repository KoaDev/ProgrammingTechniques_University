package edu.tucn.pt.model;

public class ClientGenerator {

    private static int counter = 1;

    private ClientGenerator() {
    }

    public static int getNextId() {
        return counter++;
    }
}
