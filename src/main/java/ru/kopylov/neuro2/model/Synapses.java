package ru.kopylov.neuro2.model;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kopylov.neuro2.logic.Calc;
import ru.kopylov.neuro2.logic.Normaliser;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by se on 12.06.2018.
 */
public class Synapses implements Serializable{
    private Layer left, right;
    private float[][] weigts;
    @Autowired
    private transient Calc calc;

    @Autowired
    private transient Normaliser normaliser;

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

    public float[][] getWeigts() {
        return weigts;
    }


}
