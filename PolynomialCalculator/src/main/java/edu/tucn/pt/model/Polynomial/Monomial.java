package edu.tucn.pt.model.Polynomial;

public class Monomial {

    private int power;
    private double coefficient;

    /**
     * Default constructor
     */
    public Monomial() {
        this.power = 0;
        this.coefficient = 0;
    }

    /**
     * @param power - the power of the monomial
     * @param coefficient - the coefficient of the monomial
     */
    public Monomial(int power, double coefficient) {
        this.power = power;
        this.coefficient = coefficient;
    }

    /**
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * @return the coefficient
     */
    public double getCoefficient() {
        return coefficient;
    }

    /**
     * @param coefficient the coefficient to set
     */
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * @return the monomial as a string
     */
    public void displayMonom() {

        if (coefficient > 0)
            System.out.print("+" + coefficient + "x^" + power + " ");
        else if (coefficient < 0)
            System.out.print(coefficient + "x^" + power + " ");
        else
            System.out.print("+" + 0 + " ");
    }

    /**
     * @return the monomial as a string
     */
    public String getMonomString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (coefficient != 0)
            return stringBuilder.append("+ ").append(coefficient).append("x^").append(power).append(" ").toString();
        else
            return "";

    }

    /**
     * @return the monomial as a string
     */
    // pe integer
    public String getIntMonomString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (coefficient != 0)
            return stringBuilder.append("+ ").append((int)coefficient).append("x^").append(power).append(" ").toString();
        else
            return "";
    }

    /**
     * @return the monomial as a string
     */
    public void displayIntegratedMonom() {
        if (power == 0)
            System.out.print("+" + coefficient + "lnX" + " ");
        else {
            if (coefficient > 0)
                System.out.print("+" + coefficient + "x^" + power + " ");
            else if (coefficient < 0)
                System.out.print(coefficient + "x^" + power + " ");
            else
                System.out.print("+" + 0 + " ");
        }
    }

    /**
     * @return the monomial as a string
     */
    public String getIntegratedMonomString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (power == 0) {
            return stringBuilder.append("+").append(coefficient).append("lnX").append(" ").toString();
        } else

        if (coefficient != 0)
            return stringBuilder.append("+ ").append(coefficient).append("x^").append(power).append(" ").toString();
        else
            return "";
    }

    /**
     * @return the monomial as a string
     */
    // pe integer
    public String getIntIntegratedMonomString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (power == 0) {
            return stringBuilder.append("+").append((int)coefficient).append("lnX").append(" ").toString();
        } else

        if (coefficient != 0)
            return stringBuilder.append("+ ").append((int)coefficient).append("x^").append(power).append(" ").toString();
        else
            return "";
    }
}
