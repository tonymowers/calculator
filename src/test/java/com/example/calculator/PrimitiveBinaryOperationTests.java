package com.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.EmptyStackException;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimitiveBinaryOperationTests {

    private CalculatorUser user;

    @BeforeEach
    void setUp() {
        user = new CalculatorUser();
    }

    @Test
    void initially_displays_zero() {
        assertUserSeesResultValue(0.0);
    }

    @Test
    void displays_last_entered_value_without_mutating_calculator() {
        user.entersValue(20.0);
        assertUserSeesResultValue(20.0);
        assertUserSeesResultValue(20.0);
    }

    @ParameterizedTest
    @EnumSource(PrimitiveBinaryOperator.class)
    void operation_performed_on_empty_stack_is_error(PrimitiveBinaryOperator operator) {
        assertUserSeesErrorForOperation(operator);
    }

    @ParameterizedTest
    @EnumSource(PrimitiveBinaryOperator.class)
    void operation_performed_on_one_value_is_error(PrimitiveBinaryOperator operator) {
        user.entersValue(10.0);
        assertUserSeesErrorForOperation(operator);
    }

    @ParameterizedTest
    @MethodSource
    void binary_operation_should_have_specified_result(
            double valueA,
            double valueB,
            PrimitiveBinaryOperator operator,
            double expectedResult
    ) {
        user.entersValue(valueA);
        user.entersValue(valueB);
        user.entersOperator(operator);
        assertUserSeesResultValue(expectedResult);
    }

    private static Stream<Arguments> binary_operation_should_have_specified_result() {
        return Stream.of(
                Arguments.of(1, 2, PrimitiveBinaryOperator.ADD, 3),
                Arguments.of(5, 3, PrimitiveBinaryOperator.SUBTRACT, 2),
                Arguments.of( 5, 3, PrimitiveBinaryOperator.MULTIPLY, 15),
                Arguments.of( 5, 3, PrimitiveBinaryOperator.DIVIDE, 1.6667)
        );
    }


    private void assertUserSeesErrorForOperation(PrimitiveBinaryOperator operator) {
        assertThrows(EmptyStackException.class, () -> user.entersOperator(operator));
    }

    private void assertUserSeesResultValue(double value) {
        assertThat(user.seesResultValue(), is(closeTo(value,0.00005)));
    }

    private static class CalculatorUser {
        private final Calculator calculator = new Calculator();

        public double seesResultValue() {
            return Double.parseDouble(calculator.getDisplayContents());
        }

        public void entersValue(double value) {
            calculator.enter("" + value);
        }

        public void entersOperator(PrimitiveBinaryOperator operator) {
            calculator.enter(operator.getOperator());
        }

    }

}