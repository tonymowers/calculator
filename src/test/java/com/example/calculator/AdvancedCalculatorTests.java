package com.example.calculator;

import com.example.calculator.helpers.AdvancedCalculatorUser;
import com.example.calculator.helpers.Point;
import com.example.calculator.helpers.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class AdvancedCalculatorTests {

    public static final Triangle THREE_FOUR_FIVE_TRIANGLE = new Triangle(
            new Point(0, 0),
            new Point(3, 4),
            new Point(3, 0)
    );
    private AdvancedCalculatorUser user;

    @BeforeEach
    public void setUp() {
        user = new AdvancedCalculatorUser();
    }

    @Test
    public void test_distance_between_points() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(3, 4);

        user.calculatesDistanceBetweenPoints(pointA, pointB);

        assertUserSeesResult(5);
    }

    @Test
    public void test_perimeter_of_triangle() {
        user.calculatesPerimeterOfTriangle(THREE_FOUR_FIVE_TRIANGLE);
        assertUserSeesResult(3 + 4 + 5);
    }

    @Test
    void test_area_of_triangle() {
        user.calculatesAreaOfTriangle(THREE_FOUR_FIVE_TRIANGLE);
        assertUserSeesResult( 6.0);
    }

    private void assertUserSeesResult(double value) {
        assertThat(user.seesResult(), is(closeTo(value,0.00005)));
    }
}