package root;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Parser {

    ArrayList<String> expression;

    public Parser(String expr) {

        this.expression = new ArrayList<>(List.of(expr.split("[()]")));
        this.expression.removeFirst(); // removes empty element

        ListIterator<String> expressionIterator = this.expression.listIterator();

        /*
        For the moment the Parser doesn't respect the order of operations leading to incorrect results.
         */
        while (expressionIterator.hasNext()) {
            switch (expressionIterator.next()) {
                case "+" -> {
                    this.expression.set(
                        expressionIterator.nextIndex(),
                        Fraction.addFractions(
                                new Fraction(this.expression.get( expressionIterator.previousIndex() - 1 )),
                                new Fraction(this.expression.get( expressionIterator.nextIndex() ))
                        ).toString()
                    );

                    removePrevious(expressionIterator);
                }
                case "-" -> {
                    this.expression.set(
                        expressionIterator.nextIndex(),
                        Fraction.subtractFractions(
                                new Fraction(this.expression.get( expressionIterator.previousIndex() - 1 )),
                                new Fraction(this.expression.get( expressionIterator.nextIndex() ))
                        ).toString()
                    );

                    removePrevious(expressionIterator);
                }
                case "*" -> {
                    this.expression.set(
                        expressionIterator.nextIndex(),
                        Fraction.multiplyFractions(
                                new Fraction(this.expression.get( expressionIterator.previousIndex() - 1 )),
                                new Fraction(this.expression.get( expressionIterator.nextIndex() ))
                        ).toString()
                    );

                    removePrevious(expressionIterator);
                }
                case "/" -> {
                    this.expression.set(
                        expressionIterator.nextIndex(),
                        Fraction.divideFractions(
                                new Fraction(this.expression.get( expressionIterator.previousIndex() - 1 )),
                                new Fraction(this.expression.get( expressionIterator.nextIndex() ))
                        ).toString()
                    );

                    removePrevious(expressionIterator);
                }
            }
        }

        System.out.println(this.expression);
    }

    /**
     * Removes elements that were processed by an operation. That includes fractions as well as an operator.
     * @param iterator ListIterator object for traversing the expression.
     */
    private static void removePrevious(ListIterator<String> iterator) {
        /*
        This method deletes all elements from the current position to the beginning.
        For future implementation it will be important to respect the order of operations
        and remove only processed elements.
         */
        while (iterator.hasPrevious()) {
            iterator.previous();
            iterator.remove();
        }
    }
}
