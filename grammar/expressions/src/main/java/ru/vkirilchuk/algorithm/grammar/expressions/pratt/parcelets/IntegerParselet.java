package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import java.util.Iterator;

import ru.vkirilchuk.algorithm.grammar.expressions.IntegerExpression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParserBase;

/**
 * Simple parselet for a named variable like "abc".
 */
public class IntegerParselet implements PrefixParselet {
    public Expression parse(PrattParserBase parser, Token token, Iterator<Token> tokenIterator) {
        return new IntegerExpression(token.getLexeme());
    }
}