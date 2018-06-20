package ru.kopylov.neuro2.utils;

import org.junit.Assert;
import org.junit.Test;
import ru.kopylov.neuro2.model.Net;

import static org.junit.Assert.*;

/**
 * Created by se on 18.06.2018.
 */
public class SaverImplTest {
    @Test
    public void saveAndGet() throws Exception {
        String fileName = "savedNet.net";
        Net net = new Net(3, 4, 2);
        Saver saver = new SaverImpl();
        saver.save(net, fileName);
        Net net2 = saver.get(fileName);
        assertEquals(net, net2);

    }



}