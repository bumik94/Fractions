package root;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void testAddFractions() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1,3);

        Fraction actualSum = Fraction.addFractions(f1, f2);

        Fraction expectedSum = new Fraction("5/6");

        assertEquals(actualSum.toString(), expectedSum.toString());
    }

    @Test
    void testAddMultipleFractions() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1,3);
        Fraction f3 = new Fraction(1,4);

        Fraction actualSum = Fraction.addFractions(f1, f2, f3);

        Fraction expectedSum = new Fraction("13/12");

        assertEquals(actualSum.toString(), expectedSum.toString());
    }

    @Test
    void testSubtractFractions() {
        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = new Fraction(1,2);

        Fraction actualDifference = Fraction.subtractFractions(f1, f2);

        Fraction expectedDifference = new Fraction("1/6");

        assertEquals(actualDifference.toString(), expectedDifference.toString());
    }

    @Test
    void testSubtractMultipleFractions() {
        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = new Fraction(1,2);
        Fraction f3 = new Fraction(1,3);

        Fraction actualDifference = Fraction.subtractFractions(f1, f2, f3);

        Fraction expectedDifference = new Fraction("-1/6");

        assertEquals(actualDifference.toString(), expectedDifference.toString());
    }

    @Test
    void testMultiplyFractions() {
        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = new Fraction(1,2);

        Fraction actualProduct = Fraction.multiplyFractions(f1, f2);

        Fraction expectedProduct = new Fraction("1/3");

        assertEquals(actualProduct.toString(), expectedProduct.toString());
    }
    @Test
    void testMultiplyMultipleFractions() {
        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = new Fraction(1,2);
        Fraction f3 = new Fraction(-3,2);

        Fraction actualProduct = Fraction.multiplyFractions(f1, f2, f3);

        Fraction expectedProduct = new Fraction("-1/2");

        assertEquals(actualProduct.toString(), expectedProduct.toString());
    }

    @Test
    void testDivideFractions() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 3);

        Fraction actualProduct = Fraction.divideFractions(f1, f2);

        Fraction expectedProduct = new Fraction("3/4");

        assertEquals(actualProduct.toString(), expectedProduct.toString());
    }
}