package com.tel_ran.handler;

import com.tel_ran.service.FileService;

import java.util.concurrent.BlockingDeque;


public class TextHandler implements Runnable {


    private static final String WRONG_OPERATION = "wrong operation";
    private static final String INCORRECT_LINE = "incorrect line";
    private String delimiter = "#";
    private final FileService fileService;
    private volatile static boolean isAlive = true;


    private OperationsProvider operationsProvider;
    private BlockingDeque<String> queue;


    public TextHandler(OperationsProvider operationsProvider, BlockingDeque<String> queue, FileService fileService1) {
        this.operationsProvider = operationsProvider;
        this.queue = queue;
        this.fileService = fileService1;

    }


    @Override
    public void run() {
        while (isAlive) {
            String queueLine = null;
            try {
                queueLine = queue.takeFirst();
            } catch (InterruptedException e) {
                return;
            }
            if (queueLine.equalsIgnoreCase("exit")) {
                isAlive = false;
                return;
            }
            String[] parts = queueLine.split(delimiter);
            String res;
            if (parts.length != 2) {
                res = queueLine + delimiter + INCORRECT_LINE;
            } else {
                String text = parts[0];
                String operationName = parts[1];
                IOperation operation = operationsProvider.getByName(operationName);
                if (operation == null) {
                    res = queueLine + " " + WRONG_OPERATION;
                } else {
                    res = operation.operate(text);
                }

                fileService.println(res);
            }

        }
    }
}
