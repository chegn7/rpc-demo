package com.c.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 表示服务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] args;

    /**
     * 传入一个类和方法，返回这个类方法的描述
     *
     * @param clazz
     * @param method
     * @return
     */
    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor descriptor = new ServiceDescriptor();
        descriptor.setClazz(clazz.getName());
        descriptor.setMethod(method.getName());
        descriptor.setReturnType(method.getReturnType().getName());

        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] argsTypes = new String[parameterTypes.length];
        for (int i = 0; i < argsTypes.length; i++) {
            argsTypes[i] = parameterTypes[i].getName();
        }
        descriptor.setArgs(argsTypes);
        return descriptor;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        ServiceDescriptor that = (ServiceDescriptor) obj;
        return this.toString().equals(that.toString());

    }

    @Override
    public String toString() {
        return "clazz=" + clazz
                + "method=" + method
                + "returnType=" + returnType
                + "args=" + Arrays.toString(args);
    }
}
