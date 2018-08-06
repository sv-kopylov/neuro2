package ru.kopylov.neuro2.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.utils.UtilCalc;

/**
 * Created by se on 12.06.2018.
 */
@Component
public class CalcImpl implements Calc {

    private static Logger logger = Logger.getLogger(CalcImpl.class);

    public void passForward(float[] input, float[][] weights, float[] output) {
        if(weights.length!=input.length||weights[0].length!=output.length){
            throw new IllegalArgumentException("Incorrect arrays dimension");
        }
        UtilCalc.apply2D(weights, (i, j, w)->output[j]+=weights[i][j]*input[i]);
    }

    public void calcDeltasOut(float[] expected, float[] actual, float[] deltasOut, Normaliser normaliser){
        if(expected.length!=deltasOut.length||normaliser==null){
            throw new IllegalArgumentException("Incorrect input in calc deltas");
        }
        UtilCalc.apply1D(expected, (i, exptd)->{
            deltasOut[i]=(exptd[i]-actual[i])*normaliser.derivite(actual[i]);
        });
    }

    public void calcBackward(float[] input, float[][] weights, float[] output) {

    }
}
