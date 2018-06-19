package ru.kopylov.neuro2.utils;

import org.junit.Test;
import ru.kopylov.neuro2.model.Net;

import static org.junit.Assert.*;

/**
 * Created by se on 18.06.2018.
 */
public class SaverImplTest {
    @Test
    public void save() throws Exception {
        Net net = new Net(3, 4, 2);
        SaverImpl saver = new SaverImpl();
        saver.save(net, "savedNet.net");

    }

    @Test
    public void get() throws Exception {

    }

}