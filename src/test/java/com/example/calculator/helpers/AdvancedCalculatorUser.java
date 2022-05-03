package com.example.calculator.helpers;

public class AdvancedCalculatorUser extends CalculatorUser {
    public void calculatesDistanceBetweenPoints(Point pointA, Point pointB) {
        squaresDifference(pointA.x(), pointB.x());
        squaresDifference(pointA.y(), pointB.y());
        addsValues();
        calculatesSquareRoot();
    }

    public void calculatesPerimeterOfTriangle(Triangle triangle) {
        calculatesDistanceBetweenPoints(triangle.pointA(), triangle.pointB());
        calculatesDistanceBetweenPoints(triangle.pointB(), triangle.pointC());
        calculatesDistanceBetweenPoints(triangle.pointC(), triangle.pointA());
        addsValues();
        addsValues();
    }
    public void calculatesAreaOfTriangle(Triangle triangle) {
        calculatesDeterminantOfTriangle(triangle);
        dividesByTwo();
        calculatesAbsoluteValue();
    }

    private void dividesByTwo() {
        entersValue(2);
        dividesValues();
    }

    private void calculatesAbsoluteValue() {
        squaresValue();
        calculatesSquareRoot();
    }

    private void calculatesDeterminantOfTriangle(Triangle triangle) {
        xDifference(triangle.pointB(), triangle.pointA());
        yDifference(triangle.pointC(), triangle.pointA());
        multipliesValues();

        yDifference(triangle.pointB(), triangle.pointA());
        xDifference(triangle.pointC(), triangle.pointA());
        multipliesValues();

        subtractsValues();
    }

    private void xDifference(Point pointB, Point pointA) {
        subtractsValues(pointB.x(), pointA.x());
    }

    private void yDifference(Point pointB, Point pointA) {
        subtractsValues(pointB.y(), pointA.y());
    }

    private void squaresDifference(double valueA, double valueB) {
        subtractsValues(valueA, valueB);
        squaresValue();
    }

    private void subtractsValues(double valueA, double valueB) {
        entersValue(valueA);
        entersValue(valueB);
        subtractsValues();
    }
}
