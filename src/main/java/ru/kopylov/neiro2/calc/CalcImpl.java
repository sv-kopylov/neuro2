package ru.kopylov.neiro2.calc;

import org.apache.log4j.Logger;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by se on 12.06.2018.
 */
public class CalcImpl implements Calc {
    private static Logger logger = Logger.getLogger(CalcImpl.class);


    public void calcForward(float[] input, float[][] weights, float[] output) {
        for(int i=0;i<output.length;i++){
            for(int j=0;j<input.length;j++){
                output[i]+=weights[j][i]*input[j];
                logger.trace(i+1+" = "+weights[j][i]+" * "+input[j]);
            }
        }
    }

    public void calcBackward(float[] input, float[][] weights, float[] output) {

    }
}
