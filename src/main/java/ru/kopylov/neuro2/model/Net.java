package ru.kopylov.neuro2.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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


    public Net(int...lauersConfig){
        layers = new Layer[lauersConfig.length];
        for(int i=0; i<layers.length;i++){
            layers[i]=new Layer(lauersConfig[i]);
        }
        synapses=new Synapses[lauersConfig.length-1];
        for(int i=0;i<layers.length-1;i++){
            synapses[i]=new Synapses(layers[i],layers[i+1]);

        }
    }

    public void setInput(float [] in) {
        if(in.length!=layers[0].getLenght()){
            throw new IllegalArgumentException("setInput array lenght inkorrect, actual: "+in.length+" required: "+layers[0].getLenght());
        }
        layers[0].setInput(in);
    }

    public float[] getResult(){
        return layers[layers.length-1].getOutput();
    }

    public float[] passForward(){
        for(Synapses s: synapses){
            calc.passForward(s, normaliser);
        }
        return getResult();
    }

    public float[] passForward(float [] in){
        setInput(in);
        return passForward();
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


    private Calc calc;
    @Autowired
    @Qualifier("parallelCalcImpl")
    public void setCalc(Calc calc) {
        this.calc = calc;
    }

    private Normaliser normaliser;
    @Autowired
    public void setNormaliser(Normaliser normaliser) {
        this.normaliser = normaliser;
    }
}
