package ru.kopylov.neuro2.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.learning.Teacher;
import ru.kopylov.neuro2.learning.TeacherImpl;
import ru.kopylov.neuro2.logic.ErrorCounter;
import ru.kopylov.neuro2.model.Net;
import ru.kopylov.neuro2.utils.Print;
import ru.kopylov.neuro2.utils.Saver;
import ru.kopylov.neuro2.utils.SaverImpl;

import java.util.Arrays;

/**
 * Created by se on 29.06.2018.
 */
@Component
public class XoR {
    @Autowired
    Net net;
    @Autowired
    Teacher teacher;
    @Autowired
    ErrorCounter errorCounter;

    public static final int ITER = 10000 ;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        XoR app = (XoR) ctx.getBean(XoR.class);
        app.launch();

    }

    private void launch() {
        float[][] ins = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        float[][] outs = {{0.3f}, {0.8f}, {0.8f}, {0.3f}};

        float [] errors = new float[4];

        double error=0;
        int k = 0;

//        teacher.lernEpoch(net, ins, outs, 5, 10);

        for (int i = 0; i < ITER; i++) {
            System.out.println("EPOCH #" + i);
            for (int j = 0; j < 4; j++) {
                k=j;
//                System.out.println("SET #" + k);
                teacher.lern(net, ins[k], outs[k]);
//                Print.print(net);
//                System.out.println("result: "+net.getResult()[0]);
                errors[k] = outs[k][0] - net.getResult()[0];
            }
            error=errorCounter.countError(errors);
            System.out.println("Epoch error: "+ error);
//            Print.print(net);


        }

       System.out.println("Результат натренированной сети");
        for (int j = 0; j < 4; j++) {
            net.passForward(ins[j]);
            Print.print(net);
            System.out.print("вход ");
            System.out.println((int) ins[j][0] + " | " + (int) ins[j][1]);
            System.out.print("выход ");
            System.out.println(net.getResult()[0]);
            System.out.print("ошибка ");
            System.out.println(outs[j][0] - net.getResult()[0]);
            System.out.println();

        }


    }

    boolean check(float[] big, float[] small) {
        Arrays.sort(big);
        Arrays.sort(small);
        Print.print(big);
        Print.print(small);
        if(big[0]<=small[small.length-1])

//        if (big[0] <= small[0] || big[1] <= small[0] || big[0] <= small[1] || big[1] <= small[1])
            return false;
        else
            return true;
    }


}
