package com.example.calculator;

public enum PrimitiveBinaryOperator {
    ADD("+"),
    SUBTRACT("-"),
    DIVIDE("/"),
    MULTIPLY("*");

    private final String operator;

    PrimitiveBinaryOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
