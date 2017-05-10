package com.wefine.examples.socket;


import com.wefine.examples.socket.client.SocketChannelClient;
import com.wefine.examples.socket.client.SocketClient;
import org.junit.Test;

import java.io.IOException;

public class ClientTest extends AbstractTest {

    @Test
    public void testSocketChannelClient() throws InterruptedException {
        Runnable client = () -> {
            try {
                new SocketChannelClient().startClient();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(client, "client-A").start();
        new Thread(client, "client-B").start();

        Thread.sleep(2 * 60 * 1000);
    }

    @Test
    public void testSocketClient() throws IOException {
        SocketClient client = new SocketClient();

        client.start("127.0.0.1", SERVER_PORT);
    }
}
