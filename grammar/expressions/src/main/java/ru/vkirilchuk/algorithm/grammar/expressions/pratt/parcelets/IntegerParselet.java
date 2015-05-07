package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import ru.vkirilchuk.algorithm.grammar.expressions.IntegerExpression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParser;

/**
 * Simple parselet for a named variable like "abc".
 */
public class IntegerParselet implements PrefixParselet {
    public Expression parse(PrattParser parser, Token token) {
        return new IntegerExpression(token.getLexeme());
    }
}