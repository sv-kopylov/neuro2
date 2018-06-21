package ru.kopylov.neuro2.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.logic.Calc;
import ru.kopylov.neuro2.logic.Normaliser;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by se on 12.06.2018.
 */

public class Synapses implements Serializable{
    public final static double CMP_PRECISSION = 0.001;
    private Layer left, right;
    private float[][] weigts;

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

    public void calcForward(Calc calc, Normaliser normaliser){
        calc.calcForward(left.getSignals(), weigts, right.getSignals());
        if(normaliser!=null){
            float[] signals = right.getSignals();
            for(int i=0;i<signals.length;i++){
                signals[i]=normaliser.normalise(signals[i]);
            }
        }
    }

    public float[][] getWeigts() {
        return weigts;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null) return false;
        if(!(obj instanceof Synapses)) return false;
        Synapses other = (Synapses) obj;
        if(this.weigts==null&&other.weigts==null) return true;

        if(this.weigts==null||other.weigts==null) return false;
        if(this.weigts.length!=other.weigts.length) return false;
        if(this.weigts[0].length!=other.weigts[0].length) return false;
        for(int i=0; i<weigts.length;i++){
            for(int j=0;j<weigts[0].length;j++){
                if(Math.abs(this.weigts[i][j]-other.weigts[i][j])>CMP_PRECISSION) return false;
            }
        }
        return true;
    }
}
