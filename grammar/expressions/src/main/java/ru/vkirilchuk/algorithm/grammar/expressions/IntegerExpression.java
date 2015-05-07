package ru.vkirilchuk.algorithm.grammar.expressions;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;

/**
 * A simple integer number expression like "12345".
 */
public class IntegerExpression implements Expression {
    private final int value;

    public IntegerExpression(String value) {
        this.value = Integer.valueOf(value);
    }

    @Override
    public int evaluate() {
        return value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}