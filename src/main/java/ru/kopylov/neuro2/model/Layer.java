package ru.kopylov.neuro2.model;

import ru.kopylov.neuro2.logic.Normaliser;
import ru.kopylov.neuro2.utils.UtilCalc;

import java.io.Serializable;

/**
 * Created by se on 12.06.2018.
 */
public class Layer implements Serializable{
private final float[] input;
private final float[] output;
private final float[] deltas;

    public Layer(int numberOfNeurons) {
        input = new float[numberOfNeurons];
        output = new float[numberOfNeurons];
        deltas  = new float[numberOfNeurons];
    }

    public void normalise(Normaliser norm){
        UtilCalc.apply1D(input,(i, sig)->{
            output[i]=norm.normalise(sig[i]);
        });
    }

    public float[] getInput() {
        return input;
    }

    public int getLenght(){
        return input.length;
    }

    public void setInput(float[] values) {
        UtilCalc.apply1D(values, (i, vals)->{
            output[i]=vals[i];
        });

    }

    public float[] getOutput() {
        return output;
    }

    public float[] getDeltas() {
        return deltas;
    }
}
