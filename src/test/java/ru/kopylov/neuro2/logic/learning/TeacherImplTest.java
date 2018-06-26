package ru.kopylov.neuro2.logic.learning;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by se on 22.06.2018.
 */
public class TeacherImplTest {
    @Test
    public void getCoefs() throws Exception {
        TeacherImpl teacher = new TeacherImpl();
        float[][] sy = new float[2][2];

        sy[0][0]= 1.1f;        sy[0][1]= 1.2f;
        sy[1][0]= 2.1f;        sy[1][1]= 2.2f;

        float[] result = teacher.getDelimitters(sy);
        assertEquals(result[0], 3.2f, 0.001);
        assertEquals(result[1], 3.4f, 0.001);

    }

}