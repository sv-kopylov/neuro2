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
        System.out.println("*****************");
        for(int i=0; i<net.getSynapses().length; i++){
            printSynapse(net.getSynapses()[i]);
            System.out.println("------------------");
        }
        System.out.println("*****************");
    }

//    private static String pat = "%.3e";
    private static String pat1 = "(%.3f)";
    private static String pat2 = "[%.3f]";
    private static void printSynapse (Synapses synapses){
        float[] left = synapses.getLeft().getOutput();
        float[] right = synapses.getRight().getInput();
        float[][] weights = synapses.getWeigts();
        for (int i=0; i<left.length; i++){
            System.out.format(pat1, left[i]);
            for(int j=0; j<weights[i].length; j++){
                System.out.format(pat2, weights[i][j]);
            }
            System.out.println();
        }
        System.out.print("       ");
        for(int k=0; k<right.length; k++){
            System.out.format(pat1, right[k]);
        }
        System.out.println();

    }

    private static void print(Layer l) {
        float[] arr = l.getInput();
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
                System.out.printf("%.3e  ", arr2d[i][j]);
            }
            System.out.println();
        }
    }
}
