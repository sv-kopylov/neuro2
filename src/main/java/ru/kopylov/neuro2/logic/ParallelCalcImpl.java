package ru.kopylov.neuro2.logic;

import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.model.Synapses;
import ru.kopylov.neuro2.utils.UtilCalc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by se on 23.08.2018.
 */
@Component("parallelCalcImpl")
public class ParallelCalcImpl extends CalcImpl {
    private  ExecutorService service;
    private  int numCPU;


    public void init(){
        this.numCPU = Runtime.getRuntime().availableProcessors();
        service = Executors.newFixedThreadPool(numCPU);
    }
    public void destroy(){
        service.shutdown();
    }


    @Override
    public void passForward(Synapses synapses, Normaliser normaliser) {
        for(int j=0; j<synapses.getRight().getOutput().length;j++){
           service.execute(new ColCount(synapses, j));
        }
        try {
            long tm = System.currentTimeMillis();
            service.awaitTermination(1, TimeUnit.SECONDS);
            System.out.println("AHA "+(System.currentTimeMillis()-tm));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synapses.getRight().normalise(normaliser);
    }

    @Override
    public void calcDeltasOut(float[] expected, Synapses synapses, Normaliser normaliser) {

    }

    @Override
    public void calcDeltasHidden(Synapses synapses, Normaliser normaliser) {

    }

    @Override
    public float[] multiplyReverse(float[][] weights, float[] deltas) {
        return new float[0];
    }

    @Override
    public void updateWeights(Synapses synapses, float epsilon, float alpha) {

    }

       private class ColCount implements Runnable{
        private final Synapses synapses;
        private final int columnIndex;

        public ColCount(Synapses synapses, int columnIndex) {
            this.synapses = synapses;
            this.columnIndex = columnIndex;
        }

        @Override
        public void run() {
            synapses.getRight().getInput()[columnIndex] =0;

            for(int i=0; i<synapses.getLeft().getOutput().length; i++){
                synapses.getRight().getInput()[columnIndex]+=synapses.getWeigts()[i][columnIndex]*synapses.getLeft().getOutput()[i];
            }

        }
    }
}
