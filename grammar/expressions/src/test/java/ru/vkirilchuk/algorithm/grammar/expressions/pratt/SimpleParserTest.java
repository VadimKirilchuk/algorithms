package ru.vkirilchuk.algorithm.grammar.expressions.pratt;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;

public class SimpleParserTest {

    private SimpleParser underTest;

    @Test
    public void testSimpleExpression() throws IOException {
        String expressionString = "1 + 2 * 5";
        underTest = new SimpleParser(expressionString);

        Expression expression = underTest.parseExpression();
        System.out.println(expression);
        assertNotNull(expression);
    }
}
