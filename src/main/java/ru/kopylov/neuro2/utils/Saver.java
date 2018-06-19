package ru.kopylov.neuro2.utils;

import ru.kopylov.neuro2.model.Net;

/**
 * Created by se on 18.06.2018.
 */
public interface Saver {
    void save(Net net, String fileNeme);
    Net get(String fileNeme);


}
