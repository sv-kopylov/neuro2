package ru.kopylov.neuro2.logic.learning;

import ru.kopylov.neuro2.model.Net;

/**
 * Created by se on 22.06.2018.
 */
public interface Teacher {

    void lern(Net net, float[] in, float[] out);
}
