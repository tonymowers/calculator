package com.example.calculator;

import com.example.calculator.helpers.CalculatorUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class PrimitiveCalculatorTests {

    private CalculatorUser user;

    @BeforeEach
    void setUp() {
        user = new CalculatorUser();
    }

    @Test
    void test_initially_displays_zero() {
        assertUserSeesResult(0.0);
    }

    @Test
    void test_displays_last_entered_value_without_mutating_calculator() {
        user.entersValue(20.0);
        assertUserSeesResult(20.0);
        assertUserSeesResult(20.0);
    }


    @ParameterizedTest
    @EnumSource(value = Operation.class, names = {"ADD", "SUBTRACT", "DIVIDE", "MULTIPLY"})
    void test_operation_fails_without_at_least_two_values_on_stack(Operation operation) {
        assertUserSeesErrorForOperation(() -> operation.executeUsing(user));

        user.entersValue(1);
        assertUserSeesErrorForOperation(() -> operation.executeUsing(user));
    }

    @ParameterizedTest
    @EnumSource(value = Operation.class, names = {"SQUARE", "SQUARE_ROOT"})
    void test_operation_fails_without_at_least_one_values_on_stack(Operation operation) {
        assertUserSeesErrorForOperation(() -> operation.executeUsing(user));
    }

    @ParameterizedTest
    @MethodSource
    void test_operation_has_expected_result(Example example) {
        for (Double value : example.values) {
            user.entersValue(value);
        }

        example.operation.executeUsing(user);

        assertUserSeesResult(example.expectedResult);
    }


    private static Stream<Example> test_operation_has_expected_result() {
        return Stream.of(
                new Example(enteredValues(1.0, 2.0), Operation.ADD, 3),
                new Example(enteredValues(1.0, 2.0), Operation.SUBTRACT, -1),
                new Example(enteredValues(3.0, 7.0), Operation.MULTIPLY, 21),
                new Example(enteredValues(2.0, 3.0), Operation.DIVIDE, 0.6667),
                new Example(enteredValues(3.0), Operation.SQUARE, 9),
                new Example(enteredValues(9.0), Operation.SQUARE_ROOT, 3)
        );
    }

    private static List<Double> enteredValues(Double ... values) {
        return Arrays.asList(values);
    }

    private record Example(List<Double> values, UserOperation operation, double expectedResult) { }

    private void assertUserSeesErrorForOperation(Runnable operation) {
        assertThrows(EmptyStackException.class, operation::run);
    }

    private void assertUserSeesResult(double value) {
        assertThat(user.seesResult(), is(closeTo(value,0.00005)));
    }




    interface UserOperation {
        void executeUsing(CalculatorUser user);
    }

    private enum Operation implements UserOperation {
        ADD(CalculatorUser::addsValues),
        SUBTRACT(CalculatorUser::subtractsValues),
        MULTIPLY(CalculatorUser::multipliesValues),
        DIVIDE(CalculatorUser::dividesValues),
        SQUARE(CalculatorUser::squaresValue),
        SQUARE_ROOT(CalculatorUser::calculatesSquareRoot);

        private final UserOperation operation;

        Operation(UserOperation operation) {
            this.operation = operation;
        }

        @Override
        public void executeUsing(CalculatorUser user) {
            operation.executeUsing(user);
        }
    }
}