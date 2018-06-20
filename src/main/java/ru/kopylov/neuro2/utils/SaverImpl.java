package ru.kopylov.neuro2.utils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.kopylov.neuro2.model.Net;

import java.io.*;

/**
 * Created by se on 18.06.2018.
 */
@Component
public class SaverImpl implements Saver {
    public static String SYMBOLS_DELIM = " ";
    public static String LINES_DELIM = "\n";


    private static Logger logger = Logger.getLogger(SaverImpl.class);
    @Override
    public void save(Net net, String fileNeme) {
        try(ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileNeme)))) {
            ous.writeObject(net);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Net get(String fileNeme) {
        Net net = null;
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileNeme)))){
            net = (Net) ois.readObject();

        } catch (IOException |ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return net;
    }
}
