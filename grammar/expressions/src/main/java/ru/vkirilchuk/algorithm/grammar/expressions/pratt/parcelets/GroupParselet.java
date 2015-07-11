package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import java.util.Iterator;
import java.util.List;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.TokenType;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParser;

/**
 * Parses parentheses used to group an expression, like "a * (b + c)".
 */
public class GroupParselet implements PrefixParselet {

    public Expression parse(PrattParser parser, Token token, Iterator<Token> tokenIterator, List<Token> aheadTokens) {
        Expression expression = parser.parseExpression(tokenIterator, aheadTokens);
        parser.consume(TokenType.RIGHT_PAR, tokenIterator, aheadTokens);
        return expression;
    }
}