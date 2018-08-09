package ru.kopylov.neuro2.logic;

import ru.kopylov.neuro2.model.Synapses;

/**
 * Created by se on 12.06.2018.
 */
public interface Calc {

    void passForward(Synapses synapses, Normaliser normaliser);

    void calcDeltasOut(float[] expected, Synapses synapses, Normaliser normaliser);

    void calcDeltasHidden(Synapses synapses, Normaliser normaliser);

    float[] multiplyReverse(float[][] weights, float[] deltas);

    void updateWeights(Synapses synapses, float epsilon, float alpha);
}
