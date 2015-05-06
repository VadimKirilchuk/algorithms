package ru.vkirilchuk.algorithm.grammar.expressions;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Token;

/**
 * A binary arithmetic expression like "a + b" or "c ^ d".
 */
public class OperatorExpression implements Expression {
    private final Expression mLeft;
    private final Token mOperator;
    private final Expression mRight;

    public OperatorExpression(Expression left, Token operator, Expression right) {
        mLeft = left;
        mOperator = operator;
        mRight = right;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(mLeft);
        builder.append(" ").append(mOperator.getLexeme()).append(" ");
        builder.append(mRight);
        builder.append(")");
        return builder.toString();
    }

}