package com.tel_ran;


import com.tel_ran.handler.IOperation;
import com.tel_ran.handler.OperationsProvider;
import com.tel_ran.handler.TextHandler;
import com.tel_ran.operation.ToLowerCaseOp;
import com.tel_ran.operation.ToUpperCaseOp;
import com.tel_ran.producer.TextProducer;
import com.tel_ran.service.FileService;
import com.tel_ran.service.PropertiesService;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Application {

    private static final String INPUT_FILENAME = "inputFile.txt";
    private static final String OUTPUT_FILENAME = "output.txt";


    public static void main(String[] args) throws IOException, InterruptedException {

        PrintWriter pw = new PrintWriter(OUTPUT_FILENAME);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT_FILENAME));
        FileService fileService = new FileService(pw, bufferedReader);

        BlockingDeque<String> queue = new LinkedBlockingDeque<>();

        PropertiesService propertiesService=new PropertiesService("config/config.props");

        List<String> operationPath = propertiesService.getOperationPaths();
        OperationsProvider op = new OperationsProvider(operationPath);
        op.init();

        Thread[] producers = new Thread[2];
        for (int i = 0; i < producers.length; i++) {
            Thread th = new Thread(new TextProducer(fileService, queue));
            producers[i] = th;
            th.start();
        }

        Thread[] handlers = new Thread[5];
        for (int i = 0; i < handlers.length; i++) {
            Thread th = new Thread(new TextHandler(op, queue, fileService));
            handlers[i] = th;
            th.start();
        }
        for (Thread th : handlers) {
            th.join();
        }
        pw.close();
        bufferedReader.close();
    }

   /* public static List<String> getPath() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("config/config.props"));
        String pack = properties.getProperty("operations_package");
        String operations_string = properties.getProperty("operations");
        String[] operations = operations_string.split(",");
        List<String> operationPath = new ArrayList<>();
        for (String operation : operations) {
            operationPath.add(pack + "." + operation);
        }
        return operationPath;
    }*/
}
