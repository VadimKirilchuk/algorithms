package ru.vkirilchuk.algorithm.grammar.expressions.common;

/**
 * Signals that some exception occurred during parse.
 *
 * @author vkirilchuk
 */
public class ParseException extends RuntimeException {
    private static final long serialVersionUID = -8526479705887018360L;

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
