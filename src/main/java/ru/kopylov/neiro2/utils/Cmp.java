package ru.kopylov.neiro2.utils;

/**
 * Created by se on 13.06.2018.
 */
public class Cmp {
    public static boolean compareFloatArrays(float[] one, float[] two){
        if(one.length!=two.length) return false;
        for(int i=0;i<one.length;i++){
            if(one[i]!=two[i]) return false;
        }
        return true;
    }
    public static boolean compareFloatArrays(float[] one, float[] two, double precission){
        if(one.length!=two.length) return false;
        for(int i=0;i<one.length;i++){
            if(Math.abs(one[i]-two[i])>precission) return false;
        }
        return true;
    }
}
