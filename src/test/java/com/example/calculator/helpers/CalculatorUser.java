package com.example.calculator.helpers;

import com.example.calculator.Calculator;

public class CalculatorUser {
    private final Calculator calculator = new Calculator();

    public double seesResult() {
        return Double.parseDouble(calculator.getDisplayContents());
    }

    public void entersValue(double value) {
        calculator.enter("" + value);
    }

    public void addsValues() {
        calculator.enter("+");
    }

    public void subtractsValues() {
        calculator.enter("-");
    }

    public void dividesValues() {
        calculator.enter("/");
    }

    public void multipliesValues() {
        calculator.enter("*");
    }

    public void squaresValue() {
        calculator.enter("square");
    }

    public void calculatesSquareRoot() {
        calculator.enter("square_root");
    }
}
