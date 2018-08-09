package ru.kopylov.neuro2.logic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by se on 08.08.2018.
 */
public class MSEErrorCounterTest {
    @Test
    public void countError1() throws Exception {
        float[] actual = {8f, 4f};
        float[] expected = {4f, 2f};
        double expectedResult = 10;

        ErrorCounter ec = new MSEErrorCounter();

        double actualResult = ec.countError(actual, expected);
//        System.out.println(actualResult);

        assertEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void countError() throws Exception {
        float[] errors = {4f, 2f};
        double expected = 10;

        ErrorCounter ec = new MSEErrorCounter();

        double actual = ec.countError(errors);
        System.out.println(actual);

        assertEquals(expected, actual, 0.00001);
    }

}