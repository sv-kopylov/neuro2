package ru.kopylov.neuro2.logic;

import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.utils.UtilCalc;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Created by se on 08.08.2018.
 */
@Component
public class MSEErrorCounter implements ErrorCounter {
    @Override
    public double countError(float[] errors) {
       return IntStream.range(0,errors.length).mapToDouble(i->errors[i]*errors[i]/errors.length).sum();
    }

    @Override
    public double countError(float[] actual, float[] expected) {
        if(actual.length!=expected.length) throw new IllegalArgumentException("Arrays dimentions not equals");
        return IntStream.range(0,actual.length)
                .mapToDouble(i->(actual[i]-expected[i])*(actual[i]-expected[i])/actual.length).sum();
    }
}
