package ru.vkirilchuk.algorithm.grammar.expressions;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Token;

/**
 * A prefix unary arithmetic expression like "!a" or "-b".
 */
public class PrefixExpression implements Expression {
    private final Token mOperator;
    private final Expression mRight;

    public PrefixExpression(Token operator, Expression right) {
        mOperator = operator;
        mRight = right;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(mOperator.getLexeme());
        builder.append(mRight);
        builder.append(")");
        return builder.toString();
    }
}