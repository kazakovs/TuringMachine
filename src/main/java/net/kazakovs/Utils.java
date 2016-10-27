package net.kazakovs;

import java.io.*;

public class Utils {

    static InputStream readFileInputStreamFromResources(String filename) {
        ClassLoader classLoader = TuringMachine.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    static String[] parseFile(InputStream in){
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        return br.lines().toArray(size -> new String[size]);
    }

    static String[] parseFile(String filename) {
        InputStream in = null;
        try {
            in = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parseFile(in);

    }

}