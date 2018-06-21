package ru.kopylov.neuro2.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.logic.Calc;
import ru.kopylov.neuro2.logic.Normaliser;
import ru.kopylov.neuro2.model.Net;
import ru.kopylov.neuro2.utils.Print;

/**
 * Created by se on 21.06.2018.
 */
@Component
public class Application {
    @Autowired
    Net net;

    private void launch(){
        float[] in = {2.0f, 3.0f};
        net.input(in);
        net.calcForward();
        Print.print(net);
    }


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Application app = (Application) ctx.getBean(Application.class);
        app.launch();

    }
}
