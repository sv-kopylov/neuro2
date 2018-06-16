package ru.kopylov.neuro2.logic;

/**
 * Created by se on 12.06.2018.
 */
public interface Calc {
    public void calcForward(float[] input, float[][] weights, float[] output);
    public void calcBackward(float[] input, float[][] weights, float[] output);
}
