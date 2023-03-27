package edu.tucn.pt.model.Polynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PolynomialTest {

    Monomial m1 = new Monomial(3, 1);
    Monomial m2 = new Monomial(2, -4);
    Monomial m3 = new Monomial(1, 2);
    Monomial m4 = new Monomial(0, -3);

    Monomial mm1 = new Monomial(1, 1);
    Monomial mm2 = new Monomial(0, 2);

    Polynomial p1 = new Polynomial();
    Polynomial p2 = new Polynomial();
    Polynomial p3 = new Polynomial();

    Polynomial sum = new Polynomial();
    Polynomial sub = new Polynomial();
    Polynomial mul = new Polynomial();
    Polynomial der = new Polynomial();
    Polynomial intg = new Polynomial();
    Polynomial copy = new Polynomial();

    Polynomial Q = new Polynomial();
    Polynomial R = new Polynomial();

    @Before
    public void setUp() throws Exception {
        p1.getPolynome().add(m1);
        p1.getPolynome().add(m2);
        p1.getPolynome().add(m3);
        p1.getPolynome().add(m4);

        p2.getPolynome().add(mm1);
        p2.getPolynome().add(mm2);

        sum = sum.add(p1, p2);
        sub = sub.substract(p1, p2);
        mul = mul.multiply(p1, p2);
        der = der.derivate(p1);
        intg = intg.integrate(p1);

        Q = p1.divide(p2)[0];
        R = p1.divide(p2)[1];

        copy = p3.copyPolynome(p1);

    }

    @Test
    public void addTest() {
        System.out.println(sum.getPolinomString());
        assertEquals("+ 1.0x^3 + -4.0x^2 + 3.0x^1 + -1.0x^0 ", sum.getPolinomString());
    }

    @Test
    public void substractTest() {
        System.out.println(sub.getPolinomString());
        assertEquals("+ 1.0x^3 + -4.0x^2 + 1.0x^1 + -5.0x^0 ", sub.getPolinomString());
    }

    @Test
    public void multiplyTest() {
        System.out.println(mul.getPolinomString());
        assertEquals("+ 1.0x^4 + -2.0x^3 + -6.0x^2 + 1.0x^1 + -6.0x^0 ", mul.getPolinomString());
    }

    @Test
    public void derivativeTest() {
        System.out.println(der.getPolinomString());
        assertEquals("+ 3.0x^2 + -8.0x^1 + 2.0x^0 ", der.getPolinomString());
    }

    @Test
    public void integrateTest() {
        System.out.println(intg.getPolinomString());
        assertEquals("+ 0.25x^4 + -1.3333333333333333x^3 + 1.0x^2 + -3.0x^1 ", intg.getPolinomString());
    }

    @Test
    public void divideTest() {
        System.out.println(Q.getPolinomString());
        System.out.println(R.getPolinomString());
        assertEquals("+ 1.0x^2 + -6.0x^1 + 14.0x^0 ", Q.getPolinomString());
        assertEquals("+ -31.0x^0 ", R.getPolinomString());
    }

    @Test
    public void getPolinomStringTest() {
        System.out.println(p1.getPolinomString());
        assertEquals("+ 1.0x^3 + -4.0x^2 + 2.0x^1 + -3.0x^0 ", p1.getPolinomString());
    }

    @Test
    public void testSortDegree() {
        p1.sortDegree();
        System.out.println(p1.getPolinomString());
        assertEquals("+ 1.0x^3 + -4.0x^2 + 2.0x^1 + -3.0x^0 ", p1.getPolinomString());
    }

    @Test
    public void testGetPolynome() {
        assertEquals(4, p1.getPolynome().size());
    }

    @Test
    public void testGetPolinomString() {
        System.out.println(p1.getPolinomString());
        assertEquals("+ 1.0x^3 + -4.0x^2 + 2.0x^1 + -3.0x^0 ", p1.getPolinomString());
    }

    @Test
    public void testCopyPolynome() {
        System.out.println(copy.getPolinomString());
        assertEquals("+ 1.0x^3 + -4.0x^2 + 2.0x^1 + -3.0x^0 ", copy.getPolinomString());
    }

    @Test
    public void testGetFirstMonom() {
        System.out.println(p1.getFirstMonom(p1).getMonomString());
        assertEquals(m1, p1.getFirstMonom(p1));
    }
}
