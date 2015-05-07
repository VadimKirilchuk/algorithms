package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import java.util.Iterator;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.TokenType;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParserBase;

/**
 * Parses parentheses used to group an expression, like "a * (b + c)".
 */
public class GroupParselet implements PrefixParselet {

    public Expression parse(PrattParserBase parser, Token token, Iterator<Token> tokenIterator) {
        Expression expression = parser.parseExpression(tokenIterator);
        parser.consume(TokenType.RIGHT_PAR, tokenIterator);
        return expression;
    }
}