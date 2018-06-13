package ru.kopylov.neiro2.calc;

/**
 * Created by se on 12.06.2018.
 */
public interface Calc {
    public void calcForward(float[] input, float[][] weights, float[] output);
    public void calcBackward(float[] input, float[][] weights, float[] output);
}
