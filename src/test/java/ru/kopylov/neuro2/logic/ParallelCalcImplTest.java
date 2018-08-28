package ru.kopylov.neuro2.logic;

import org.junit.Test;
import ru.kopylov.neuro2.model.Layer;
import ru.kopylov.neuro2.model.Synapses;
import ru.kopylov.neuro2.utils.Cmp;
import ru.kopylov.neuro2.utils.Print;

import static org.junit.Assert.*;

/**
 * Created by se on 24.08.2018.
 */
public class ParallelCalcImplTest {
    @Test
    public void testCalcForward(){

        Normaliser norm = new NormalaserImpl();
        ParallelCalcImpl calc = new ParallelCalcImpl();
        calc.init();
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
        calc.destroy();
        assertTrue(Cmp.compareFloatArrays(expected, synapses.getRight().getInput(), 0.01));

    }

}