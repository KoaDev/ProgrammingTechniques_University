package edu.tucn.pt.view;


import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


import javax.swing.*;


public class SwingUI extends JFrame {


    private final JTextField timeMinField;
    private final JTextField timeMaxField;
    private final JTextField serviceMinField;
    private final JTextField serviceMaxField;
    private final JTextArea q1Field;
    private final JTextArea q2Field;
    private final JTextArea q3Field;
    private final JTextArea q4Field;
    private final JTextArea q5Field;
    private final JTextField nbQueuesField;
    private final JTextField simulationField;
    private final JTextField extraIntervalMinField;
    private final JTextField extraIntervalMaxField;
    private final JButton startButton;
    private final JTextArea minimumOutputField;
    private final JTextArea logsField;
    private final JTextArea currentTimeField;
    private JScrollPane logScrollPane;
    private final JScrollPane minimumOutputScrollPane;


    public SwingUI() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Max Champion
        JLabel timeLabel = new JLabel();
        JLabel timeLabelMin = new JLabel();
        timeMinField = new JTextField();
        JLabel timeLabelMax = new JLabel();
        timeMaxField = new JTextField();
        JLabel q1Label = new JLabel();
        JLabel q2Label = new JLabel();
        JLabel q3Label = new JLabel();
        JLabel q4Label = new JLabel();
        JLabel q5Label = new JLabel();
        JLabel serviceLabel = new JLabel();
        JLabel serviceLabelMin = new JLabel();
        serviceMinField = new JTextField();
        JLabel serviceLabelMax = new JLabel();
        serviceMaxField = new JTextField();
        q1Field = new JTextArea();
        q2Field = new JTextArea();
        q3Field = new JTextArea();
        q4Field = new JTextArea();
        q5Field = new JTextArea();
        JLabel nbQueues = new JLabel();
        nbQueuesField = new JTextField();
        JLabel simulation = new JLabel();
        simulationField = new JTextField();
        JLabel extraInterval = new JLabel();
        JLabel extraIntervalMin = new JLabel();
        extraIntervalMinField = new JTextField();
        JLabel extraIntervalMax = new JLabel();
        extraIntervalMaxField = new JTextField();
        JLabel commandLabel = new JLabel();
        startButton = new JButton();
        JLabel minimumOutputLabel = new JLabel();
        JLabel logsLabel = new JLabel();
        minimumOutputField = new JTextArea();
        logsField = new JTextArea();
        currentTimeField = new JTextArea();
        logScrollPane = new JScrollPane();



        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]",
                // rows
                "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[22]" +
                        "[]" +
                        "[]" +
                        "[]"));

        //---- timeLabel ----
        timeLabel.setText("Interval Between arrival time : ");
        contentPane.add(timeLabel, "cell 1 1");

        //---- timeLabelMin ----
        timeLabelMin.setText("Min :");
        contentPane.add(timeLabelMin, "cell 2 1");
        contentPane.add(timeMinField, "cell 3 1");

        //---- timeLabelMax ----
        timeLabelMax.setText("Max: ");
        contentPane.add(timeLabelMax, "cell 4 1");
        contentPane.add(timeMaxField, "cell 5 1");

        //---- q1Label ----
        q1Label.setText("Queue 1");
        contentPane.add(q1Label, "cell 8 1");

        //---- q2Label ----
        q2Label.setText("Queue 2");
        contentPane.add(q2Label, "cell 10 1");

        //---- q3Label ----
        q3Label.setText("Queue 3");
        contentPane.add(q3Label, "cell 12 1");

        //---- q4Label ----
        q4Label.setText("Queue 4");
        contentPane.add(q4Label, "cell 14 1");

        //---- q5Label ----
        q5Label.setText("Queue 5");
        contentPane.add(q5Label, "cell 16 1");

        //---- serviceLabel ----
        serviceLabel.setText("Service duration time :");
        contentPane.add(serviceLabel, "cell 1 2");

        //---- serviceLabelMin ----
        serviceLabelMin.setText("Min :");
        contentPane.add(serviceLabelMin, "cell 2 2");
        contentPane.add(serviceMinField, "cell 3 2");

        //---- serviceLabelMax ----
        serviceLabelMax.setText("Max: ");
        contentPane.add(serviceLabelMax, "cell 4 2");
        contentPane.add(serviceMaxField, "cell 5 2");

        //---- q1Field ----
        q1Field.setEditable(false);
        contentPane.add(q1Field, "cell 8 2 1 8,height 355::355");

        //---- q2Field ----
        q2Field.setEditable(false);
        contentPane.add(q2Field, "cell 10 2 1 8,height 355::355");

        //---- q3Field ----
        q3Field.setEditable(false);
        contentPane.add(q3Field, "cell 12 2 1 8,height 355::355");



        //---- q4Field ----
        q4Field.setEditable(false);
        contentPane.add(q4Field, "cell 14 2 1 8,height 355::355");

        //---- q5Field ----
        q5Field.setEditable(false);
        contentPane.add(q5Field, "cell 16 2 1 8,height 355::355");

        //---- nbQueues ----
        nbQueues.setText("Number of active queues (5 max) :");
        contentPane.add(nbQueues, "cell 1 3");
        contentPane.add(nbQueuesField, "cell 3 3");

        //---- simulation ----
        simulation.setText("Simulation interval :");
        contentPane.add(simulation, "cell 1 4");
        contentPane.add(simulationField, "cell 3 4");

        //---- extraInterval ----
        extraInterval.setText("Extra interval for statistic");
        contentPane.add(extraInterval, "cell 1 5");

        //---- extraIntervalMin ----
        extraIntervalMin.setText("Start :");
        contentPane.add(extraIntervalMin, "cell 2 5");
        contentPane.add(extraIntervalMinField, "cell 3 5");

        //---- extraIntervalMax ----
        extraIntervalMax.setText("Stop :");
        contentPane.add(extraIntervalMax, "cell 4 5");
        contentPane.add(extraIntervalMaxField, "cell 5 5");

        //---- commandLabel ----
        commandLabel.setText("Command :");
        contentPane.add(commandLabel, "cell 1 6");

        //---- startButton ----
        startButton.setText("Start");
        contentPane.add(startButton, "cell 3 6");

        //---- currentTimeField ----

        JScrollPane currentTimePanel = new JScrollPane(currentTimeField);
        currentTimeField.setEditable(false);
        contentPane.add(currentTimePanel, "cell 5 6");

        //---- minimumOutputLabel ----
        minimumOutputLabel.setText("Output");
        contentPane.add(minimumOutputLabel, "cell 1 7");

        //---- logsLabel ----
        logsLabel.setText("Logs");
        contentPane.add(logsLabel, "cell 3 7");

        logScrollPane = new JScrollPane(logsField);
        minimumOutputScrollPane = new JScrollPane(minimumOutputField);

        minimumOutputField.setEditable(false);
        logsField.setEditable(false);

        contentPane.add(minimumOutputScrollPane, "cell 1 8 1 2,height 150::150");
        contentPane.add(logScrollPane, "cell 3 8 3 2,height 150::150");

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void updateQueue(int queueNumber, int numberOfClients) {
        JTextArea[] queueFields = {q1Field, q2Field, q3Field, q4Field, q5Field};

        if (queueNumber >= 1 && queueNumber <= queueFields.length) {
            JTextArea queueField = queueFields[queueNumber - 1];
            queueField.setText("");

            for (int i = 0; i < numberOfClients; i++) {
                queueField.append("███\n");
            }
        } else {
            System.err.println("Invalid queue number: " + queueNumber);
        }
    }


    public void updateLogs(String updateText) {
        logsField.append(updateText + "\n");
        JScrollBar myScrollBar = logScrollPane.getVerticalScrollBar();
        myScrollBar.setValue(myScrollBar.getMaximum());
    }

    public void updateMinimumOutput(String updateText) {
        minimumOutputField.append(updateText + "\n");
        JScrollBar myScrollBar = minimumOutputScrollPane.getVerticalScrollBar();
        myScrollBar.setValue(myScrollBar.getMaximum());
    }

    public void updateCurrentTime(String currentTimeString) {
        String[] timeValues = currentTimeString.split("/");

        if (timeValues.length < 2) {
            System.err.println("Invalid time format: " + currentTimeString);
            return;
        }

        int currentTime = Integer.parseInt(timeValues[0].trim());

        currentTimeField.setText(String.valueOf(currentTime));

    }



    public String getTimeMin()
    {
        return timeMinField.getText();
    }

    public String getTimeMax()
    {
        return timeMaxField.getText();
    }

    public String getServiceTimeMin()
    {
        return serviceMinField.getText();
    }

    public String getServiceTimeMax()
    {
        return serviceMaxField.getText();
    }

    public String getNumberOfQueues()
    {
        return nbQueuesField.getText();
    }

    public String getSimulationTime()
    {
        return simulationField.getText();
    }

    public String getExtraTimeMin()
    {
        return extraIntervalMinField.getText();
    }

    public String getExtraTimeMax()
    {
        return extraIntervalMaxField.getText();
    }

    public void addStartButtonActionListener(final ActionListener actionListener)
    {
        startButton.addActionListener(actionListener);
    }
}