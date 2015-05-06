package ru.vkirilchuk.algorithm.grammar.expressions.common;

/**
 * A token is a pair consisting of a token name and an optional attribute lexeme.
 *
 * <p>
 * A lexeme is a sequence of characters in the source program that matches the pattern for a token and is identified by
 * the lexical analyzer as an instance of that token.
 *
 * <p>
 * The token name is an abstract symbol representing a kind of lexical unit, e.g., a particular keyword, or sequence of
 * input characters denoting an identifier. The token names are the input symbols that the parser processes.
 *
 * @author vkirilchuk
 */
public class Token {

    private final TokenType type;
    private final String lexeme;
    private final int line;
    private final int column;

    public Token(TokenType type, String lexeme, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    public TokenType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
