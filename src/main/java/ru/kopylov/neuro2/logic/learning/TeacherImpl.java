package ru.kopylov.neuro2.logic.learning;

import ru.kopylov.neuro2.model.Net;
import ru.kopylov.neuro2.utils.Print;
import ru.kopylov.neuro2.utils.UtilCalc;

/**
 * Created by se on 22.06.2018.
 */
public class TeacherImpl implements Teacher {
    @Override
    public void lern(Net net, float[] in, float[] expected) {
        if (net.getLayers()[0].getLenght() != in.length || net.getLayers()[net.getLayers().length - 1].getLenght() != expected.length) {
            throw new IllegalArgumentException("incorrect lerning data");
        }

        float[] actualOutput = net.calcForward(in);
        float[][]weights = net.getSynapses()[net.getSynapses().length-1].getWeigts();
        float[] errors = UtilCalc.diff(expected, actualOutput);
//        делители для расчета коэфициента внесения погрешности весом каждой связи для данного нейрона
        float[] sumsOfWeightsForThisNeuro = getDelimitters(weights);
        float[] previousLayer = net.getLayers()[net.getLayers().length-2].getSignals();

        float[][]errorPerWeight = calcErrorPerWeights(weights, errors, previousLayer, sumsOfWeightsForThisNeuro);

        System.out.println("Errors");
        Print.print(errorPerWeight);


//        dA = E/x

        
        sumsOfWeightsForThisNeuro = getDelimitters(weights);
        apply2D(weights, (i, j, w) -> {
//            w[i][j]+=(errors[i]*)
        });



        for (int i =  net.getSynapses().length-2; i > 0; i--) {
            weights = net.getSynapses()[i].getWeigts();
            sumsOfWeightsForThisNeuro = getDelimitters(weights);
            for (int j = 0; j < errors.length; j++) {

            }
        }
    }

    private float[][] calcErrorPerWeights(float[][] weights, float[] errors, float[] previousLayer, float[] delims) {
        float[][] result = new float[weights.length][weights[0].length];
        apply2D(result, (i, j, r)->{
            r[i][j]=((weights[i][j]/delims[i])*errors[i])/previousLayer[i];
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



    public void apply2D(float[][] weights, ThreeConsumer<Integer, Integer, float[][]> consumer){
        for (int i=0; i<weights.length;i++){
            for(int j=0; j<weights[0].length;j++){
                consumer.accept(i, j, weights);
            }
        }
    }
}
@FunctionalInterface
interface ThreeConsumer<T, U, V>{
    void accept(T t, U u, V v);
}


