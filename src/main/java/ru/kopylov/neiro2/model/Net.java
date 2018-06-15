package ru.kopylov.neiro2.model;

/**
 * Created by se on 15.06.2018.
 */
public class Net {
   private Layer[] layers;
   private Synapses[] synapses;

    public Net(int layersNum, int neuronNum, int neuronsInLastLayer){
        layers = new Layer[layersNum];
        for(int i=0; i<layers.length-1;i++){
            layers[i]=new Layer(neuronNum);
        }
        layers[layers.length-1]=new Layer(neuronsInLastLayer);

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
}
