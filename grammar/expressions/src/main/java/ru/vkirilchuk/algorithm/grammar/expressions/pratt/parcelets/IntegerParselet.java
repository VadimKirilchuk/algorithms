package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import java.util.Iterator;
import java.util.List;

import ru.vkirilchuk.algorithm.grammar.expressions.IntegerExpression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParser;

/**
 * Simple parselet for a named variable like "abc".
 */
public class IntegerParselet implements PrefixParselet {
    public Expression parse(PrattParser parser, Token token, Iterator<Token> tokenIterator, List<Token> aheadTokens) {
        return new IntegerExpression(token.getLexeme());
    }
}