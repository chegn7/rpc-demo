package com.c.rpc.transport;

/**
 * 1. 启动、监听端口
 * 2. 接收处理请求
 * 3. 关闭监听
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);

    void start();

    void close();
}
