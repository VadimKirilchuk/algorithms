package ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets;

import java.io.IOException;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.common.TokenType;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.PrattParser;

/**
 * Parses parentheses used to group an expression, like "a * (b + c)".
 */
public class GroupParselet implements PrefixParselet {

    public Expression parse(PrattParser parser, Token token) throws IOException {
        Expression expression = parser.parseExpression();
        parser.consume(TokenType.RIGHT_PAR);
        return expression;
    }
}