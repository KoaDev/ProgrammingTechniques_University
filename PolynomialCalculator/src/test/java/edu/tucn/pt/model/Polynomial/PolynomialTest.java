package edu.tucn.pt.model.Polynomial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PolynomialTest {

    private Polynomial p1, p2;

    @BeforeEach
    public void setup() {
        p1 = new Polynomial();
        p2 = new Polynomial();

        p1.getPolynome().put(0, new Monomial(0, 1));
        p1.getPolynome().put(1, new Monomial(1, 2));
        p1.getPolynome().put(2, new Monomial(2, 3));

        p2.getPolynome().put(0, new Monomial(0, 4));
        p2.getPolynome().put(1, new Monomial(1, 5));
        p2.getPolynome().put(2, new Monomial(2, 6));
    }

    @Test
    public void testGetPolinomString() {
        assertEquals("+ 1.0x^0 + 2.0x^1 + 3.0x^2 ", p1.getPolinomString());
        assertEquals("+ 4.0x^0 + 5.0x^1 + 6.0x^2 ", p2.getPolinomString());
    }

    @Test
    public void testFindDegree() {
        Monomial m1 = p1.findDegree(1);
        assertNotNull(m1);
        assertEquals(1, m1.getPower());
        assertEquals(2, m1.getCoefficient());

        Monomial m2 = p1.findDegree(3);
        assertNull(m2);
    }

    @Test
    public void testCopyPolynome() {
        Polynomial copy = p1.copyPolynome(p1);
        assertEquals("+ 1.0x^0 + 2.0x^1 + 3.0x^2 ", copy.getPolinomString());
    }

    @Test
    public void testAdd() {
        Polynomial sum = p1.add(p1, p2);
        assertEquals("+ 5.0x^0 + 7.0x^1 + 9.0x^2 ", sum.getPolinomString());
    }

    @Test
    public void testSubtract() {
        Polynomial difference = p1.subtract(p1, p2);
        assertEquals("+ -3.0x^0 + -3.0x^1 + -3.0x^2 ", difference.getPolinomString());
    }

    @Test
    public void testMultiply() {
        Polynomial product = p1.multiply(p1, p2);
        assertEquals("+ 4.0x^0 + 13.0x^1 + 28.0x^2 + 27.0x^3 + 18.0x^4 ", product.getPolinomString());
    }

    @Test
    public void testDerivate() {
        Polynomial derivative = p1.derivate(p1);
        assertEquals("+ 2.0x^0 + 6.0x^1 ", derivative.getPolinomString());
    }

    @Test
    public void testIntegrate() {
        Polynomial integral = p1.integrate(p1);
        assertEquals("+ 1.0x^1 + 1.0x^2 + 1.0x^3 ", integral.getPolinomString());
    }

    @Test
    public void getPolinomStringTest() {
        System.out.println(p1.getPolinomString());
        assertEquals("+ 1.0x^0 + 2.0x^1 + 3.0x^2 ", p1.getPolinomString());
    }

    /*

    @Test
    public void testDivide() {
        Polynomial p3 = new Polynomial();
        Polynomial p4 = new Polynomial();

        p3.getPolynome().put(0, new Monomial(0, 1));
        p3.getPolynome().put(1, new Monomial(1, 1));

        p4.getPolynome().put(0, new Monomial(0, 1));
        p4.getPolynome().put(1, new Monomial(1, 1));
        p4.getPolynome().put(2, new Monomial(2, 1));

        Polynomial[] divisionResult = p4.divide(p3);

        assertNotNull(divisionResult);
        assertEquals("1.0x^1+1.0x^0", divisionResult[0].getPolinomString());
        assertEquals("1.0x^0", divisionResult[1].getPolinomString());
    }

    */
}