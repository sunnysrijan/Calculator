package edu.csc413.calculator.operators;


import edu.csc413.calculator.evaluator.*;

import java.util.HashMap;

public abstract class Operator {


    //Create Hashmap in operator class
    private static HashMap<String, Operator> operators = new HashMap<>();
    static{
        operators.put("+", new AddOperator());
        operators.put("-", new SubtractOperator());
        operators.put("*", new MultiplyOperator());
        operators.put("/", new DivideOperator());
        operators.put("^", new PowerOperator());
        operators.put("(", new LeftParenthesis());
        operators.put(")", new RightParenthesis());

    }

    public abstract int priority();
    public abstract Operand execute(Operand op1, Operand op2 );

    public static boolean check( String token ) {
        return operators.containsKey(token); //checks the method
    }

    public static Operator getOperator(String token){
        return operators.get(token);
    }

}

/**
 * determines if a given token is a valid operator.
 * please do your best to avoid static checks
 * for example token.equals("+") and so on.
 * Think about what happens if we add more operators.
 */
// The Operator class should contain an instance of a HashMap
// This map will use keys as the tokens we're interested in,
// and values will be instances of the Operators.
// ALL subclasses of operator MUST be in their own file.
// Example:
// Where does this declaration go? What should its access level be?
// Class or instance variable? Is this the right declaration?
// HashMap operators = new HashMap();
// operators.put( "+", new AdditionOperator() );
// operators.put( "-", new SubtractionOperator() );
//put all mapping operator to corresponding methods
