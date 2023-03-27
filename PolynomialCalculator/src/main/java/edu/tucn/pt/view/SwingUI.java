package edu.tucn.pt.view;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import edu.tucn.pt.model.Polynomial.Polynomial;

import edu.tucn.pt.controller.ApplicationController;

public class SwingUI extends JFrame {

    private final ApplicationController controller;
    private Polynomial p;
    private Polynomial q;
    private Polynomial Q;
    private Polynomial R;
    private Polynomial result;
    private JTextField inputField1;
    private JTextField inputField2;
    private JTextArea outputArea;

    /**
     * Creates a new SwingUI object.
     */
    public SwingUI() {
        controller = new ApplicationController(this);
    }

    /**
     * Creates and shows the main window.
     */
    public void createAndShowGUI() {

        // Create the main window
        JFrame frame = new JFrame("Polynomial Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));

        // Create the content pane
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Create the title label
        JLabel titleLabel = new JLabel("Polynomial Calculator");
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(titleLabel, BorderLayout.NORTH);

        // Create the input panel
        JPanel inputPanel = new JPanel(new MigLayout("insets 10"));
        JLabel inputLabel1 = new JLabel("P(x) =");
        inputField1 = new JTextField(20);
        inputField1.putClientProperty("Nimbus.Overrides", Map.of(
                "TextField.contentMargins", new Insets(0, 5, 0, 5)
        ));
        inputField1.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
        JLabel inputLabel2 = new JLabel("Q(x) =");
        inputField2 = new JTextField(20);
        inputField2.putClientProperty("Nimbus.Overrides", Map.of(
                "TextField.contentMargins", new Insets(0, 5, 0, 5)
        ));
        inputField2.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
        inputPanel.add(inputLabel1);
        inputPanel.add(inputField1, "wrap");
        inputPanel.add(inputLabel2);
        inputPanel.add(inputField2, "wrap");

        // Create the button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            try {
                p = controller.createPolynomial(inputField1.getText());
                q = controller.createPolynomial(inputField2.getText());
                result = controller.add(p, q);
                outputArea.setText(result.getPolinomString());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton subtractButton = new JButton("Subtract");
        subtractButton.addActionListener(e -> {
            try {
                p = controller.createPolynomial(inputField1.getText());
                q = controller.createPolynomial(inputField2.getText());
                result = controller.subtract(p, q);
                outputArea.setText(result.getPolinomString());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton multiplyButton = new JButton("Multiply");
        multiplyButton.addActionListener(e -> {
            try {
                p = controller.createPolynomial(inputField1.getText());
                q = controller.createPolynomial(inputField2.getText());
                result = controller.multiply(p, q);
                outputArea.setText(result.getPolinomString());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton divideButton = new JButton("Divide");
        divideButton.addActionListener(e -> {
            try {
                p = controller.createPolynomial(inputField1.getText());
                Q = controller.divide(p)[0];
                R = controller.divide(p)[1];
                outputArea.setText("Q: " + Q.getPolinomString() + " R: " + R.getPolinomString());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton integrateButton = new JButton("Integrate (only P(x))");
        integrateButton.addActionListener(e -> {
            try {
                p = controller.createPolynomial(inputField1.getText());
                q = controller.createPolynomial(inputField2.getText());
                 result = controller.integrate(p);
                outputArea.setText(result.getPolinomString());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton derivationButton = new JButton("Derivate (only P(x))");
        derivationButton.addActionListener(e -> {
            try {
                p = controller.createPolynomial(inputField1.getText());
                q = controller.createPolynomial(inputField2.getText());
                result = controller.derivate(p);
                outputArea.setText(result.getPolinomString());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(integrateButton);
        buttonPanel.add(derivationButton);

        // Create the output panel
        JPanel outputPanel = new JPanel(new BorderLayout());
        JLabel outputLabel = new JLabel("Result:");
        outputArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the panels to the content pane
        contentPane.add(inputPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(outputPanel, BorderLayout.EAST);

        // Display the window
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
