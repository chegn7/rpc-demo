package com.c.rpc.client;

import com.c.rpc.Peer;
import com.c.rpc.transport.TransportClient;

import java.util.List;

public interface TransportSelector {
    /**
     * 初始化选择器
     * @param peers server节点列表
     * @param count client与server建立连接个数
     * @param clazz client的实现类
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport和server进行连接
     * @return
     */
    TransportClient select();

    /**
     * 释放连接客户端
     * @param client
     */
    void release(TransportClient client);

    void close();
}
