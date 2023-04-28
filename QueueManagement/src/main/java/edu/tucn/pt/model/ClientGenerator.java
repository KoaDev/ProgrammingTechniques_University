package edu.tucn.pt.model;

/**
 * Client class
 */
public class ClientGenerator {

    private static int counter = 1;

    /**
     *
     */
    private ClientGenerator() {
    }

    /**
     * @return the next id
     */
    public static int getNextId() {
        return counter++;
    }
}
