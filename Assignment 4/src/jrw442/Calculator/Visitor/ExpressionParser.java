package jrw442.Calculator.Visitor;

import java.util.ArrayList;

import jrw442.Calculator.Composite.AddSubExpression;
import jrw442.Calculator.Composite.AtomicExpression;
import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.Composite.MulDivExpression;

public class ExpressionParser {

    private ArrayList<String> tokens;
    private int currentIndex;

    public Expression parse(String input){
        this.tokens = tokenize(input);
        this.currentIndex = 0;
        return parseExpression();
    }

    private ArrayList<String> tokenize(String input){
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder numBuffer = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') {
                numBuffer.append(ch);  // Build a multi-digit number
            } else if ("+-*/".indexOf(ch) != -1) {
                if (numBuffer.length() > 0) {
                    tokens.add(numBuffer.toString());
                    numBuffer.setLength(0);
                }
                tokens.add(Character.toString(ch));  // Add operator
            }
        }
        if (numBuffer.length() > 0) {
            tokens.add(numBuffer.toString());
        }
        return tokens;
    }

    private Expression parseExpression() {
        Expression left = parseTerm();

        while (currentIndex < tokens.size() && (tokens.get(currentIndex).equals("+") || tokens.get(currentIndex).equals("-"))) {
            char operator = tokens.get(currentIndex++).charAt(0);
            Expression right = parseTerm();
            left = new AddSubExpression(left, right, operator);
        }
        return left;
    }

    private Expression parseTerm() {
        Expression left = parseFactor();

        while (currentIndex < tokens.size() && (tokens.get(currentIndex).equals("*") || tokens.get(currentIndex).equals("/"))) {
            char operator = tokens.get(currentIndex++).charAt(0);
            Expression right = parseFactor();
            left = new MulDivExpression(left, right, operator);
        }
        return left;
    }

    private Expression parseFactor() {
        String token = tokens.get(currentIndex++);
        return new AtomicExpression(Double.parseDouble(token));
    }

}
