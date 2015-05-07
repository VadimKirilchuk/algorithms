package ru.vkirilchuk.algorithm.grammar.expressions;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.Token;

/**
 * A prefix unary arithmetic expression like "!a" or "-b".
 */
public class PrefixExpression implements Expression {
    private final Token operatorToken;
    private final Expression expression;

    public PrefixExpression(Token operator, Expression expression) {
        this.operatorToken = operator;
        this.expression = expression;
    }

    @Override
    public int evaluate() {
        if("-".equals(operatorToken.getLexeme())) {
            return -expression.evaluate();
        }

        throw new UnsupportedOperationException("Prefix operation is unsupported for: " + operatorToken);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(operatorToken.getLexeme());
        builder.append(expression);
        builder.append(")");
        return builder.toString();
    }
}