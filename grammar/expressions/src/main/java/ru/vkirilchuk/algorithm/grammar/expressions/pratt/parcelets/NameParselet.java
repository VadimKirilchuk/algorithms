package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import ru.vkirilchuk.algorithm.grammar.expressions.NameExpression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParser;

/**
 * Simple parselet for a named variable like "abc".
 */
public class NameParselet implements PrefixParselet {
    public Expression parse(PrattParser parser, Token token) {
        return new NameExpression(token.getLexeme());
    }
}