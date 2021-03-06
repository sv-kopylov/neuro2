package ru.kopylov.neuro2.model;

import ru.kopylov.neuro2.logic.Calc;
import ru.kopylov.neuro2.logic.Normaliser;
import ru.kopylov.neuro2.utils.UtilCalc;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by se on 12.06.2018.
 */

public class Synapses implements Serializable{
    public final static double CMP_PRECISSION = 0.001;
    private final Layer left, right;
    private final float[][] weigts;
    private final float[][] previousWeights;

    public Synapses(Layer left, Layer right) {
        Random r = new Random(System.currentTimeMillis());
        this.left = left;
        this.right = right;
        weigts=new float[left.getLenght()][right.getLenght()];
        previousWeights=new float[left.getLenght()][right.getLenght()];
        for(float[] arr:weigts){
            for(int i=0;i<arr.length;i++){
                arr[i]=r.nextFloat();
            }
        }
    }

    public void calcDeltasHidden(){


    }

    private float[] calcMultyDeltas(float[][] weights, float[] deltas){
        if(weights[0].length!=deltas.length){
            throw new IllegalArgumentException("incporrect setInput array lenght");
        }
        float[] result = new float[weights.length];
        UtilCalc.apply2D(weights, (i, j, w)->{
            result[i]+=w[i][j]*deltas[j];
        });
        return result;
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

    public Layer getLeft() {
        return left;
    }

    public Layer getRight() {
        return right;
    }

    public float[][] getPreviousWeights() {
        return previousWeights;
    }

    public void saveAndIncrement(int i, int j, float value){
        previousWeights[i][j]=weigts[i][j];
        weigts[i][j]+=value;
    }
}
