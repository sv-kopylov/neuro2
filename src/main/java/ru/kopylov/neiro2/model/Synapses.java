package ru.kopylov.neiro2.model;

import ru.kopylov.neiro2.calc.Calc;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by se on 12.06.2018.
 */
public class Synapses {
    private Layer left, right;
    private float[][] weigts;
    private Calc calc;

    public Synapses(Layer left, Layer right) {
        Random r = new Random(System.currentTimeMillis());
        this.left = left;
        this.right = right;
        weigts=new float[left.getLenght()][right.getLenght()];
        for(float[] arr:weigts){
            for(int i=0;i<arr.length;i++){
                arr[i]=r.nextFloat();
            }
        }
    }

    public void setCalc(Calc calc) {
        this.calc = calc;
    }

    public void calcForward(){
        calc.calcForward(left.getSignals(), weigts, right.getSignals());
    }


}
