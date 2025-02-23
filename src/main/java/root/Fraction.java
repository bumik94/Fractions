package root;

import java.util.HashMap;

public class Fraction {

    private enum Quotient {
        NOMINATOR,
        DENOMINATOR
    }

    HashMap<Quotient, Integer> quotient = new HashMap<>();

    // Constructor
    public Fraction(int nominator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("DivisionByZero");
        }
        this.quotient.put(
                Quotient.NOMINATOR,
                nominator);
        this.quotient.put(
                Quotient.DENOMINATOR,
                denominator);
        reduceFraction();
    }

    public Fraction(String fractionString) {
        String[] stringArray = fractionString.split("/");

        if (stringArray.length != 2) {
            throw new IllegalArgumentException("InvalidFractionFormat");
        } else if (stringArray[Quotient.DENOMINATOR.ordinal()].equals("0") ) {
            throw new ArithmeticException("DivisionByZero");
        }

        this.quotient.put(
                Quotient.NOMINATOR,
                Integer.valueOf(stringArray[Quotient.NOMINATOR.ordinal()]));
        this.quotient.put(
                Quotient.DENOMINATOR,
                Integer.valueOf(stringArray[Quotient.DENOMINATOR.ordinal()]));
        reduceFraction();
    }

    // Getters & Setters
    private int getNominator() {
        return quotient.get(Quotient.NOMINATOR);
    }

    private int getDenominator() {
        return quotient.get(Quotient.DENOMINATOR);
    }

    private void setNominator(int nominator) {
        this.quotient.put(Quotient.NOMINATOR, nominator);
    }

    private void setDenominator(int denominator) {
        this.quotient.put(Quotient.DENOMINATOR, denominator);
    }

    /**
     * Find the greatest common factor of 2 numbers.
     * @param value1 first number to compare
     * @param value2 second number to compare
     * @return the greatest common factor if the numbers have one, otherwise 1
     */
    private static int getGreatestCommonFactor(int value1, int value2) {
        if (value1 == value2) return value1;
        int minValue = Integer.min(Math.abs(value1), Math.abs(value2));
        int factor = 1;

        for (int i = 2; i <= minValue; i++) {
            if (value1 % i == 0 && value2 % i == 0) {
                factor = i;
            }
        }

        return factor;
    }

    private static int getGreatestCommonFactor(Fraction fraction) {
        return getGreatestCommonFactor(
                fraction.getNominator(),
                fraction.getDenominator()
        );
    }

    private static int getLeastCommonDenominator(int denominator1, int denominator2) {
        return denominator1 * denominator2;
    }

    private static Fraction getReciprocal(Fraction fraction) {
        return new Fraction(fraction.getDenominator(), fraction.getNominator());
    }

    /**
     * Addition of 2 fractions.
     * @param fraction1 First fraction to add
     * @param fraction2 Second fraction to add
     * @return A sum of the fractions
     */
    public static Fraction addFractions(Fraction fraction1, Fraction fraction2) {
        int nominator1 = fraction1.getNominator();
        int nominator2 = fraction2.getNominator();
        int denominator1 = fraction1.getDenominator();
        int denominator2 = fraction2.getDenominator();

        if (denominator1 == denominator2) {
            return new Fraction((nominator1 + nominator2), denominator1);
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
     * Addition of arbitrary amount of fractions
     * @param fractions Fractions to add
     * @return A sum of the fractions
     */
    public static Fraction addFractions(Fraction... fractions) {
        Fraction result = fractions[0];

        for (int i = 1; i < fractions.length; i++) {
            result = addFractions(result, fractions[i]);
        }

        return result;
    }

    /**
     * Subtraction of 2 fractions.
     * @param fraction1 First fraction to subtract
     * @param fraction2 Second fraction to subtract
     * @return A difference of the fractions
     */
    public static Fraction subtractFractions(Fraction fraction1, Fraction fraction2) {
        int nominator1 = fraction1.getNominator();
        int nominator2 = fraction2.getNominator();
        int denominator1 = fraction1.getDenominator();
        int denominator2 = fraction2.getDenominator();

        if (denominator1 == denominator2) {
            return new Fraction((nominator1 - nominator2), denominator1);
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
     * Subtraction of arbitrary amount of fractions
     * @param fractions Fractions to subtract
     * @return A difference of the fractions
     */
    public static Fraction subtractFractions(Fraction... fractions) {
        Fraction result = fractions[0];

        for (int i = 1; i < fractions.length; i++) {
            result = subtractFractions(result, fractions[i]);
        }

        return result;
    }

    /**
     * Reduce fraction by its greatest common factor
     */
    private void reduceFraction() {
        int greatestCommonFactor = getGreatestCommonFactor(this);

        this.setNominator(this.getNominator() / greatestCommonFactor);
        this.setDenominator(this.getDenominator() / greatestCommonFactor);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getNominator()) + '/' + this.getDenominator();
    }

}
