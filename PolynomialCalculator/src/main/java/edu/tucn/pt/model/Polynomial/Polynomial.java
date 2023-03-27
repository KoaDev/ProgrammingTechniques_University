package edu.tucn.pt.model.Polynomial;

import javax.swing.*;
import java.util.*;

public class Polynomial {

    /**
     * The polynome is an ArrayList of Monomials
     */
    private final ArrayList<Monomial> polynome = new ArrayList<>();

    /**
     * Default constructor
     */
    public Polynomial() {}

    /**
     * Sorts the polynome by degree
     */
    public void sortDegree() {

        polynome.sort((o1, o2) -> Integer.compare(o2.getPower(), o1.getPower()));
    }

    /**
     * @return the polynome
     */
    public ArrayList<Monomial> getPolynome() {
        return this.polynome;
    }

    /**
     * @return the polynome as a string
     */
    public String getPolinomString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Monomial currentMonom : this.getPolynome()) {
            stringBuilder.append(currentMonom.getMonomString());
        }
        return stringBuilder.toString();
    }

    /**
     * @param degree the degree of the monomial
     * @return the monomial with the given degree
     */
    public Monomial findDegree(int degree) {
        Monomial resultMonom = null;

        for (Monomial currentMonom : this.getPolynome()) {
            if (currentMonom.getPower() == degree)
                resultMonom = currentMonom;
        }

        return resultMonom;
    }

    /**
     * @param p the polynome to be copied
     * @return a copy of the polynome
     */
    public Polynomial copyPolynome(Polynomial p) {
        Polynomial copyPolinom = new Polynomial();

        for (Monomial currentMonom : p.getPolynome()) {
            int power = currentMonom.getPower();
            double coeff = currentMonom.getCoefficient();

            Monomial copyMonom = new Monomial(power, coeff);
            copyPolinom.getPolynome().add(copyMonom);
        }
        return copyPolinom;
    }

    /**
     * @param p the first polynome
     * @param q the second polynome
     * @return the sum of the two polynomes
     */
    public Polynomial add(Polynomial p, Polynomial q) {
        Polynomial sumPolinom;
        sumPolinom = p.copyPolynome(p);

        for (Monomial currentMonom : q.getPolynome()) {
            int currentDegree = currentMonom.getPower();
            double currentCoefficient = currentMonom.getCoefficient();

            Monomial searchedMonom = sumPolinom.findDegree(currentDegree);
            if (searchedMonom == null) {
                sumPolinom.getPolynome().add(currentMonom);
            } else {
                double oldCoefficient = searchedMonom.getCoefficient();
                searchedMonom.setCoefficient(currentCoefficient + oldCoefficient);
            }
        }

        sumPolinom.sortDegree();

        return sumPolinom;
    }

    /**
     * @param p the first polynome
     * @param q the second polynome
     * @return the difference of the two polynomes
     */
    public Polynomial substract(Polynomial p, Polynomial q) {
        Polynomial differencePolinom;
        differencePolinom = p.copyPolynome(p);

        for (Monomial currentMonom : q.getPolynome()) {
            int currentDegree = currentMonom.getPower();
            double currentCoefficient = currentMonom.getCoefficient();

            Monomial searchedMonom = differencePolinom.findDegree(currentDegree);
            if (searchedMonom == null) {

                Monomial newMonom = new Monomial(currentDegree, -currentCoefficient);

                differencePolinom.getPolynome().add(newMonom);
            } else {
                double oldCoefficient = searchedMonom.getCoefficient();
                searchedMonom.setCoefficient(oldCoefficient - currentCoefficient);
            }
        }
        differencePolinom.sortDegree();

        return differencePolinom;
    }

    /**
     * @param p the first polynome
     * @param q the second polynome
     * @return the product of the two polynomes
     */
    public Polynomial multiply(Polynomial p, Polynomial q) {
        Polynomial multipliedPolinom = new Polynomial(), pp = p.copyPolynome(p), qq = q.copyPolynome(q);

        for (Monomial currentMonom1 : pp.getPolynome()) {
            int powerP1 = currentMonom1.getPower();
            double coeffP1 = currentMonom1.getCoefficient();

            for (Monomial currentMonom2 : qq.getPolynome()) {
                int powerP2 = currentMonom2.getPower(), resultedPower = powerP1 + powerP2;
                double coeffP2 = currentMonom2.getCoefficient(), resultedCoeff = coeffP1 * coeffP2;

                Monomial searchedMonom = multipliedPolinom.findDegree(resultedPower);
                if (searchedMonom == null) {
                    Monomial newMonom = new Monomial(resultedPower, resultedCoeff);
                    multipliedPolinom.getPolynome().add(newMonom);
                } else {
                    double oldCoeff = searchedMonom.getCoefficient();
                    searchedMonom.setCoefficient(oldCoeff + resultedCoeff);
                }
            }
        }
        multipliedPolinom.sortDegree();
        return multipliedPolinom;
    }

    /**
     * @param p the polynome to be derived
     * @return the derived polynome
     */
    public Polynomial derivate(Polynomial p) {
        Polynomial derivatedPolinom = new Polynomial();

        Polynomial copyP = p.copyPolynome(p);

        for (Monomial currentMonom : copyP.getPolynome()) {
            int power = currentMonom.getPower();
            double coeff = currentMonom.getCoefficient();

            currentMonom.setCoefficient(power * coeff);
            currentMonom.setPower(power - 1);

            derivatedPolinom.getPolynome().add(currentMonom);
        }
        derivatedPolinom.sortDegree();

        return derivatedPolinom;
    }

    /**
     * @param p the polynome to be integrated
     * @return the integrated polynome
     */
    public Polynomial integrate(Polynomial p) {
        Polynomial integratedPolinom = new Polynomial();

        Polynomial pp = p.copyPolynome(p);

        for (Monomial currentMonom : pp.getPolynome()) {
            int power = currentMonom.getPower();
            double coeff = currentMonom.getCoefficient();

            currentMonom.setPower(power + 1);
            if (power == -1) {
                currentMonom.setCoefficient(coeff);
            } else
                currentMonom.setCoefficient(coeff / (power + 1));

            integratedPolinom.getPolynome().add(currentMonom);
        }
        integratedPolinom.sortDegree();
        return integratedPolinom;
    }

    /**
     * @param p the polynome to be integrated
     * @return the divided polynome
     */
    public Polynomial[] divide(Polynomial p) {
        Polynomial quotient = new Polynomial(), remainder, pp = p.copyPolynome(p);
        remainder = this.copyPolynome(this);

        Monomial dividerFirstMonom = pp.getPolynome().get(0);
        Monomial remainderMonom = remainder.getFirstMonom(remainder);

        double qCoeff, dividerCoef = dividerFirstMonom.getCoefficient(),
                remainderCoeff = remainderMonom.getCoefficient();
        int dividerPower = dividerFirstMonom.getPower(), remainderPower = remainderMonom.getPower(), qPower;

        if (remainderPower < dividerPower) {
            JOptionPane.showMessageDialog(null, "ERROR : DIVISION NOT POSSIBLE, divident power < divider power!");
            return null;
        }

        while (remainderPower >= dividerPower) {
            qPower = remainderPower - dividerPower;
            qCoeff = remainderCoeff / dividerCoef;

            Monomial myMonom = new Monomial(qPower, qCoeff);
            quotient.getPolynome().add(myMonom);

            Polynomial helper = new Polynomial();
            helper.getPolynome().add(myMonom);

            remainder = remainder.substract(remainder, (helper.multiply(helper, pp)));

            remainderMonom = remainder.getFirstMonom(remainder);
            remainderPower = remainderMonom.getPower();
            remainderCoeff = remainderMonom.getCoefficient();
        }
        Polynomial[] returnedPolinom = new Polynomial[2];
        returnedPolinom[0] = quotient;
        returnedPolinom[1] = remainder;
        return returnedPolinom;
    }

    /**
     * @param p the polynome to be integrated
     * @return the integrated polynome
     */
    public Monomial getFirstMonom(Polynomial p) {
        Monomial firstMonom = new Monomial();

        for (Monomial currentMonom : p.getPolynome()) {
            if (currentMonom.getCoefficient() != 0) {
                firstMonom = currentMonom;
                break;
            }
        }
        return firstMonom;
    }
}
