package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import java.io.IOException;

import ru.vkirilchuk.algorithm.grammar.expressions.OperatorExpression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParser;

/**
 * Generic infix parselet for a binary arithmetic operator. The only difference when parsing, "+", "-", "*", "/", and
 * "^" is precedence and associativity, so we can use a single parselet class for all of those.
 */
public class BinaryOperatorParselet implements InfixParselet {
    public BinaryOperatorParselet(int precedence, boolean isRight) {
        mPrecedence = precedence;
        mIsRight = isRight;
    }

    public Expression parse(PrattParser parser, Expression left, Token token) throws IOException {
        // To handle right-associative operators like "^", we allow a slightly
        // lower precedence when parsing the right-hand side. This will let a
        // parselet with the same precedence appear on the right, which will then
        // take *this* parselet's result as its left-hand argument.
        Expression right = parser.parseExpression(mPrecedence - (mIsRight ? 1 : 0));

        return new OperatorExpression(left, token, right);
    }

    public int getPrecedence() {
        return mPrecedence;
    }

    private final int mPrecedence;
    private final boolean mIsRight;
}