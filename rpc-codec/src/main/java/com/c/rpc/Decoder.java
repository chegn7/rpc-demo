package com.c.rpc;

public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
