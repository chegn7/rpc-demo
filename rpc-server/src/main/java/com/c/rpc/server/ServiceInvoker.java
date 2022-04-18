package com.c.rpc.server;

import com.c.rpc.Request;
import com.c.rpc.common.utils.ReflectionUtils;

public class ServiceInvoker {
    public Object invoke(ServiceInstance serviceInstance, Request request) {
        return ReflectionUtils.invoke(serviceInstance.getTarget(), serviceInstance.getMethod(), request.getArgs());
    }
}
