package ru.kopylov.neuro2.logic.learning;

import org.apache.log4j.Logger;
import ru.kopylov.neuro2.model.Net;
import ru.kopylov.neuro2.utils.Print;
import ru.kopylov.neuro2.utils.UtilCalc;

/**
 * Created by se on 22.06.2018.
 */
public class TeacherImpl implements Teacher {
    private double learningSpeed = 1d;
    private static Logger logger = Logger.getLogger(TeacherImpl.class);
    @Override
    public void lern(Net net, float[] in, float[] expected) {
        if (net.getLayers()[0].getLenght() != in.length || net.getLayers()[net.getLayers().length - 1].getLenght() != expected.length) {
            throw new IllegalArgumentException("incorrect lerning data");
        }

        float[] actualOutput = net.calcForward(in);
        float[][]weights = net.getSynapses()[net.getSynapses().length-1].getWeigts();
        float[] errors = UtilCalc.diff(expected, actualOutput);
        Print.print(errors);

//        делители для расчета коэфициента внесения погрешности весом каждой связи для данного нейрона
        float[] sumsOfWeightsForThisNeuro = getDelimitters(weights);

        float[] previousLayer = net.getLayers()[net.getLayers().length-2].getInput();
        float[][]errorPerWeight = calcErrorPerWeights(weights, errors, previousLayer, sumsOfWeightsForThisNeuro);

//        корректировка весов последнего слоя
        UtilCalc.apply2D(weights, (i, j, r)->r[i][j]+=(errorPerWeight[i][j]/previousLayer[i])*learningSpeed);



//        logger.trace("Corrected ");
//        Print.print(weights);

//        dA = E/x

    }

    private float[][] calcErrorPerWeights(float[][] weights, float[] errors, float[] previousLayer, float[] delims) {
        float[][] result = new float[weights.length][weights[0].length];
        UtilCalc.apply2D(result, (i, j, r)->{

            r[i][j]=((weights[i][j]/delims[j])*errors[j]);
        });

        return result;
    }

    public float[] getDelimitters(float[][] weights) {
        float[] result = new float[weights[0].length];
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                result[j] += weights[i][j];
            }
        }
        return result;
    }

    public double getLearningSpeed() {
        return learningSpeed;
    }

    public void setLearningSpeed(double learningSpeed) {
        this.learningSpeed = learningSpeed;
    }


}


