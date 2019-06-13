package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.operators.LeftParenthesis;
import edu.csc413.calculator.operators.Operator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "+-*^/#! ( )";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval(String expression ) {
        String token;
        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

        // initialize operator stack - necessary with operator priority schema
        // the priority of any operator in the operator stack other than
        // the usual mathematical operators - "+-*/" - should be less than the priority
        // of the usual operators

        // while there are still tokens to be read in
        while ( this.tokenizer.hasMoreTokens()) {

            // filter out space
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // if token is an operand push it to the operand stack
                if (Operand.check(token)) {
                    // if token is an operand push it to operandStack
                    operandStack.push(new Operand(token));

                    // check if token is an operator
                } else {

                    // if token is not an operator display invalid token message
                    if (!Operator.check(token)) {
                        System.out.println("*****invalid token******");
                        throw new RuntimeException("*****invalid token******");

                    } else if (")".equals(token)) { // if token is ")"
                        while (operatorStack.peek().priority() != 0) {  // this runs until "(" is found
                            Operator oldOpr = operatorStack.pop();
                            Operand op2 = operandStack.pop();
                            Operand op1 = operandStack.pop();
                            operandStack.push(oldOpr.execute(op1, op2));
                        }
                        // discard "("
                        operatorStack.pop();

                        // if token is "(" then this pushes to operator stack
                    } else if ("(".equals(token)) {
                        operatorStack.push(new LeftParenthesis());

                    } else {
                        Operator newOperator = Operator.getOperator(token);

                        if (!operatorStack.empty()) {
                            while ((!operatorStack.isEmpty()) && (operandStack.size()>=2)&&(operatorStack.peek().priority() >= newOperator.priority()) ) {
                                Operator oldOpr = operatorStack.pop();
                                Operand op2 = operandStack.pop();
                                Operand op1 = operandStack.pop();
                                operandStack.push(oldOpr.execute(op1, op2));
                            }
                        }
                        operatorStack.push(newOperator);
                    }
                }
            }
        }

        // while-the operator stack is not empty
        while (!operatorStack.empty()) {
            Operator oldOpr = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push(oldOpr.execute(op1, op2));
        }

        return (operandStack.pop().getValue());
    }

}





// Control gets here when we've picked up all of the tokens; you must add
        // code to complete the evaluation - consider how the code given here
        // will evaluate the expression 1+2*3
        // When we have no more tokens to scan, the operand stack will contain 1 2
        // and the operator stack will have + * with 2 and * on the top;
        // In order to complete the evaluation we must empty the stacks (except
        // the init operator on the operator stack); that is, we should keep
        // evaluating the operator stack until it only contains the init operator;
        // Suggestion: create a method that takes an operator as argument and
        // then executes the while loop.

