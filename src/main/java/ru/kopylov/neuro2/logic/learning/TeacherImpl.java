package ru.kopylov.neuro2.logic.learning;

import ru.kopylov.neuro2.model.Net;

/**
 * Created by se on 22.06.2018.
 */
public class TeacherImpl implements Teacher {
    @Override
    public void lern(Net net, float[] in, float[] expected) {
        if (net.getLayers()[0].getLenght() != in.length || net.getLayers()[net.getLayers().length - 1].getLenght() != expected.length) {
            throw new IllegalArgumentException("incorrect lerning data");
        }
        float[] currentVallues = net.calcForward(in);
//         корректировка весов последнего слоя
        float[][]weights = net.getSynapses()[net.getSynapses().length-1].getWeigts();
        float[] errors = getErrors(net, in, expected);
//        делители для расчета коэфициента внесения погрешности весом каждой связи для данного нейрона
        float[] sumsOfWeightsForThisNeuro = getDelimitters(weights);
        


//        dA = E*A/y

        
        sumsOfWeightsForThisNeuro = getDelimitters(weights);
        applyToWeights(weights, (i, j, w) -> {
//            w[i][j]+=(errors[i]*w[i][j])
        });



        for (int i =  net.getSynapses().length-2; i > 0; i--) {
            weights = net.getSynapses()[i].getWeigts();
            sumsOfWeightsForThisNeuro = getDelimitters(weights);
            for (int j = 0; j < errors.length; j++) {

            }
        }
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

    private float[] getErrors(Net net, float[] in, float[] expected) {
        net.input(in);
        float[] result = new float[expected.length];
        float[] actual = net.calcForward();
        for (int i = 0; i < actual.length; i++) {
            result[i] = expected[i] - actual[i];
        }
        return result;
    }

    public void applyToWeights(float[][] weights, ThreeConsumer<Integer, Integer, float[][]> consumer){
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


