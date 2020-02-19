package com.tel_ran;

import javafx.scene.control.IndexedCell;

public class Main {
    private static final int THREAD_NUMBER=100;
    private static final int ITERATION_NUMBER=100000;


    public static void main(String[] args) throws InterruptedException {
        Incrementer incrementer=new Incrementer();

        Thread[] threads=new IncrementingThread[THREAD_NUMBER];
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread th=new IncrementingThread(incrementer,ITERATION_NUMBER);
            threads[i]=th;
        }
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i].start();
        }
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i].join();
        }

        System.out.println(incrementer.getCount());


    }
}
