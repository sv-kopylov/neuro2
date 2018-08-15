package ru.kopylov.neuro2.logic;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.model.Synapses;
import ru.kopylov.neuro2.utils.UtilCalc;

/**
 * Created by se on 12.06.2018.
 */
@Component
public class CalcImpl implements Calc {

    private static Logger logger = Logger.getLogger(CalcImpl.class);

    @Override
    public void passForward(Synapses synapses, Normaliser normaliser) {
        UtilCalc.apply1D(synapses.getRight().getInput(), (i, inputs)->inputs[i]=0);
        UtilCalc.apply2D(synapses.getWeigts(), (i, j, w)->synapses.getRight().getInput()[j]+=w[i][j]*synapses.getLeft().getOutput()[i]);
        synapses.getRight().normalise(normaliser);
    }
    @Override
    public void calcDeltasOut(float[] expected, Synapses synapses, Normaliser normaliser){
        if(expected.length!=synapses.getRight().getOutput().length||normaliser==null){
            throw new IllegalArgumentException("Incorrect setInput in calc deltas");
        }
        UtilCalc.apply1D(expected, (i, exptd)->{
            synapses.getRight().getDeltas()[i]=(exptd[i]-synapses.getRight().getOutput()[i])*normaliser.derivite(synapses.getRight().getOutput()[i]);
        });
    }
    @Override
    public void calcDeltasHidden(Synapses synapses, Normaliser normaliser){
        float[] sumWeightMultyDeltas = multiplyReverse(synapses.getWeigts(), synapses.getRight().getDeltas());
        UtilCalc.apply1D(synapses.getLeft().getDeltas(), (i, dts)->{
            dts[i]=sumWeightMultyDeltas[i]*normaliser.derivite(synapses.getLeft().getOutput()[i]);
        });
    }
    @Override
    public float[] multiplyReverse(float[][] weights, float[] deltas){
        if(weights[0].length!=deltas.length){
            throw new IllegalArgumentException("incporrect setInput array lenght");
        }
        float[] result = new float[weights.length];
        UtilCalc.apply2D(weights, (i, j, w)->{
            result[i]+=w[i][j]*deltas[j];
        });
        return result;
    }
    @Override
    public void updateWeights(Synapses synapses, float epsilon, float alpha) {
        UtilCalc.apply2D(synapses.getWeigts(),(i, j, w)->{
            synapses.saveAndIncrement(i, j,
                    epsilon*gradAB(i, j, synapses)+alpha*synapses.getPreviousWeights()[i][j]
                    );
        } );

    }
    private float gradAB(int a, int b, Synapses synapses){
        return synapses.getLeft().getOutput()[a]*synapses.getRight().getDeltas()[b];
    }


}
