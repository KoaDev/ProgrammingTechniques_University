package edu.tucn.pt.model.Polynomial;

import javax.swing.*;
import java.util.*;

public class Polynomial {
    /**
     * The polynome is a TreeMap of Monomials with degree as key
     */
    private final TreeMap<Integer, Monomial> polynome = new TreeMap<>();

    /**
     * Default constructor
     */
    public Polynomial() {}

    /**
     * @return the polynome
     */
    public TreeMap<Integer, Monomial> getPolynome() {
        return this.polynome;
    }

    /**
     * @return the polynome as a string
     */
    public String getPolinomString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Monomial currentMonom : this.getPolynome().values()) {
            stringBuilder.append(currentMonom.getMonomString());
        }
        return stringBuilder.toString();
    }

    /**
     * @param degree the degree of the monomial
     * @return the monomial with the given degree or null if not found
     */
    public Monomial findDegree(int degree) {
        return this.getPolynome().getOrDefault(degree, null);
    }

    /**
     * @param p the polynome to be copied
     * @return a copy of the polynome
     */
    public Polynomial copyPolynome(Polynomial p) {
        Polynomial copyPolinom = new Polynomial();

        for (Map.Entry<Integer, Monomial> entry : p.getPolynome().entrySet()) {
            int power = entry.getKey();
            double coeff = entry.getValue().getCoefficient();

            Monomial copyMonom = new Monomial(power, coeff);
            copyPolinom.getPolynome().put(power, copyMonom);
        }
        return copyPolinom;
    }

    /**
     * @param p the first polynome
     * @param q the second polynome
     * @return the sum of the two polynomes
     */
    public Polynomial add(Polynomial p, Polynomial q) {
        Polynomial sumPolinom = p.copyPolynome(p);

        for (Map.Entry<Integer, Monomial> entry : q.getPolynome().entrySet()) {
            int currentDegree = entry.getKey();
            double currentCoefficient = entry.getValue().getCoefficient();

            Monomial searchedMonom = sumPolinom.findDegree(currentDegree);
            if (searchedMonom == null) {
                Monomial newMonom = new Monomial(currentDegree, currentCoefficient);
                sumPolinom.getPolynome().put(currentDegree, newMonom);
            } else {
                double oldCoefficient = searchedMonom.getCoefficient();
                searchedMonom.setCoefficient(currentCoefficient + oldCoefficient);
            }
        }

        return sumPolinom;
    }

    /**
     * @param p the first polynome
     * @param q the second polynome
     * @return the difference of the two polynomes
     */
    public Polynomial subtract(Polynomial p, Polynomial q) {
        Polynomial differencePolinom = p.copyPolynome(p);

        for (Map.Entry<Integer, Monomial> entry : q.getPolynome().entrySet()) {
            int currentDegree = entry.getKey();
            double currentCoefficient = entry.getValue().getCoefficient();

            Monomial searchedMonom = differencePolinom.findDegree(currentDegree);
            if (searchedMonom == null) {
                Monomial newMonom = new Monomial(currentDegree, -currentCoefficient);
                differencePolinom.getPolynome().put(currentDegree, newMonom);
            } else {
                double oldCoefficient = searchedMonom.getCoefficient();
                searchedMonom.setCoefficient(oldCoefficient - currentCoefficient);
            }
        }

        return differencePolinom;
    }

    /**
     * @param p the first polynome
     * @param q the second polynome
     * @return the product of the two polynomes
     */
    public Polynomial multiply(Polynomial p, Polynomial q) {
        Polynomial multipliedPolinom = new Polynomial();

        for (Monomial currentMonom1 : p.getPolynome().values()) {
            int powerP1 = currentMonom1.getPower();
            double coeffP1 = currentMonom1.getCoefficient();

            for (Monomial currentMonom2 : q.getPolynome().values()) {
                int powerP2 = currentMonom2.getPower();
                int resultedPower = powerP1 + powerP2;
                double coeffP2 = currentMonom2.getCoefficient();
                double resultedCoeff = coeffP1 * coeffP2;

                Monomial searchedMonom = multipliedPolinom.findDegree(resultedPower);
                if (searchedMonom == null) {
                    Monomial newMonom = new Monomial(resultedPower, resultedCoeff);
                    multipliedPolinom.getPolynome().put(resultedPower, newMonom);
                } else {
                    double oldCoeff = searchedMonom.getCoefficient();
                    searchedMonom.setCoefficient(oldCoeff + resultedCoeff);
                }
            }
        }

        return multipliedPolinom;
    }

    /**
     * @param p the polynome to be derivated
     * @return the derivated polynome
     */
    public Polynomial derivate(Polynomial p) {
        Polynomial derivatedPolinom = new Polynomial();

        for (Map.Entry<Integer, Monomial> entry : p.getPolynome().entrySet()) {
            int power = entry.getKey();
            double coeff = entry.getValue().getCoefficient();

            if (power > 0) {
                Monomial derivatedMonom = new Monomial(power - 1, power * coeff);
                derivatedPolinom.getPolynome().put(power - 1, derivatedMonom);
            }
        }

        return derivatedPolinom;
    }

    /**
     * @param p the polynome to be integrated
     * @return the integrated polynome
     */
    public Polynomial integrate(Polynomial p) {
        Polynomial integratedPolinom = new Polynomial();

        for (Map.Entry<Integer, Monomial> entry : p.getPolynome().entrySet()) {
            int power = entry.getKey();
            double coeff = entry.getValue().getCoefficient();

            Monomial newMonom;
            if (power == -1) {
                newMonom = new Monomial(power + 1, coeff);
            } else {
                newMonom = new Monomial(power + 1, coeff / (power + 1));
            }

            integratedPolinom.getPolynome().put(power + 1, newMonom);
        }
        return integratedPolinom;
    }

    /**
     * @param dividend the polynome to be divided
     * @return the quotient and the remainder of the division
     */
    public Polynomial[] divide(Polynomial dividend) {
        Polynomial quotient = new Polynomial(), remainder;
        remainder = dividend.copyPolynome(dividend);

        Monomial dividerFirstMonom = this.getPolynome().firstEntry().getValue();
        Monomial remainderFirstMonom = remainder.getPolynome().firstEntry().getValue();

        double quotientCoeff, dividerCoeff = dividerFirstMonom.getCoefficient(),
                remainderCoeff = remainderFirstMonom.getCoefficient();
        int dividerPower = dividerFirstMonom.getPower(), remainderPower = remainderFirstMonom.getPower(), quotientPower;

        if (remainderPower < dividerPower) {
            JOptionPane.showMessageDialog(null, "ERROR: DIVISION NOT POSSIBLE, divident power < divider power!");
            return null;
        }

        while (remainderPower >= dividerPower) {
            quotientPower = remainderPower - dividerPower;
            quotientCoeff = remainderCoeff / dividerCoeff;

            Monomial quotientMonom = new Monomial(quotientPower, quotientCoeff);
            quotient.getPolynome().put(quotientPower, quotientMonom);

            Polynomial tempPolynomial = new Polynomial();
            tempPolynomial.getPolynome().put(quotientPower, quotientMonom);

            remainder = remainder.subtract(remainder, this.multiply(tempPolynomial, this));

            if (!remainder.getPolynome().isEmpty()) {
                remainderFirstMonom = remainder.getPolynome().firstEntry().getValue();
                remainderPower = remainderFirstMonom.getPower();
                remainderCoeff = remainderFirstMonom.getCoefficient();
            } else {
                break;
            }
        }

        Polynomial[] result = new Polynomial[2];
        result[0] = quotient;
        result[1] = remainder;
        return result;
    }


}
