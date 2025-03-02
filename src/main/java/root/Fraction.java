package root;

import java.util.Arrays;
import java.util.Objects;

public class Fraction {

    private enum Quotient {
        NOMINATOR,
        DENOMINATOR
    }

    Integer[] fraction = new Integer[2];

    // Constructor
    public Fraction(int nominator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException( "DivisionByZero" );
        }
        this.fraction[Quotient.NOMINATOR.ordinal()] = nominator;
        this.fraction[Quotient.DENOMINATOR.ordinal()] = denominator;
        reduceFraction(this);
    }

    public Fraction(String s) {
        String[] f = s.split("/");

        if (f.length != 2) {
            throw new IllegalArgumentException("InvalidFractionFormat");
        } else if ( f[Quotient.DENOMINATOR.ordinal()].equals("0") ) {
            throw new ArithmeticException("DivisionByZero");
        }

        this.fraction[Quotient.NOMINATOR.ordinal()]
                = Integer.valueOf( f[Quotient.NOMINATOR.ordinal()]);
        this.fraction[Quotient.DENOMINATOR.ordinal()]
                = Integer.valueOf( f[Quotient.DENOMINATOR.ordinal()]);
        reduceFraction(this);
    }

    // Getters & Setters
    private int getNominator() {
        return this.fraction[Quotient.NOMINATOR.ordinal()];
    }

    private int getDenominator() {
        return this.fraction[Quotient.DENOMINATOR.ordinal()];
    }

    private void setNominator(int nominator) {
        this.fraction[Quotient.NOMINATOR.ordinal()] = nominator;
    }

    private void setDenominator(int denominator) {
        this.fraction[Quotient.DENOMINATOR.ordinal()] = denominator;
    }

    /**
     * Find the greatest common factor of 2 numbers.
     * @param value1 first number to compare
     * @param value2 second number to compare
     * @return the greatest common factor if the numbers have one, otherwise 1
     */
    private static int getGreatestCommonFactor(int value1, int value2) {
        if (value1 == value2) return value1;
        int minValue = Math.min( Math.abs(value1), Math.abs(value2) );
        int factor = 1;

        for (int i = 2; i <= minValue; i++) {
            if (value1 % i == 0 && value2 % i == 0) {
                factor = i;
            }
        }

        return factor;
    }

    private static int getGreatestCommonFactor(Fraction f) {
        return getGreatestCommonFactor(
                f.getNominator(),
                f.getDenominator()
        );
    }

    /**
     * Find the least common denominator of 2 fractions.
     * @param value1 Denominator of first fraction
     * @param value2 Denominator of second fraction
     * @return The least common denominator of 2 fractions
     */
    private static int getLeastCommonDenominator(int value1, int value2) {
        int maxValue = Math.max( Math.abs(value1), Math.abs(value2) );
        int minValue = Math.min( Math.abs(value1), Math.abs(value2) );
        int result = maxValue;

        while (result % minValue != 0) {
            result += maxValue;
        }

        return result;
    }

    /**
     * Get a reciprocal of a fraction.
     * @param f Fraction from which to get the reciprocal
     * @return A reciprocal of the fraction
     */
    private static Fraction getReciprocal(Fraction f) {
        return new Fraction( f.getDenominator(), f.getNominator());
    }

    /**
     * Reduce fraction by its greatest common factor.
     */
    private static void reduceFraction(Fraction f) {
        int greatestCommonFactor = getGreatestCommonFactor(f);

        f.setNominator( f.getNominator() / greatestCommonFactor);
        f.setDenominator( f.getDenominator() / greatestCommonFactor);
    }

    /**
     * Addition of 2 fractions.
     *
     * @param f1 First fraction to add
     * @param f2 Second fraction to add
     * @return A sum of the fractions
     */
    public static Fraction addFractions(Fraction f1, Fraction f2) {
        int nominator1 = f1.getNominator();
        int nominator2 = f2.getNominator();
        int denominator1 = f1.getDenominator();
        int denominator2 = f2.getDenominator();

        if (denominator1 == denominator2) {
            return new Fraction( (nominator1 - nominator2), denominator1 );
        } else {
            int leastCommonDenominator = getLeastCommonDenominator(denominator1, denominator2);
            return new Fraction(
                    (leastCommonDenominator / denominator1) * nominator1
                            + (leastCommonDenominator / denominator2) * nominator2,
                    leastCommonDenominator
            );
        }
    }

    /**
     * Addition of arbitrary amount of fractions.
     * @param f Fractions to add
     * @return A sum of the fractions
     */
    public static Fraction addFractions(Fraction... f) {
        Fraction result = f[0];

        for (int i = 1; i < f.length; i++) {
            result = addFractions(result, f[i]);
        }

        return result;
    }

    /**
     * Subtraction of 2 fractions.
     * @param f1 First fraction to subtract
     * @param f2 Second fraction to subtract
     * @return A difference of the fractions
     */
    public static Fraction subtractFractions(Fraction f1 , Fraction f2) {
        int nominator1 = f1.getNominator();
        int nominator2 = f2.getNominator();
        int denominator1 = f1.getDenominator();
        int denominator2 = f2.getDenominator();

        if (denominator1 == denominator2) {
            return new Fraction( (nominator1 - nominator2), denominator1 );
        } else {
            int leastCommonDenominator = getLeastCommonDenominator(denominator1, denominator2);
            return new Fraction(
                    (leastCommonDenominator / denominator1) * nominator1
                            - (leastCommonDenominator / denominator2) * nominator2,
                    leastCommonDenominator
            );
        }
    }

    /**
     * Subtraction of arbitrary amount of fractions.
     * @param f Fractions to subtract
     * @return A difference of the fractions
     */
    public static Fraction subtractFractions(Fraction... f) {
        Fraction result = f[0];

        for (int i = 1; i < f.length; i++) {
            result = subtractFractions(result, f[i]);
        }

        return result;
    }

    /**
     * Multiplication of 2 fractions.
     * @param f1 First fraction to multiply
     * @param f2 Second fraction to multiply
     * @return A product of the fractions
     */
    public static Fraction multiplyFractions(Fraction f1, Fraction f2) {
        int nominator1 = f1.getNominator();
        int nominator2 = f2.getNominator();
        int denominator1 = f1.getDenominator();
        int denominator2 = f2.getDenominator();

        return new Fraction(
                (nominator1 * nominator2),
                (denominator1 * denominator2)
        );
    }

    /**
     * Multiplication of arbitrary amount of fractions.
     * @param f Fractions to multiply
     * @return A product of the fractions
     */
    public static Fraction multiplyFractions(Fraction... f) {
        Fraction result = f[0];

        for (int i = 1; i < f.length; i++) {
            result = multiplyFractions(result, f[i]);
        }

        return result;
    }

    /**
     * Division of 2 fractions.
     * @param f1 First fraction to divide
     * @param f2 Second fraction to divide
     * @return A product of the fractions
     */
    public static Fraction divideFractions(Fraction f1, Fraction f2) {
        return multiplyFractions( f1, getReciprocal(f2) );
    }

    @Override
    public String toString() {
        return String.valueOf(this.getNominator()) + '/' + this.getDenominator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fraction f = (Fraction) o;
        return Objects.deepEquals(fraction, f.fraction);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(fraction);
    }
}
