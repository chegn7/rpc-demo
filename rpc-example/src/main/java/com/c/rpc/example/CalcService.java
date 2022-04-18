package com.c.rpc.example;

public interface CalcService {
    default int add(int a, int b) {
        return a + b;
    }

    default int minus(int a, int b) {
        return a - b;
    }

}
