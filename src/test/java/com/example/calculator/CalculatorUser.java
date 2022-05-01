package com.example.calculator;

class CalculatorUser {
    private final Calculator calculator = new Calculator();

    public double seesResult() {
        return Double.parseDouble(calculator.getDisplayContents());
    }

    public void entersValue(double value) {
        calculator.enter("" + value);
    }

    public void entersOperator(PrimitiveBinaryOperator operator) {
        calculator.enter(operator.getOperator());
    }

}
