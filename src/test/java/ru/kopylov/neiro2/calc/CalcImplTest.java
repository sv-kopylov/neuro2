package ru.kopylov.neiro2.calc;

import org.junit.Test;
import ru.kopylov.neiro2.utils.Cmp;
import ru.kopylov.neiro2.utils.Print;

import static org.junit.Assert.*;

/**
 * Created by se on 13.06.2018.
 */
public class CalcImplTest {
    @Test
    public void testCalcForward(){
        float [] input = {1f, 2f};
        float [][] weights ={{1.1f, 1.2f}, {2.1f, 2.2f}};

        float [] expected = {5.3f, 5.6f};
        float [] result=new float[2];

        Calc c = new CalcImpl();
        c.calcForward(input, weights, result);
        Print.print(expected);
        Print.print(result);

        assertTrue(Cmp.compareFloatArrays(input, result));
    }

}