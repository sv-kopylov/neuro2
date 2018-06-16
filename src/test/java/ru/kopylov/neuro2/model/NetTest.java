package ru.kopylov.neuro2.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by se on 15.06.2018.
 */
public class NetTest {
    private final int LAYERS_NUM =4;
    private final int NEURO_NUM = 4;
    private final int IN_LAST_LAYER_NEURO_NUM =2;
    @Test
    public void testConsructor(){

        Net net = new Net(LAYERS_NUM,NEURO_NUM,IN_LAST_LAYER_NEURO_NUM);


        assertTrue(net.getLayers()[0].getLenght()==NEURO_NUM);
        assertTrue(net.getLayers()[1].getLenght()==NEURO_NUM);
        assertTrue(net.getLayers()[2].getLenght()==NEURO_NUM);
        assertTrue(net.getLayers()[3].getLenght()==IN_LAST_LAYER_NEURO_NUM);

        assertTrue(net.getSynapses()[0].getWeigts().length==NEURO_NUM);
        assertTrue(net.getSynapses()[0].getWeigts()[0].length==NEURO_NUM);
        assertTrue(net.getSynapses()[1].getWeigts().length==NEURO_NUM);
        assertTrue(net.getSynapses()[1].getWeigts()[0].length==NEURO_NUM);
        assertTrue(net.getSynapses()[2].getWeigts().length==NEURO_NUM);
        assertTrue(net.getSynapses()[2].getWeigts()[0].length==IN_LAST_LAYER_NEURO_NUM);

    }

}