package ru.kopylov.neuro2.logic;

import org.springframework.stereotype.Component;

/**
 * Created by se on 15.06.2018.
 */
@Component
public class NormalaserImpl implements Normaliser{
    @Override
    public float normalise(float arg) {

        return (float) (1/
                        (1+Math.pow(Math.E, -arg)));
    }

    @Override
    public float derivite(float arg) {
        return (1-arg)*arg;
    }
}
