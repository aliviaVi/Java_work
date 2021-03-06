package com.tel_ran.operation;

import com.tel_ran.handler.IOperation;

public class ReverseOperation implements IOperation {
    private static final String NAME="reverse";
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String operate(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
