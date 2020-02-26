package com.tel_ran.handler;

public interface IOperation {

    String getName(); // name of the operation in incoming file
    /**
     * takes input string and handles it and returns a result
     * @param input string to process
     * @return result string
     */
    String operate(String input);
}
