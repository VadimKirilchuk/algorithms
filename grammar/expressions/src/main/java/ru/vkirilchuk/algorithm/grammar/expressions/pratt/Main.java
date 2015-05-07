package ru.vkirilchuk.algorithm.grammar.expressions.pratt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"))) {
            do {
                try {
                    System.out.println("Please type in arithmetic expression (please use UTF-8) and press enter");
                    String expression = reader.readLine();
                    int value = new SimpleParser(expression).parseExpression().evaluate();
                    System.out.println("The value of your expression is: " + value);
                    System.out.println("Do you want to quit? (y/n)");
                } catch (RuntimeException ex) {
                    System.out.println("Exception happened. Is your expression correct?");
                    ex.printStackTrace(System.out);
                    System.out.println("Do you want to quit? (y/n)");
                }
            } while (!reader.readLine().contains("y"));
        }
    }
}
