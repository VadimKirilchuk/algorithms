package ru.vkirilchuk.algorithm.grammar.expressions.pratt;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.LexicalAnalyzer;
import ru.vkirilchuk.algorithm.grammar.expressions.common.ParseException;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.common.TokenType;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets.InfixParselet;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets.PrefixParselet;

public class PrattParser {

    private final LexicalAnalyzer lexer;
    private final List<Token> aheadTokens = new ArrayList<Token>();
    private final Map<TokenType, PrefixParselet> prefixParselets = new HashMap<TokenType, PrefixParselet>();
    private final Map<TokenType, InfixParselet> infixParselets = new HashMap<TokenType, InfixParselet>();

    //TODO: parser should be stateless about input
    // it should be just parser.parse(expression)
    // and all other happens inside in local context
    public PrattParser(String input) {
        lexer = new LexicalAnalyzer(input);
    }

    public PrattParser(Reader input) {
        lexer = new LexicalAnalyzer(input);
    }

    public Expression parseExpression() throws IOException {
        return parseExpression(0);
    }

    //TODO: I vote for builders
    public void register(TokenType token, PrefixParselet parselet) {
        prefixParselets.put(token, parselet);
    }

    public void register(TokenType token, InfixParselet parselet) {
        infixParselets.put(token, parselet);
    }

    public Expression parseExpression(int precedence) throws IOException {
        Token token = consume();
        PrefixParselet prefix = prefixParselets.get(token.getType());

        if (prefix == null) {
            throw new ParseException("Could not parse \"" + token.getLexeme() + "\".");
        }

        Expression left = prefix.parse(this, token);

        while (precedence < getPrecedence()) {
            token = consume();

            InfixParselet infix = infixParselets.get(token.getType());
            left = infix.parse(this, left, token);
        }

        return left;
    }

    public boolean match(TokenType expected) throws IOException {
        Token token = lookAhead(0);
        if (token.getType() != expected) {
            return false;
        }

        consume();
        return true;
    }

    public Token consume(TokenType expected) throws IOException {
        Token token = lookAhead(0);
        if (token.getType() != expected) {
            throw new RuntimeException("Expected token " + expected + " and found " + token.getType());
        }

        return consume();
    }

    public Token consume() throws IOException {
        // Make sure we've read the token.
        lookAhead(0);

        return aheadTokens.remove(0);
    }

    private Token lookAhead(int distance) throws IOException {
        // Read in as many as needed.
        while (distance >= aheadTokens.size()) {
            aheadTokens.add(lexer.nextToken());
        }

        // Get the queued token.
        return aheadTokens.get(distance);
    }

    private int getPrecedence() throws IOException {
        InfixParselet parser = infixParselets.get(lookAhead(0).getType());
        if (parser != null) {
            return parser.getPrecedence();
        }

        return 0;
    }
}
