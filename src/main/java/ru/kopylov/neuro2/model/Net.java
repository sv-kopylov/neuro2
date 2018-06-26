package ru.kopylov.neuro2.model;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kopylov.neuro2.logic.Calc;
import ru.kopylov.neuro2.logic.Normaliser;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by se on 15.06.2018.
 */
public class Net implements Serializable {
   private Layer[] layers;
   private Synapses[] synapses;

    public void input(float [] in) {
        if(in.length!=layers[0].getLenght()){
            throw new IllegalArgumentException("input array lenght inkorrect, actual: "+in.length+" required: "+layers[0].getLenght());
        }
        layers[0].setSignals(in);
    }

    public float[] output(){
        return layers[layers.length-1].getSignals();
    }

    public float[] calcForward(){
        for(Synapses s: synapses){
            s.calcForward(calc, norm);
        }
        return output();
    }
    public float[]calcForward(float [] in){
        input(in);
        return calcForward();
    }

    public Net(int layersNum, int neuronNum, int neuronsInLastLayer){
        layers = new Layer[layersNum];
        for(int i=0; i<layers.length-1;i++){
            layers[i]=new Layer(neuronNum);
        }
        layers[layers.length-1]=new Layer(neuronsInLastLayer);

        synapses=new Synapses[layers.length-1];

        for(int i=0;i<layers.length-1;i++){
            synapses[i]=new Synapses(layers[i],layers[i+1]);

        }



    }

    public Layer[] getLayers() {
        return layers;
    }

    public Synapses[] getSynapses() {
        return synapses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Net net = (Net) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(synapses, net.synapses);
    }

    @Autowired
    private Normaliser norm;

    @Autowired
    private Calc calc;

    public void setNorm(Normaliser norm) {
        this.norm = norm;
    }
}
