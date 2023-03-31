package edu.tucn.pt.controller;


import edu.tucn.pt.model.Polynomial.Monomial;
import edu.tucn.pt.model.Polynomial.Polynomial;
import edu.tucn.pt.view.SwingUI;

import javax.swing.*;

/**
 * The controller
 */
public class ApplicationController {

    private final SwingUI view;

    /**
     * @param view - the view
     */
    public ApplicationController(SwingUI view) {
        this.view = view;
    }

    /**
     * Creates the GUI
     */
    public void run() {
        view.createAndShowGUI();
    }

    /**
     * @param textFieldString - the string from the text field
     * @return - the polynomial created from the string
     */
    public Polynomial createPolynomial(String textFieldString) {
        Polynomial currentPolynome = new Polynomial();
        String myPolinomialString = textFieldString;
        try {
            myPolinomialString = myPolinomialString.replaceAll("\\s", "");
            if (myPolinomialString.charAt(0) == '+')
                myPolinomialString = myPolinomialString.substring(1);

            for (String val : myPolinomialString.split("\\+")) {
                double coeff = 0;
                int power = 0;
                int xPosition = val.indexOf("x");
                int powPosition = val.indexOf("^");
                try {

                    coeff = Integer.parseInt(val.substring(0, xPosition));
                    power = Integer.parseInt(val.substring(powPosition + 1));

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Bad input! Please respect the format");
                    return null;
                }

                if (coeff != 0) {
                    Monomial myMonom = new Monomial(power, coeff);
                    currentPolynome.getPolynome().put(power, myMonom);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bad input!");
        }
        return currentPolynome;
    }

    /**
     * @param p - the first polynomial
     * @param q - the second polynomial
     * @return - the sum of the two polynomials
     */
    public Polynomial add(Polynomial p, Polynomial q) {
        return p.add(p ,q);
    }

    /**
     * @param p - the first polynomial
     * @param q - the second polynomial
     * @return - the difference of the two polynomials
     */
    public Polynomial subtract(Polynomial p, Polynomial q) {
        return p.subtract(p ,q);
    }

    /**
     * @param p - the first polynomial
     * @param q - the second polynomial
     * @return - the product of the two polynomials
     */
    public Polynomial multiply(Polynomial p, Polynomial q) {
        return p.multiply(p ,q);
    }

    /**
     * @param p - the polynomial to be divided
     * @return - the quotient of the division
     */
    public Polynomial[] divide(Polynomial p) {
        return p.divide(p);
    }

    /**
     * @param p - the polynomial to be derived
     * @return - the derivative of the polynomial
     */
    public Polynomial derivate(Polynomial p) {
        return p.derivate(p);
    }

    /**
     * @param p - the polynomial to be integrated
     * @return - the integral of the polynomial
     */
    public Polynomial integrate(Polynomial p) {
        return p.integrate(p);
    }


}