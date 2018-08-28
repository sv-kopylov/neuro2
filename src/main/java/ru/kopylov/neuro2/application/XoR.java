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

    public static final int ITER = 10000;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        XoR app = (XoR) ctx.getBean(XoR.class);
        app.launch();

    }

    private void launch() {
        long time = System.currentTimeMillis();
        float[][] ins = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        float[][] outs = {{0.3f}, {0.8f}, {0.8f}, {0.3f}};

        float [] errors = new float[4];

        double error=1;
        double previousError;
        int k = 0;
        int cnt=0;
        int treshold=100;

        for (int i = 0; i < ITER; i++) {
           System.out.println("EPOCH #" + i);
            for (int j = 0; j < 4; j++) {
                k=j;
                teacher.lern(net, ins[k], outs[k]);
                errors[k] = outs[k][0] - net.getResult()[0];
            }

            previousError=error;
            error=errorCounter.countError(errors);
//            if(error>previousError){
//                cnt++;
//                System.out.println("ERROR UP");
//                if(cnt>=treshold){
//                    System.out.println("finish");
//                    break;
//                }
//
//            } else {
//                cnt=0;
//            }
            System.out.println("Epoch error: "+ error);
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
        System.out.println("время выполнения: "+(System.currentTimeMillis()-time));


    }



}
