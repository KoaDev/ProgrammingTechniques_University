package edu.tucn.pt.model.Polynomial;

import junit.framework.TestCase;
import org.junit.Before;

public class MonomialTest extends TestCase {

    Monomial m1 = new Monomial(3, 1);

    public void testDisplayMonom() {
        m1.displayMonom();
        assertEquals("+ 1.0x^3 ", m1.getMonomString());
    }

    public void testGetMonomString() {
        System.out.println(m1.getMonomString());
        assertEquals("+ 1.0x^3 ", m1.getMonomString());
    }

    public void testGetIntMonomString() {
        System.out.println(m1.getIntMonomString());
        assertEquals("+ 1x^3 ", m1.getIntMonomString());
    }

    public void testDisplayIntegratedMonom() {
        m1.displayIntegratedMonom();
        assertEquals("+ 1.0x^3 ", m1.getIntegratedMonomString());
    }

    public void testGetIntegratedMonomString() {
        System.out.println(m1.getIntegratedMonomString());
        assertEquals("+ 1.0x^3 ", m1.getIntegratedMonomString());
    }

    public void testGetIntIntegratedMonomString() {
        System.out.println(m1.getIntIntegratedMonomString());
        assertEquals("+ 1x^3 ", m1.getIntIntegratedMonomString());
    }

    public void testGetPower() {
        System.out.println(m1.getPower());
        assertEquals(3, m1.getPower());
    }

    public void testSetPower() {
        m1.setPower(2);
        System.out.println(m1.getPower());
        assertEquals(2, m1.getPower());
    }

    public void testGetCoefficient() {
        System.out.println(m1.getCoefficient());
        assertEquals(1.0, m1.getCoefficient());
    }

    public void testSetCoefficient() {
        m1.setCoefficient(2);
        System.out.println(m1.getCoefficient());
        assertEquals(2.0, m1.getCoefficient());
    }

    public void testTestDisplayMonom() {

    }

    public void testTestGetMonomString() {
    }

    public void testTestGetIntMonomString() {
    }

    public void testTestDisplayIntegratedMonom() {
    }

    public void testTestGetIntegratedMonomString() {
    }

    public void testTestGetIntIntegratedMonomString() {
    }
}