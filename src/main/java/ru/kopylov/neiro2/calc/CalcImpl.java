package ru.kopylov.neiro2.calc;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by se on 12.06.2018.
 */
public class CalcImpl implements Calc {


    public void calcForward(float[] input, float[][] weights, float[] output) {
        for(int i=0;i<output.length;i++){
            for(int j=0;j<input.length;j++){
                output[i]+=weights[j][i]*input[j];
                System.out.println(i+" = "+weights[i][j]+" * "+input[j]);
            }
        }
    }

    public void calcBackward(float[] input, float[][] weights, float[] output) {

    }
}
