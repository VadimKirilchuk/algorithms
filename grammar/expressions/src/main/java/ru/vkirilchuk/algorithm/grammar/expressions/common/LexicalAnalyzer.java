package ru.vkirilchuk.algorithm.grammar.expressions.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

//TODO: make custom analyzer builder (should be quite interesting)
// i.e.
// when('+', consumeAndReturn LEXEME.PLUS)
// when('letter', buildIdentifierAndReturn LEXEME.IDENTIFIER)
public class LexicalAnalyzer {

    private final Reader reader;

    public LexicalAnalyzer(String input) {
        this(new StringReader(input));
    }

    public LexicalAnalyzer(Reader input) {
        this.reader = new BufferedReader(input);
    }

    public Token nextToken() throws IOException { // TODO: custom exception?
        skipWhiteSpace();

        //TODO: determine line and column
        int line = 0;
        int column = 0;

        int characterCode = reader.read();
        if (characterCode == -1) {
            return new Token(TokenType.END_OF_STREAM, null, line, column);
        }

        char character = (char) characterCode;
        if (character == '(') {
            return new Token(TokenType.LEFT_PAR, null, line, column);
        }

        if (character == ')') {
            return new Token(TokenType.RIGHT_PAR, null, line, column);
        }

        if (character == '+') {
            return new Token(TokenType.PLUS, String.valueOf(character), line, column);
        }

        if (character == '-') {
            return new Token(TokenType.MINUS, String.valueOf(character), line, column);
        }

        if (character == '*') {
            return new Token(TokenType.MUL, String.valueOf(character), line, column);
        }

        if (character == '/') {
            return new Token(TokenType.DIV, String.valueOf(character), line, column);
        }

        if (Character.isDigit(character)) {
            StringBuilder numberBuilder = new StringBuilder();
            do {
                numberBuilder.append(character);
                reader.mark(1);
                characterCode = reader.read();
                character = (char) characterCode;
            } while ((characterCode != -1) && Character.isDigit(character));
            reader.reset();
            return new Token(TokenType.NUMBER, numberBuilder.toString(), line, column);
        }

        throw new UnsupportedOperationException("Unsupported character: " + character); // TODO:
    }

    private void skipWhiteSpace() throws IOException {
        int characterCode;
        char character;
        do {
            reader.mark(1);
            characterCode = reader.read();
            character = (char) characterCode;
        } while ((characterCode != -1) && Character.isWhitespace(character));
        reader.reset();
    }
}
