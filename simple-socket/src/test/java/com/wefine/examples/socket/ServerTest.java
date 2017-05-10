package com.wefine.examples.socket;


import com.wefine.examples.socket.server.SocketServer;
import org.junit.Test;

import java.io.IOException;

public class ServerTest {

    @Test
    public void test() throws InterruptedException {
        Runnable server = () -> {
            try {
                new SocketServer("localhost", 8090).startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread t = new Thread(server);
        t.start();

        Thread.sleep(2 * 60 * 1000);
    }
}
