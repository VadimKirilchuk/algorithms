package ru.vkirilchuk.algorithm.grammar.expressions.common;

import java.io.Reader;

public interface Parser {

    Expression parse(String input);

    Expression parse(Reader input);

}