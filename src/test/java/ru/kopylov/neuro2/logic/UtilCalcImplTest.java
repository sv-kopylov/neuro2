package ru.kopylov.neuro2.logic;

import org.junit.Before;
import org.junit.Test;
import ru.kopylov.neuro2.model.Layer;
import ru.kopylov.neuro2.model.Synapses;
import ru.kopylov.neuro2.utils.Cmp;
import ru.kopylov.neuro2.utils.Print;

import static org.junit.Assert.*;

/**
 * Created by se on 13.06.2018.
 */
public class UtilCalcImplTest {



    @Test
    public void testCalcForward(){

        Normaliser norm = new NormalaserImpl();
        Calc calc = new CalcImpl();
        Layer right = new Layer(2);
        Layer left = new Layer(2);
        left.getOutput()[0]=1;
        left.getOutput()[1]=2;
        Synapses synapses = new Synapses(left, right);
        synapses.getWeigts()[0][0]=1.1f;
        synapses.getWeigts()[0][1]=1.2f;
        synapses.getWeigts()[1][0]=2.1f;
        synapses.getWeigts()[1][1]=2.2f;

        float [] expected = {5.3f, 5.6f};

        calc.passForward(synapses, norm);
        Print.print(expected);
        Print.print(synapses.getRight().getInput());

        assertTrue(Cmp.compareFloatArrays(expected, synapses.getRight().getInput(), 0.01));
    }
    @Test
    public void testCalcDeltasOut(){
        Normaliser norm = new NormalaserImpl();
        Calc c = new CalcImpl();
        Layer right = new Layer(1);
        Layer left = new Layer(2);

        right.getOutput()[0] = 0.33f;
        Synapses synapses = new Synapses(left, right);

        float [] expected = {1f};


        float CORRECT = 0.148f;

        c.calcDeltasOut(expected, synapses, norm);
        Print.print(synapses.getRight().getDeltas());

        assertEquals(CORRECT, synapses.getRight().getDeltas()[0], 0.001);
    }

    @Test
    public void testCalcDeltasHidden(){
        Normaliser norm = new NormalaserImpl();
        Calc c = new CalcImpl();
        Layer right = new Layer(1);
        Layer left = new Layer(2);
        left.getInput()[0] = 0.45f;
        left.getInput()[1] = 0.78f;
        right.getInput()[0] = -0.672f;
        right.getDeltas()[0] = 0.148f;



        left.normalise(norm);
        right.normalise(norm);



        Synapses synapses = new Synapses(left, right);
        synapses.getWeigts()[0][0]=1.5f;
        synapses.getWeigts()[1][0]=2.3f;

        c.calcDeltasHidden(synapses, norm);

        float CORRECT = 0.053f;

        assertEquals(CORRECT, left.getDeltas()[0], 0.001);
        Print.print(left.getDeltas());

    }

}