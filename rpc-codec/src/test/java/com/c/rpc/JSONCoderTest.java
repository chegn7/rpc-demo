package com.c.rpc;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONCoderTest {

    @Test
    public void code() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean("zhangsan", 12);
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
        Decoder decoder = new JSONDecoder();
        TestBean decode = decoder.decode(bytes, TestBean.class);
        assertNotNull(decode);
        System.out.println(decode.toString());
    }
}