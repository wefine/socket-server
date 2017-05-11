package com.wefine.examples.socket;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class AsynchronousSocketClient {

    private final AsynchronousSocketChannel asynchronousSocketChannel;
    private final String address;
    private final ByteBuffer byteBuffer;

    public AsynchronousSocketClient(String address, AsynchronousSocketChannel asynchronousSocketChannel) {
        this.asynchronousSocketChannel = asynchronousSocketChannel;
        this.address = address;

        byteBuffer = ByteBuffer.allocateDirect(16 * 1024);
    }

    public AsynchronousSocketChannel getAsynchronousSocketChannel() {
        return asynchronousSocketChannel;
    }

    public String getAddress() {
        return address;
    }

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }
}  