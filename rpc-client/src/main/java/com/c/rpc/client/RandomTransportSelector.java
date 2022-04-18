package com.c.rpc.client;

import com.c.rpc.Peer;
import com.c.rpc.common.utils.ReflectionUtils;
import com.c.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RandomTransportSelector implements TransportSelector {
    // 连接好的client
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers,
                     int count,
                     Class<? extends TransportClient> clazz) {
        count = Math.max(count,1);

        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server : {}" , peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int idx = (int) (Math.random() * (clients.size()));
        return clients.remove(idx);
    }

    /**
     * 把client加入clients
     * @param client
     */
    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client : clients) {
            client.close();
        }
        clients.clear();
    }
}
