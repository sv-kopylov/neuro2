package ru.kopylov.neuro2.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.logic.learning.Teacher;
import ru.kopylov.neuro2.logic.learning.TeacherImpl;
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
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        XoR app = (XoR) ctx.getBean(XoR.class);
        app.launch();

    }

    private void launch() {
        float[][] ins = {{0.01f, 0.01f}, {0.01f, 1}, {1, 0.01f}, {1, 1}};
        float[][] outs = {{1}, {0.01f}, {0.01f}, {1}};

//        Print.print(net);
        Teacher teacher = new TeacherImpl();
        teacher.setLearningSpeed(0.25);
        float trues[] = new float[2];
        float falses[] = new float[2];

        for (int i = 0; i < 100; i++) {
            System.out.println("Set #" + i);
            for (int j = 0; j < 4; j++) {
                teacher.lern(net, ins[j], outs[j]);

            }
            trues[0] = net.calcForward(ins[1])[0];
            trues[1] = net.calcForward(ins[2])[0];
            falses[0] = net.calcForward(ins[0])[0];
            falses[0] = net.calcForward(ins[3])[0];

            if(check(trues, falses)){
                System.out.println("donne" +i);
                Saver saver = new SaverImpl();
                saver.save(net, "xor.net");
                break;
            }
        }

        System.out.println("Результат натренированной сети");
        for (int j = 0; j < 4; j++) {
            net.calcForward(ins[j]);
            System.out.print("вход ");
            System.out.println((int) ins[j][0] + " | " + (int) ins[j][1]);
            System.out.print("выход ");
            System.out.println(net.output()[0]);
            System.out.print("ошибка ");
            System.out.println(outs[j][0] - net.output()[0]);
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


    @Autowired
    Net net;
}
