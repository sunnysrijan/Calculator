package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */

public class Operand {
    int value;

    public Operand( String token ) {
        this.value = Integer.parseInt(token); // initialize this value

    }

    public Operand( int value ) {
        this.value = value;

    }

    public int getValue() {
        return value;
    }

    //check the method
    public static boolean check( String token) {
        try{
            Integer.parseInt(token);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }


}
