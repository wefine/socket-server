package com.wefine.examples.socket;


import com.wefine.examples.socket.client.NioEchoClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class NioEchoTest {

    private NioEchoClient client;

    @Before
    public void setup() throws IOException, InterruptedException {
        client = NioEchoClient.start();
    }

    @Test
    public void givenServerClient_whenServerEchosMessage_thenCorrect() {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");

        System.out.println(resp1);
        System.out.println(resp2);
    }

    @After
    public void teardown() throws IOException {
        NioEchoClient.stop();
    }
}