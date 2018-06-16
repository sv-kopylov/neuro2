package ru.kopylov.neuro2.model;

/**
 * Created by se on 12.06.2018.
 */
public class Layer {
private float [] signals;

    public Layer(int numberOfNeurons) {
        signals = new float[numberOfNeurons];
    }

    public float[] getSignals() {
        return signals;
    }

    public int getLenght(){
        return signals.length;
    }
}
