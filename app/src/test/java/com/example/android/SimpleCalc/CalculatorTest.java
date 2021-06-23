/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.SimpleCalc;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(2d, 2d);
        assertThat(resultAdd, is(equalTo(4d)));
    }

    /**
     * Test for adding negative numbers
     */
    @Test
    public void addTwoNegativeNumbers(){
        double resultNegativeNumbers = mCalculator.add(-1, -1);
        assertThat(resultNegativeNumbers, is(equalTo(-2d)));
    }

    /**
     * Test for adding two floats
     */
    @Test
    public void addTwoFloats(){
        double resultFloatAddition = mCalculator.add(1.5f, 1.5f);
        assertThat(resultFloatAddition, is(equalTo(3.0d)));
    }

    /**
     * Test for adding two floats with imprecise outcome
     */
    @Test
    public void addTwoInexactFloats(){
        double resultFloatAddition = mCalculator.add(1.444f, 1.444d);
        //assertThat(resultFloatAddition, is(equalTo(2.222d)));
        /**
         * Throws assertion error:
         * Expected: is <2.222>
         *      but: was <2.2219999418258665>
         * Expected :is <2.222>
         * Actual   :<2.2219999418258665>
         *
         *     Import:
         *     import static org.hamcrest.number.IsCloseTo.closeTo;
         *     Instead, write test:
         */
        assertThat(resultFloatAddition, is(closeTo(2.888, 0.01)));
    }

    /**
     * Test for simple subtraction
     */
    @Test
    public void subtractTwoNumbers(){
        double resultSubtract = mCalculator.sub(4, 2);
        assertThat(resultSubtract, is(equalTo(2d)));
    }

    /**
     * Test for subtracting negative numbers
     */
    @Test
    public void subtractTwoNegativeNumbers(){
        double resultSubtractNegatives = mCalculator.sub(-10.0, -5.0);
        assertThat(resultSubtractNegatives, is(equalTo(-5d)));
        double resultSubtractNegativesAndPositives = mCalculator.sub(-35.0, 10);
        assertThat(resultSubtractNegativesAndPositives, is(equalTo(-45d)));
    }

    /**
     * Test for simple multiplication
     */
    @Test
    public void multiplyTwoNumbers(){
        double resultMultiply = mCalculator.mul(6, 6);
        assertThat(resultMultiply, is(equalTo(36d)));
    }

    /**
     * Test for multiplying one number by zero
     */
    @Test
    public void multiplyNumbersToZero(){
        double resultZeroMultiply = mCalculator.mul(5, 0);
        assertThat(resultZeroMultiply, is(equalTo(0d)));
    }

    /**
     * Test for simple division
     */
    @Test
    public void divideTwoNumbers(){
        double resultDivide = mCalculator.div(9, 3);
        assertThat(resultDivide, is(equalTo(3d)));
    }

    /**
     * Test for dividing one number by zero
     */
    @Test
    public void divideOneNumberByZero(){
        double resultDivideByZero = mCalculator.div(25, 0);
        //assertThat(resultDivideByZero, is(equalTo(0d))); <--- Will result in the assertion error
        /**
         * This produces another assertion error because the result is infinity:
         * java.lang.AssertionError:
         * Expected: is <0.0>
         *      but: was <Infinity>
         * Expected :is <0.0>
         * Actual   :<Infinity>
         *
         *     You have to pass Double.POSITIVE_INFINITY to the equalTo() method
         *     See the method below for a negative infinity
         */
        assertThat(resultDivideByZero, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    @Test
    public void negativeInfinityDivision(){
        double resultNegativeInfinityDivision = mCalculator.div(-100, 0);
        assertThat(resultNegativeInfinityDivision, is(equalTo(Double.NEGATIVE_INFINITY))); //<---passes the test
    }


}