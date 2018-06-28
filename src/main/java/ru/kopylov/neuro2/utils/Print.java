package ru.kopylov.neuro2.utils;

import ru.kopylov.neuro2.model.Layer;
import ru.kopylov.neuro2.model.Net;
import ru.kopylov.neuro2.model.Synapses;

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

    public static void print(Net net){
        Layer[] layers = net.getLayers();
        Synapses [] synapses = net.getSynapses();
        for (int k=0; k<synapses.length; k++){
            print(layers[k]);
            System.out.println();
            print(synapses[k]);
            System.out.println();
        }
        print(layers[layers.length-1]);
        System.out.println();
    }

    private static void print(Layer l) {
        float[] arr = l.getSignals();
        for (float fl : arr) {
            System.out.printf("%.1e \n", fl);
        }
    }
    private static void print(Synapses s){
        print(s.getWeigts());
    }


    public static void print(float[][] arr2d) {
        for(int i=0;i<arr2d.length;i++){
            for(int j=0;j<arr2d[0].length;j++){
                System.out.printf("%.1e  ", arr2d[i][j]);
            }
            System.out.println();
        }
    }
}
