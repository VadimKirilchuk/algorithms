package ru.vkirilchuk.algorithm.grammar.expressions;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.TokenType;

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
        TokenType type = operatorToken.getType();

        int leftValue = leftExpression.evaluate();
        int rightValue = rightExpression.evaluate();

        if (TokenType.PLUS == type) {
            return leftValue + rightValue;
        }

        if (TokenType.MINUS == type) {
            return leftValue - rightValue;
        }

        if (TokenType.MUL == type) {
            return leftValue * rightValue;
        }

        if (TokenType.DIV == type) {
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