package ru.kopylov.neuro2.utils;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by se on 26.06.2018.
 */
public class UtilCalc {
    public static float[] diff(float[] arg1, float[] arg2){
        if(arg1.length!=arg2.length){
            throw new IllegalArgumentException("different arrays lenght");
        }
        float[] result = new float[arg1.length];
        for (int i=0;i< arg1.length;i++){
            result[i]=arg1[i]-arg2[i];
        }
        return result;
    }

    public static float[][] summ(float[][] arg1, float[][] arg2){
        if(arg1.length!=arg2.length||arg1[0].length!=arg2[0].length){
            throw new IllegalArgumentException("Matrix dimention not equals");
        }
        float[][] result = new float[arg1.length][arg1[0].length];
        apply2D(result, (i, j, r)->{
            result[i][j]=arg1[i][j]+arg2[i][j];
        });
        return result;
    }
    public static void apply2D(float[][] weights, ThreeConsumer<Integer, Integer, float[][]> consumer){
        for (int i=0; i<weights.length;i++){
            for(int j=0; j<weights[0].length;j++){
                consumer.accept(i, j, weights);
            }
        }
    }

    public static void apply1D(float[] arr, BiConsumer<Integer, float[]> cns){
        for(int i=0; i<arr.length; i++){
            cns.accept(i, arr);
        }
    }
}
