package ru.vkirilchuk.algorithm.grammar.expressions.lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;

import ru.vkirilchuk.algorithm.grammar.expressions.common.ParseException;

//TODO: make custom analyzer builder (should be quite interesting)
// i.e.
// when('+', consumeAndReturn LEXEME.PLUS)
// when('letter', buildIdentifierAndReturn LEXEME.IDENTIFIER)
public class Lexer {

    public Iterator<Token> parse(String input) {
        return parse(new StringReader(input));
    }

    public Iterator<Token> parse(Reader input) {
        return new LexerTokensIterator(new BufferedReader(input));
    }

    private class LexerTokensIterator implements Iterator<Token> {

        private final BufferedReader reader;

        // TODO: determine line and column

        private Token nextToken;
        private int currentLine;
        private int currentColumn;

        public LexerTokensIterator(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public boolean hasNext() {
            ensureTokenRead();
            return (nextToken.getType() != TokenType.END_OF_STREAM);
        }

        @Override
        public Token next() {
            ensureTokenRead();

            Token result = nextToken;
            nextToken = null;

            return result;
        }

        private void ensureTokenRead() {
            try {
                if (nextToken == null) {
                    nextToken = nextInternal();
                }
            } catch (IOException e) {
                throw new ParseException(e);
            }
        }

        private Token nextInternal() throws IOException {
            skipWhiteSpace();

            int characterCode = reader.read();
            if (characterCode == -1) {
                return new Token(TokenType.END_OF_STREAM, null, currentLine, currentColumn);
            }

            char character = (char) characterCode;
            if (character == '(') {
                return new Token(TokenType.LEFT_PAR, null, currentLine, currentColumn);
            }

            if (character == ')') {
                return new Token(TokenType.RIGHT_PAR, null, currentLine, currentColumn);
            }

            if (character == '+') {
                return new Token(TokenType.PLUS, String.valueOf(character), currentLine, currentColumn);
            }

            if (character == '-') {
                return new Token(TokenType.MINUS, String.valueOf(character), currentLine, currentColumn);
            }

            if (character == '*') {
                return new Token(TokenType.MUL, String.valueOf(character), currentLine, currentColumn);
            }

            if (character == '/') {
                return new Token(TokenType.DIV, String.valueOf(character), currentLine, currentColumn);
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
                return new Token(TokenType.NUMBER, numberBuilder.toString(), currentLine, currentColumn);
            }

            throw new UnsupportedOperationException("Unsupported character: " + character); // TODO:
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported");
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
}
