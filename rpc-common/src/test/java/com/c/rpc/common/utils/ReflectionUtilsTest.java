package com.c.rpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }

    @Test
    public void getPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertNotNull(methods);
        System.out.println(methods.length);
        assertEquals("c", methods[0].getName());
    }

    @Test
    public void invoke() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        Method[] methods = ReflectionUtils.getPublicMethods(t.getClass());
        Object o = ReflectionUtils.invoke(t, methods[0]);
        assertEquals("c", o);
    }
}