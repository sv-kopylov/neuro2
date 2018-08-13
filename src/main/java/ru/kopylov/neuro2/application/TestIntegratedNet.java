package ru.kopylov.neuro2.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.learning.Teacher;
import ru.kopylov.neuro2.learning.TeacherImpl;
import ru.kopylov.neuro2.model.Net;

/**
 *   <bean id ="net" class="ru.kopylov.neuro2.model.Net">
 *   <constructor-arg value="2"></constructor-arg>
 *   <constructor-arg value="2"></constructor-arg>
 *   <constructor-arg value="2"></constructor-arg>
 *   </bean>
 */
@Component
public class TestIntegratedNet {


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        TestIntegratedNet app = (TestIntegratedNet) ctx.getBean(TestIntegratedNet.class);
        app.launch();

    }

    private void launch() {
        float[] in = {1f, 2f};
        float[] expected = {5.5f, 6.4f};
        float[][] sy = net.getSynapses()[0].getWeigts();
        sy[0][0]= 1.1f;        sy[0][1]= 1.2f;
        sy[1][0]= 2.1f;        sy[1][1]= 2.2f;
        net.setNormaliser(null);
        Teacher teacher = new TeacherImpl();
        teacher.lern(net, in, expected);
    }

    @Autowired
    Net net;
}
