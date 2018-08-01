package ru.kopylov.neuro2.model;

import org.junit.Test;
import ru.kopylov.neuro2.logic.NormalaserImpl;
import ru.kopylov.neuro2.logic.Normaliser;
import ru.kopylov.neuro2.utils.Cmp;

import static org.junit.Assert.*;

/**
 * Created by se on 01.08.2018.
 */
public class LayerTest {
    @Test
    public void normalise() throws Exception {
        float[] input = new float[4];
        float[] actual = new float[4];

        Normaliser normaliser = new NormalaserImpl();

        input[0]= -4;
        input[1]=  0;
        input[2]=  0.5f;
        input[3]= 150;

        for(int i=0; i<4; i++){
            actual[i]=normaliser.normalise(input[i]);
        }

        Layer layer = new Layer(4);
        for(int i=0; i<4; i++){
            layer.getSignals()[i]=input[i];
        }

        layer.normalise(normaliser);

        assertTrue(Cmp.compareFloatArrays(actual, layer.getNormalizedSignals(), 0.0001));


    }

}