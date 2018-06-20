package ru.kopylov.neuro2.utils;

import org.junit.Test;
import ru.kopylov.neuro2.model.Net;

import static org.junit.Assert.*;

/**
 * Created by se on 20.06.2018.
 */
public class PrintTest {

    @Test
        public void test() {
        Net net = new Net(3, 3, 2);
        Print.print(net);
    }

}