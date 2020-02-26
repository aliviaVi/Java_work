package com.tel_ran.service;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.BlockingDeque;

public class FileService {

    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public FileService(PrintWriter printWriter, BufferedReader bufferedReader) {
        this.printWriter = printWriter;
        this.bufferedReader = bufferedReader;
    }

    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }


    public void println(String res) {
        printWriter.println(res);
    }

    public void readFileToQueue(String inputFilename, Collection<String> collection) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                collection.add(line);
            }

        }
    }
}
