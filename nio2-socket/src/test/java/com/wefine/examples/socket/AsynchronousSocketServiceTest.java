package com.wefine.examples.socket;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class AsynchronousSocketServiceTest {

    public static void main(String[] args) throws Exception {
        InetSocketAddress address = new InetSocketAddress("localhost", 18081);
        SocketChannel socketChannel = SocketChannel.open(address);


        ArrayList<String> companyDetails = new ArrayList<>();

        // create a ArrayList with companyName list
        companyDetails.add("Facebook");
        companyDetails.add("Twitter");
        companyDetails.add("IBM");
        companyDetails.add("Google");
        companyDetails.add("Over");

        for (String name : companyDetails) {
            byte[] message = name.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            socketChannel.write(buffer);
            log("sending: " + name);
            buffer.clear();

            ByteBuffer responseBuffer = ByteBuffer.allocate(64);
            socketChannel.read(responseBuffer);
            String response = new String(responseBuffer.array());
            System.out.println("response:" + response);
            responseBuffer.clear();

            Thread.sleep(2000);
        }
        socketChannel.close();
    }

    private static void log(String str) {
        System.out.println(str);
    }

}
