package com.tel_ran;

public class Incrementer {
    private int count=0;

    public  void  increment() {
        synchronized(this){
            count++;
        }

    }
    public void decrement(){
        synchronized(this){
            count--;
        }
    }

    public int getCount() {
        return count;
    }
}
