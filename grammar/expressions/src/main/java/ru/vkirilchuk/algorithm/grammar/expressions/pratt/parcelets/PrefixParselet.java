package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import java.util.Iterator;
import java.util.List;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParser;

/**
 * One of the two interfaces used by the Pratt parser. A PrefixParselet is associated with a token that appears at the
 * beginning of an expression. Its parse() method will be called with the consumed leading token, and the parselet is
 * responsible for parsing anything that comes after that token. This interface is also used for single-token
 * expressions like variables, in which case parse() simply doesn't consume any more tokens.
 *
 */
public interface PrefixParselet {
    Expression parse(PrattParser parser, Token token, Iterator<Token> tokenIterator, List<Token> aheadTokens);
}