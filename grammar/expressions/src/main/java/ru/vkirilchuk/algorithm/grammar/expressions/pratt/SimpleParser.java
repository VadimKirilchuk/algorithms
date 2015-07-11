package ru.vkirilchuk.algorithm.grammar.expressions.pratt;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Precedence;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.TokenType;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets.BinaryOperatorParselet;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets.GroupParselet;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets.IntegerParselet;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets.PrefixOperatorParselet;

public class SimpleParser extends PrattParser {

    public SimpleParser() {
        // Register all of the parselets for the grammar.

        // Register the ones that need special parselets.
        register(TokenType.NUMBER, new IntegerParselet());
        register(TokenType.LEFT_PAR, new GroupParselet());

        // Register the simple operator parselets.
        prefix(TokenType.PLUS, Precedence.PREFIX);
        prefix(TokenType.MINUS, Precedence.PREFIX);

        infixLeft(TokenType.PLUS, Precedence.SUM);
        infixLeft(TokenType.MINUS, Precedence.SUM);
        infixLeft(TokenType.MUL, Precedence.PRODUCT);
        infixLeft(TokenType.DIV, Precedence.PRODUCT);
    }

    /**
     * Registers a prefix unary operator parselet for the given token and precedence.
     */
    public void prefix(TokenType token, int precedence) {
        register(token, new PrefixOperatorParselet(precedence));
    }

    /**
     * Registers a left-associative binary operator parselet for the given token and precedence.
     */
    public void infixLeft(TokenType token, int precedence) {
        register(token, new BinaryOperatorParselet(precedence, false));
    }

    /**
     * Registers a right-associative binary operator parselet for the given token and precedence.
     */
    public void infixRight(TokenType token, int precedence) {
        register(token, new BinaryOperatorParselet(precedence, true));
    }
}
