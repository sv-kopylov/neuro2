package ru.kopylov.neuro2.utils;

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
}
