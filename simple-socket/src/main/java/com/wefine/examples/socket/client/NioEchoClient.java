package com.wefine.examples.socket.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static com.wefine.examples.socket.server.Constants.SERVER_HOST;
import static com.wefine.examples.socket.server.Constants.SERVER_PORT;

public class NioEchoClient {
    private static SocketChannel client;
    private static ByteBuffer buffer;
    private static ByteBuffer responseBuffer;
    private static NioEchoClient instance;

    public static NioEchoClient start() {
        if (instance == null)
            instance = new NioEchoClient();

        return instance;
    }

    public static void stop() throws IOException {
        client.close();
        buffer = null;
        responseBuffer = null;
    }

    private NioEchoClient() {
        try {
            client = SocketChannel.open(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
            buffer = ByteBuffer.allocate(1024);
            responseBuffer = ByteBuffer.allocate(1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg) {
        buffer = ByteBuffer.wrap(msg.getBytes());
        String response = null;
        try {
            client.write(buffer);
            buffer.clear();

            client.read(responseBuffer);
            response = new String(responseBuffer.array());
            System.out.println("response=" + response);
            buffer.clear();
            responseBuffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }
}
