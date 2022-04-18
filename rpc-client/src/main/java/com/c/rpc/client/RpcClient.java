package com.c.rpc.client;

import com.c.rpc.Decoder;
import com.c.rpc.Encoder;
import com.c.rpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        selector = ReflectionUtils.newInstance(config.getSelectorClass());
        selector.init(config.getServers(),
                config.getConnectCount(),
                config.getTransportClass());
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, selector));
    }

}
