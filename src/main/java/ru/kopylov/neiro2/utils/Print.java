package ru.kopylov.neiro2.utils;

/**
 * Created by se on 13.06.2018.
 */
public class Print {
    public static void print(float[] arr){
        System.out.print("[");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
            if(i<arr.length-1){
                System.out.print(", ");
            }
        }
        System.out.println("]");

    }
}
