package ru.vkirilchuk.algorithm.grammar.expressions;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;

/**
 * A simple variable name expression like "abc".
 */
public class NameExpression implements Expression {
    private final String mName;

    public NameExpression(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return mName;
    }
}