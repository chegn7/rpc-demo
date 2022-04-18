package com.c.rpc.server;

import com.c.rpc.Decoder;
import com.c.rpc.Encoder;
import com.c.rpc.JSONDecoder;
import com.c.rpc.JSONEncoder;
import com.c.rpc.transport.HTTPTransportServer;
import com.c.rpc.transport.TransportServer;
import lombok.Data;

@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3031;
}
