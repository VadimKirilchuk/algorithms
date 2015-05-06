package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import java.io.IOException;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParser;

/**
 * One of the two parselet interfaces used by the Pratt parser. An InfixParselet is associated with a token that appears
 * in the middle of the expression it parses. Its parse() method will be called after the left-hand side has been
 * parsed, and it in turn is responsible for parsing everything that comes after the token. This is also used for
 * postfix expressions, in which case it simply doesn't consume any more tokens in its parse() call.
 */
public interface InfixParselet {

    Expression parse(PrattParser parser, Expression left, Token token) throws IOException;

    int getPrecedence();
}