package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import java.util.Iterator;
import java.util.List;

import ru.vkirilchuk.algorithm.grammar.expressions.PrefixExpression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParser;

/**
 * Generic prefix parselet for an unary arithmetic operator. Parses prefix
 * unary "-", "+", "~", and "!" expressions.
 */
public class PrefixOperatorParselet implements PrefixParselet {
  public PrefixOperatorParselet(int precedence) {
    mPrecedence = precedence;
  }

  public Expression parse(PrattParser parser, Token token, Iterator<Token> tokenIterator, List<Token> aheadTokens) {
    // To handle right-associative operators like "^", we allow a slightly
    // lower precedence when parsing the right-hand side. This will let a
    // parselet with the same precedence appear on the right, which will then
    // take *this* parselet's result as its left-hand argument.
    Expression right = parser.parseExpression(mPrecedence, tokenIterator, aheadTokens);

    return new PrefixExpression(token, right);
  }

  public int getPrecedence() {
    return mPrecedence;
  }

  private final int mPrecedence;
}