package edu.tucn.pt;

import edu.tucn.pt.controller.ApplicationController;
import edu.tucn.pt.view.SwingUI;


public class App {
    /**
     * @param args - the command line arguments
     *             - not used
     *             - the program starts from here
     *             - the main method
     *             - the entry point
     *             - the starting point
     *             - the beginning
     *             - the start
     *             - the beginning of the end
     *             - the end of the beginning
     *             - the end of the end
     *             - the end
     */
    public static void main(String[] args) {
        ApplicationController main = new ApplicationController(new SwingUI());
        main.run();
    }
}