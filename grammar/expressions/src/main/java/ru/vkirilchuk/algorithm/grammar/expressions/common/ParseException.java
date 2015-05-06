package ru.vkirilchuk.algorithm.grammar.expressions.common;

public class ParseException extends RuntimeException {
    private static final long serialVersionUID = -8526479705887018360L;

    public ParseException(String message) {
      super(message);
    }
  }
