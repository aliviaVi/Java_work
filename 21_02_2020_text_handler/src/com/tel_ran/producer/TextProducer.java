package com.tel_ran.producer;

import com.tel_ran.service.FileService;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;


public class TextProducer  implements Runnable{

    private FileService fs;
    private BlockingDeque<String> queue;
    private volatile static boolean isAlive = true;

    public TextProducer(FileService fileService, BlockingDeque<String> queue) {
        this.fs=fileService;
        this.queue=queue;

    }

    @Override
    public void run() {

        try{
            String line;
            while ((line=fs.readLine())!=null){
                queue.add(line);
            }
            queue.add("exit");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
