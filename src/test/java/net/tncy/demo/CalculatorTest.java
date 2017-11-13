package net.tncy.demo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {


    private Calculator c;
    @Before
    public void setup() {
        this.c = new Calculator();
    }

    @Test
    public void testAdd() {
        assertEquals("Should be 3",3, this.c.add(1,2), 0);
    }

    @Test
    public void testSub() {
        assertEquals("Should be 3",3, this.c.substract(5,2), 0);
    }

    @Test
    public void testMul() {
        assertEquals("Should be 3",3, this.c.multiply(1,3), 0);
    }

    @Test
    public void testDiv() {
        assertEquals("Should be 3",3, this.c.divide(9,3), 0);
    }

}
