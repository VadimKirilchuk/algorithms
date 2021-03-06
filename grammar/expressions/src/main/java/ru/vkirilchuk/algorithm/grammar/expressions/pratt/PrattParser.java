package ru.vkirilchuk.algorithm.grammar.expressions.pratt;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.ParseException;
import ru.vkirilchuk.algorithm.grammar.expressions.common.Parser;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.Lexer;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.Token;
import ru.vkirilchuk.algorithm.grammar.expressions.lexer.TokenType;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets.InfixParselet;
import ru.vkirilchuk.algorithm.grammar.expressions.pratt.parcelets.PrefixParselet;

public class PrattParser implements Parser {

    private final Lexer lexer = new Lexer();
    private final Map<TokenType, PrefixParselet> prefixParselets = new HashMap<TokenType, PrefixParselet>();
    private final Map<TokenType, InfixParselet> infixParselets = new HashMap<TokenType, InfixParselet>();

    // TODO: I vote for builders
    public void register(TokenType token, PrefixParselet parselet) {
        prefixParselets.put(token, parselet);
    }

    public void register(TokenType token, InfixParselet parselet) {
        infixParselets.put(token, parselet);
    }

    @Override
    public Expression parse(String input) {
        return parse(new StringReader(input));
    }

    @Override
    public Expression parse(Reader input) {
        Iterator<Token> tokenIterator = lexer.parse(input);
        List<Token> aheadTokens = new ArrayList<Token>();
        return parseExpression(tokenIterator, aheadTokens);
    }

    public Expression parseExpression(Iterator<Token> tokenIterator, List<Token> aheadTokens) {
        return parseExpression(0, tokenIterator, aheadTokens);
    }

    public Expression parseExpression(int precedence, Iterator<Token> tokenIterator, List<Token> aheadTokens) {
        Token token = consume(tokenIterator, aheadTokens);

        if (token.getType() == TokenType.END_OF_STREAM) {
            throw new ParseException("Unexpected end of stream");
        }

        PrefixParselet prefix = prefixParselets.get(token.getType());

        if (prefix == null) {
            throw new ParseException("Could not parse \"" + token.getLexeme() + "\".");
        }

        Expression left = prefix.parse(this, token, tokenIterator, aheadTokens);

        while (precedence < getPrecedence(tokenIterator, aheadTokens)) {
            token = consume(tokenIterator, aheadTokens);

            InfixParselet infix = infixParselets.get(token.getType());
            left = infix.parse(this, left, token, tokenIterator, aheadTokens);
        }

        return left;
    }

    public boolean match(TokenType expected, Iterator<Token> tokenIterator, List<Token> aheadTokens) {
        Token token = lookAhead(0, tokenIterator, aheadTokens);
        if (token.getType() != expected) {
            return false;
        }

        consume(tokenIterator, aheadTokens);
        return true;
    }

    public Token consume(TokenType expected, Iterator<Token> tokenIterator, List<Token> aheadTokens) {
        Token token = lookAhead(0, tokenIterator, aheadTokens);
        if (token.getType() != expected) {
            throw new RuntimeException("Expected token " + expected + " and found " + token.getType());
        }

        return consume(tokenIterator, aheadTokens);
    }

    public Token consume(Iterator<Token> tokenIterator, List<Token> aheadTokens) {
        // Make sure we've read the token.
        lookAhead(0, tokenIterator, aheadTokens);

        return aheadTokens.remove(0);
    }

    private Token lookAhead(int distance, Iterator<Token> tokenIterator, List<Token> aheadTokens) {
        // Read in as many as needed.
        while (distance >= aheadTokens.size()) {
            aheadTokens.add(tokenIterator.next());
        }

        // Get the queued token.
        return aheadTokens.get(distance);
    }

    private int getPrecedence(Iterator<Token> tokenIterator,  List<Token> aheadTokens) {
        InfixParselet parser = infixParselets.get(lookAhead(0, tokenIterator, aheadTokens).getType());
        if (parser != null) {
            return parser.getPrecedence();
        }

        return 0;
    }
}
