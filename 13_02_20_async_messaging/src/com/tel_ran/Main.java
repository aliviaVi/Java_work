package com.tel_ran;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        MessageProcessor messageProcessor=new MessageProcessor();
        Thread processorThread=new Thread(messageProcessor);
        processorThread.setDaemon(true);
        processorThread.start();

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line=bufferedReader.readLine())!=null && !line.equals("exit")){
            messageProcessor.setMessage(line);
            processorThread.interrupt();
        }

    }
}
