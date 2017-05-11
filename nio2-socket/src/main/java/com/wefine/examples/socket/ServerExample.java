package com.wefine.examples.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ServerExample {

    public static void main(String[] args)
            throws Exception {

        new ServerExample().go();
    }

    private void go()
            throws IOException, InterruptedException, ExecutionException {

        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("0.0.0.0", 3883);
        serverChannel.bind(hostAddress);

        System.out.println("Server channel bound to port: " + hostAddress.getPort());
        System.out.println("Waiting for client to connect... ");

        Future<AsynchronousSocketChannel> acceptResult = serverChannel.accept();
        AsynchronousSocketChannel channel = acceptResult.get();

        System.out.println("Messages from client: ");

        if ((channel != null) && (channel.isOpen())) {

            while (true) {

                ByteBuffer buffer = ByteBuffer.allocate(32);
                Future<Integer> result = channel.read(buffer);

                while (!result.isDone()) {
                    // do nothing
                    System.out.println("! result.isDone()");
                }

                buffer.flip();
                String message = new String(buffer.array()).trim();
                System.out.println(message);

                if (message.equals("Bye.")) {

                    break; // while loop
                }

                buffer.clear();

            } // while()

            channel.close();

        } // end-if

        serverChannel.close();
    }
}
