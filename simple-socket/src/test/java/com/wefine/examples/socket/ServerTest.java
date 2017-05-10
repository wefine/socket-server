package com.wefine.examples.socket;


import com.wefine.examples.socket.server.SocketServer;
import org.junit.Test;

import java.io.IOException;

public class ServerTest extends AbstractTest {

    @Test
    public void test() throws InterruptedException {
        Runnable server = () -> {
            try {
                new SocketServer("localhost", SERVER_PORT).startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread t = new Thread(server);
        t.start();

        Thread.sleep(5 * 60 * 1000);
    }
}
