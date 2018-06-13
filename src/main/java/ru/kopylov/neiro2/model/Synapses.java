package ru.kopylov.neiro2.model;

/**
 * Created by se on 12.06.2018.
 */
public class Synapses {
    private Layer left, right;
    private float[][] weigts;

    public Synapses(Layer left, Layer right) {
        this.left = left;
        this.right = right;
        weigts=new float[left.getLenght()][right.getLenght()];
    }
}
