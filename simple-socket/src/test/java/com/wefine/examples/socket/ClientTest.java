package com.wefine.examples.socket;


import com.wefine.examples.socket.client.SocketChannelClient;
import org.junit.Test;

import java.io.IOException;

public class ClientTest {

    @Test
    public void testClient() throws InterruptedException {
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
}
