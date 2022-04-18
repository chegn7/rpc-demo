package com.c.rpc.server;

import com.c.rpc.Request;
import com.c.rpc.ServiceDescriptor;
import com.c.rpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 注册bean
     * @param interfaceClass 接口的类
     * @param bean 实现该接口的一个单例的实例
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance serviceInstance = new ServiceInstance(bean, method);
            ServiceDescriptor descriptor = ServiceDescriptor.from(interfaceClass, method);

            services.put(descriptor, serviceInstance);
            log.info("register service : {} {}", descriptor.getClazz(), descriptor.getMethod());
        }
    }

    public ServiceInstance lookup(Request request) {
        ServiceDescriptor serviceDescriptor = request.getServiceDescriptor();
        // serviceDescriptor要重写equals方法
        return services.get(serviceDescriptor);
    }
}
