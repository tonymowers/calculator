package com.example.calculator;

public class CalculatorUser {
    private final Calculator calculator = new Calculator();

    public double seesResult() {
        return Double.parseDouble(calculator.getDisplayContents());
    }

    public void entersValue(double value) {
        calculator.enter("" + value);
    }

    public void performsCustomOperation(CustomOperation<CalculatorUser> operation) {
        operation.executeUsing(this);
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
}
