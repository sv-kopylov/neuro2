package ru.kopylov.neuro2.logic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by se on 20.06.2018.
 */
public class NormalaserImplTest {

    @Test
    public void normaliserVisualTest(){
        float arg = -5f;
        Normaliser norm = new NormalaserImpl();
        float r;
        while (arg<20){
            r=norm.normalise(arg);
            System.out.format("%.1e  %-9.1e \n",arg, norm.normalise(arg));
            arg+=0.25;
            assertTrue(r>=0&&r<=1);
        }
    }

}