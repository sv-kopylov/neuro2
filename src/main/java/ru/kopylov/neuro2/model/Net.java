package ru.kopylov.neuro2.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by se on 15.06.2018.
 */
public class Net implements Serializable {
   private Layer[] layers;
   private Synapses[] synapses;

   public Net(){}

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

    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }

    public void setSynapses(Synapses[] synapses) {
        this.synapses = synapses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Net net = (Net) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(synapses, net.synapses);
    }


}
