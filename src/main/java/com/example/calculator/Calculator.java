package com.example.calculator;

import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {

    private final Stack<Double> stackOfValues;

    Calculator() {
        stackOfValues = new Stack<>();
    }

    public String getDisplayContents() {
        return stackOfValues.isEmpty() ? "0" : "" + stackOfValues.peek();
    }

    public void enter(String valueOrOperation) {
        try {
            stackOfValues.push(Double.parseDouble(valueOrOperation));
        } catch (NumberFormatException e) {
            double a;
            double b;
            switch (valueOrOperation) {
                case "+" -> {
                    if (stackOfValues.size() < 2)
                        throw new EmptyStackException();
                    a = stackOfValues.pop();
                    b = stackOfValues.pop();
                    stackOfValues.push(a + b);
                }
                case "-" -> {
                    if (stackOfValues.size() < 2)
                        throw new EmptyStackException();
                    a = stackOfValues.pop();
                    b = stackOfValues.pop();
                    stackOfValues.push(b - a);
                }
                case "*" -> {
                    if (stackOfValues.size() < 2)
                        throw new EmptyStackException();
                    a = stackOfValues.pop();
                    b = stackOfValues.pop();
                    stackOfValues.push(b * a);
                }
                case "/" -> {
                    if (stackOfValues.size() < 2)
                        throw new EmptyStackException();
                    a = stackOfValues.pop();
                    b = stackOfValues.pop();
                    stackOfValues.push(b / a);
                }
                case "square" -> {
                    if (stackOfValues.size() < 1)
                        throw new EmptyStackException();
                    a = stackOfValues.pop();
                    stackOfValues.push(a * a);
                }
                case "square_root" -> {
                    if (stackOfValues.size() < 1)
                        throw new EmptyStackException();
                    a = stackOfValues.pop();
                    stackOfValues.push(Math.sqrt(a));
                }
                default -> throw new UnsupportedOperationException(valueOrOperation + " not supported");
            }
        }
    }
}
