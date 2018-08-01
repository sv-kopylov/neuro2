package ru.kopylov.neuro2.model;

import ru.kopylov.neuro2.logic.Normaliser;
import ru.kopylov.neuro2.utils.UtilCalc;

import java.io.Serializable;

/**
 * Created by se on 12.06.2018.
 */
public class Layer implements Serializable{
private float[] signals;
private float[] normalizedSignals;
private float[] deltas;

    public Layer(int numberOfNeurons) {
        signals = new float[numberOfNeurons];
        normalizedSignals = new float[numberOfNeurons];
        deltas  = new float[numberOfNeurons];
    }

    public void normalise(Normaliser norm){
        UtilCalc.apply1D(signals,(i, sig)->{
            normalizedSignals[i]=norm.normalise(sig[i]);
        });
    }

    public float[] getSignals() {
        return signals;
    }

    public int getLenght(){
        return signals.length;
    }

    public void setSignals(float[] signals) {
        this.signals = signals;
    }

    public float[] getNormalizedSignals() {
        return normalizedSignals;
    }

    public float[] getDeltas() {
        return deltas;
    }
}
