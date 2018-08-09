package ru.kopylov.neuro2.logic;

/**
 * Created by se on 08.08.2018.
 */
public interface ErrorCounter {
    double countError(float[] errors);
    double countError(float[] actual, float[] expected);

}
