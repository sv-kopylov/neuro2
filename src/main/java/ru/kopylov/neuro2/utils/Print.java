package ru.kopylov.neuro2.utils;

import ru.kopylov.neuro2.model.Net;

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
