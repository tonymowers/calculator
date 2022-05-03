package com.example.calculator;

import com.example.calculator.helpers.AdvancedCalculatorUser;
import com.example.calculator.helpers.Point;
import com.example.calculator.helpers.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class AdvancedCalculatorTests {

    private static final Triangle THREE_FOUR_FIVE_RIGHT_TRIANGLE = new Triangle(
            new Point(0, 0),
            new Point(3, 4),
            new Point(3, 0)
    );

    private static final Triangle ONE_BY_ONE_RIGHT_TRIANGLE = new Triangle(
            new Point(1, 1),
            new Point(2, 2),
            new Point(2, 1)
    );

    private AdvancedCalculatorUser user;

    @BeforeEach
    public void setUp() {
        user = new AdvancedCalculatorUser();
    }

    @ParameterizedTest
    @MethodSource
    public void test_distance_between_points(Point pointA, Point pointB, double expectedDistance) {
        user.calculatesDistanceBetweenPoints(pointA, pointB);

        assertUserSeesResult(expectedDistance);
    }

    @ParameterizedTest
    @MethodSource
    public void test_perimeter_of_triangle(Triangle triangle, double expectedPerimeter) {
        user.calculatesPerimeterOfTriangle(triangle);
        assertUserSeesResult(expectedPerimeter);
    }

    @ParameterizedTest
    @MethodSource
    void test_area_of_triangle(Triangle triangle, double expectedArea) {
        user.calculatesAreaOfTriangle(triangle);
        assertUserSeesResult( expectedArea);
    }

    private static Stream<Arguments> test_distance_between_points() {
        return Stream.of(
                Arguments.of(new Point(0, 0), new Point(3, 4), 5.0),
                Arguments.of(new Point(10, 10), new Point(40, 50), 50.0)
        );
    }

    private static Stream<Arguments> test_perimeter_of_triangle() {
        return Stream.of(
                Arguments.of(THREE_FOUR_FIVE_RIGHT_TRIANGLE, 3 + 4 + 5),
                Arguments.of(ONE_BY_ONE_RIGHT_TRIANGLE, 1 + 1 + Math.sqrt(2.0))
        );
    }

    private static Stream<Arguments> test_area_of_triangle() {
        return Stream.of(
                Arguments.of(THREE_FOUR_FIVE_RIGHT_TRIANGLE, 3 * 4 / 2.0),
                Arguments.of(ONE_BY_ONE_RIGHT_TRIANGLE, 1.0 * 1 / 2.0)
        );
    }

    private void assertUserSeesResult(double value) {
        assertThat(user.seesResult(), is(closeTo(value,0.00005)));
    }
}