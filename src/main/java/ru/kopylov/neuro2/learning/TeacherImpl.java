package ru.kopylov.neuro2.learning;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.logic.Calc;
import ru.kopylov.neuro2.logic.ErrorCounter;
import ru.kopylov.neuro2.logic.Normaliser;
import ru.kopylov.neuro2.model.Net;
import ru.kopylov.neuro2.model.Synapses;
import ru.kopylov.neuro2.utils.Print;
import ru.kopylov.neuro2.utils.UtilCalc;

/**
 * Created by se on 22.06.2018.
 */

public class TeacherImpl implements Teacher {
    private final float EPSIOLON; // Скорость обучения
    private final float ALPHA; // Момент

    public TeacherImpl(float EPSIOLON, float ALPHA) {
        this.EPSIOLON = EPSIOLON;
        this.ALPHA = ALPHA;
    }

    private static Logger logger = Logger.getLogger(TeacherImpl.class);
    @Override
    public double lern(Net net, float[] in, float[] expected) {
        if (net.getLayers()[0].getLenght() != in.length || net.getLayers()[net.getLayers().length - 1].getLenght() != expected.length) {
            throw new IllegalArgumentException("incorrect lerning data");
        }
        float[] actualOutput = net.passForward(in);
        double error = errorCounter.countError(actualOutput, expected);

        Synapses[] synapses = net.getSynapses();
        int lastIndex = synapses.length-1;
        calc.calcDeltasOut(expected, synapses[lastIndex], normaliser);
        for(int i=lastIndex; i>=0; i--){
                calc.calcDeltasHidden(synapses[i], normaliser);
            calc.updateWeights(synapses[i], EPSIOLON, ALPHA);
        }
        return error;
    }
    @Override
    public double lernEpoch(Net net, float[][] ins, float[][] outs, int times, int everySetPerTime) {

        for(int k=0; k<times;k++) {
            for (int i = 0; i < ins.length; i++) {
                for (int j = 0; j < everySetPerTime; j++) {
                    lern(net, ins[i], outs[i]);
                }
            }
        }



        return 0;
}

    private float[][] calcErrorPerWeights(float[][] weights, float[] errors, float[] previousLayer, float[] delims) {
        float[][] result = new float[weights.length][weights[0].length];
        UtilCalc.apply2D(result, (i, j, r)->{

            r[i][j]=((weights[i][j]/delims[j])*errors[j]);
        });

        return result;
    }

    @Autowired
    ErrorCounter errorCounter;

    @Autowired
    @Qualifier("parallelCalcImpl")
    Calc calc;

    @Autowired
    Normaliser normaliser;

}


