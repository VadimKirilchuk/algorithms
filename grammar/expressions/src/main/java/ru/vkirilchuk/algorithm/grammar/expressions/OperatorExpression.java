package ru.vkirilchuk.algorithm.grammar.expressions;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Token;

/**
 * A binary arithmetic expression like "a + b" or "c ^ d".
 */
public class OperatorExpression implements Expression {
    private final Expression leftExpression;
    private final Token operatorToken;
    private final Expression rightExpression;

    public OperatorExpression(Expression left, Token operator, Expression right) {
        this.leftExpression = left;
        this.operatorToken = operator;
        this.rightExpression = right;
    }

    @Override
    public int evaluate() {
        String lexeme = operatorToken.getLexeme();

        int leftValue = leftExpression.evaluate();
        int rightValue = rightExpression.evaluate();

        if ("+".equals(lexeme)) {
            return leftValue + rightValue;
        }

        if ("-".equals(lexeme)) {
            return leftValue - rightValue;
        }

        if ("*".equals(lexeme)) {
            return leftValue * rightValue;
        }

        if ("/".equals(lexeme)) {
            return leftValue / rightValue;
        }

        throw new UnsupportedOperationException("Unsuported binary operation: " + this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(leftExpression);
        builder.append(" ").append(operatorToken.getLexeme()).append(" ");
        builder.append(rightExpression);
        builder.append(")");
        return builder.toString();
    }

}