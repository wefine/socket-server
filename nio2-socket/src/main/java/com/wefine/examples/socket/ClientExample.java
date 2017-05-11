package com.wefine.examples.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClientExample {

    public static void main(String[] args)
            throws Exception {

        new ClientExample().go();
    }

    private void go()
            throws IOException, InterruptedException, ExecutionException {

        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 3883);
        Future<Void> future = client.connect(hostAddress);
        future.get(); // returns null

        System.out.println("Client is started: " + client.isOpen());
        System.out.println("Sending messages to server: ");

        String[] messages = new String[]{"Time goes fast.", "What now?", "Bye."};

        for (String message : messages) {

            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            Future<Integer> result = client.write(buffer);

            while (!result.isDone()) {
                System.out.println("... ");
            }

            System.out.println(message);
            buffer.clear();
            Thread.sleep(3000);
        } // for

        client.close();
    }
}