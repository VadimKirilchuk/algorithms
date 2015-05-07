package ru.vkirilchuk.algorithm.grammar.expressions.pratt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.carrotsearch.randomizedtesting.RandomizedTest;

import ru.vkirilchuk.algorithm.grammar.expressions.common.Expression;
import ru.vkirilchuk.algorithm.grammar.expressions.common.ParseException;

public class SimpleParserTest extends RandomizedTest {

    private SimpleParser underTest;

    @Before
    public void before() {
        underTest = new SimpleParser();
    }

    @Test
    public void testRandomOperation() throws IOException {
        int first = randomShort();
        int second = randomShort();

        List<String> operations = new ArrayList<>();
        operations.add("+");
        operations.add("-");
        operations.add("*");
        operations.add("/");

        int randomOperationNum = getRandom().nextInt(operations.size());
        String randomOperation = operations.get(randomOperationNum);

        if ("/".equals(randomOperation)) {
            while (second == 0) { // do not divide on zero
                second = randomShort();
            }
        }

        List<Integer> expectations = new ArrayList<>();
        expectations.add(first + second);
        expectations.add(first - second);
        expectations.add(first * second);
        expectations.add(first / second);

        String expressionString = first + randomOperation + second;

        Expression expression = underTest.parse(expressionString);
        assertNotNull(expression);

        assertEquals((int) expectations.get(randomOperationNum), expression.evaluate());
    }

    @Test(expected = ArithmeticException.class)
    public void testZeroDivideExpression() throws IOException {
        String expressionString = "15 / 0";

        Expression expression = underTest.parse(expressionString);
        expression.evaluate();
    }

    @Test(expected = ParseException.class)
    public void testEmptyExpression() throws IOException {
        String expressionString = "";

        Expression expression = underTest.parse(expressionString);
        assertNotNull(expression);

        expression.evaluate();
    }

    @Test(expected = ParseException.class)
    public void testWhitespaceExpression() throws IOException {
        String expressionString = "     \r\n    \n";

        Expression expression = underTest.parse(expressionString);
        assertNotNull(expression);

        expression.evaluate();
    }

    @Test
    public void testSimpleExpression() throws IOException {
        String expressionString = "1 + 2 * 5";

        Expression expression = underTest.parse(expressionString);
        assertNotNull(expression);
        assertEquals(11, expression.evaluate());
    }

    @Test
    public void testComplexExpression() throws IOException {
        String expressionString = "-(2 + 3) * 5 - 6 - 7 + (-5 / 5) * 2 + 1";
        // -25 - 13 -2 + 1 = -39

        Expression expression = underTest.parse(expressionString);
        assertNotNull(expression);

        assertEquals(-39, expression.evaluate());
    }

    @Test
    public void testParExpression() throws IOException {
        String expressionString = "(1 + 2) * 5";

        Expression expression = underTest.parse(expressionString);
        assertNotNull(expression);

        assertEquals(15, expression.evaluate());
    }
}
