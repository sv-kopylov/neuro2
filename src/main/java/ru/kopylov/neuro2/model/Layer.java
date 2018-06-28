package ru.kopylov.neuro2.model;

import java.io.Serializable;

/**
 * Created by se on 12.06.2018.
 */
public class Layer implements Serializable{
private float[] signals;
private float[] normalizedSignals;

    public Layer(int numberOfNeurons) {
        signals = new float[numberOfNeurons];
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

}
