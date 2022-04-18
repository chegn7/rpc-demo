package com.c.rpc.server;

import com.c.rpc.Request;
import com.c.rpc.ServiceDescriptor;
import com.c.rpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;


public class ServiceManagerTest {
    ServiceManager manager;

    @Before
    public void init() {
        manager = new ServiceManager();
        TestInterface bean = new TestClass();
        manager.register(TestInterface.class, bean);
    }

    @Test
    public void register() {
        TestInterface bean = new TestClass();

        manager.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor descriptor = ServiceDescriptor.from(TestInterface.class, method);

        Request  request = new Request();
        request.setServiceDescriptor(descriptor);

        ServiceInstance instance = manager.lookup(request);
        assertNotNull(instance);
    }
}