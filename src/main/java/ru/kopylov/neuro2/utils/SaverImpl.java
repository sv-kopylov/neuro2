package ru.kopylov.neuro2.utils;

import org.apache.log4j.Logger;
import ru.kopylov.neuro2.model.Layer;
import ru.kopylov.neuro2.model.Net;
import ru.kopylov.neuro2.model.Synapses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by se on 18.06.2018.
 */
public class SaverImpl implements Saver {
    public static String SYMBOLS_DELIM = " ";
    public static String LINES_DELIM = "\n";


    private static Logger logger = Logger.getLogger(SaverImpl.class);
    @Override
    public void save(Net net, String fileNeme) {
        File file = new File(fileNeme);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileNeme))) {
            for(Synapses s: net.getSynapses()){
                writeSynapses(bw, s);
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    private void writeSynapses(Writer fw, Synapses synapses) throws IOException {
        for (int i=0;i<synapses.getWeigts().length; i++){
            for(int j=0;j<synapses.getWeigts()[0].length;j++){
                fw.write(Float.toString(synapses.getWeigts()[i][j]));
                fw.write(SYMBOLS_DELIM);
            }
            fw.write(LINES_DELIM);
        }
        fw.write(LINES_DELIM);

    }

    @Override
    public Net get(String fileNeme) {
        Net net = new Net();
        List<Synapses> synapsesList = new ArrayList<>();
        List<Float> synapseRow = new ArrayList<>();
        Synapses synapses;

        Path path = Paths.get(fileNeme);
        try {
            List<String[]> listOfArrays = Files.lines(path).
                    map(s-> s.split(SYMBOLS_DELIM)).
                    collect(toList());
            for(int i=0; i<listOfArrays.size();i++){
                if(listOfArrays.get(i).length>0){
                    for(int j=0; j<listOfArrays.get(i).length; j++){
                        synapseRow.add(Float.parseFloat(listOfArrays.get(i)[j]));
                    }

                } else {

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
