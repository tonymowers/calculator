package com.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
    @EnumSource(Operation.class)
    void test_binary_operation_performed_on_empty_stack_fails(Operation operation) {
        assertUserSeesErrorForOperation(operation);
    }

    @ParameterizedTest
    @EnumSource(Operation.class)
    void test_binary_operation_performed_on_one_value_fails(Operation operation) {
        user.entersValue(10.0);
        assertUserSeesErrorForOperation(operation);
    }


    @ParameterizedTest
    @MethodSource
    void test_binary_operation_has_expected_result(Example example) {
        user.entersValue(example.valueA);
        user.entersValue(example.valueB);

        user.performsCustomOperation(example.operation);

        assertUserSeesResult(example.expectedResult);
    }

    private static Stream<Example> test_binary_operation_has_expected_result() {
        return Stream.of(
                new Example(1, 2, Operation.ADD, 3),
                new Example(1, 2, Operation.SUBTRACT, -1),
                new Example(3, 7, Operation.MULTIPLY, 21),
                new Example(2, 3, Operation.DIVIDE, 0.6667)
        );
    }

    private record Example(
            double valueA,
            double valueB,
            CustomOperation<CalculatorUser> operation,
            double expectedResult) {

    }


    private void assertUserSeesErrorForOperation(CustomOperation<CalculatorUser> operation) {
        assertThrows(EmptyStackException.class, () -> operation.executeUsing(user));
    }

    private void assertUserSeesResult(double value) {
        assertThat(user.seesResult(), is(closeTo(value,0.00005)));
    }



    private enum Operation implements CustomOperation<CalculatorUser> {
        ADD(CalculatorUser::addsValues),
        SUBTRACT(CalculatorUser::subtractsValues),
        MULTIPLY(CalculatorUser::multipliesValues),
        DIVIDE(CalculatorUser::dividesValues);

        private final CustomOperation<CalculatorUser> operation;

        Operation(CustomOperation<CalculatorUser> operation) {
            this.operation = operation;
        }

        @Override
        public void executeUsing(CalculatorUser user) {
            operation.executeUsing(user);
        }
    }
}