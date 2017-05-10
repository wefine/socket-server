package com.wefine.examples.socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static com.wefine.examples.socket.server.Constants.SERVER_HOST;
import static com.wefine.examples.socket.server.Constants.SERVER_PORT;


public class SelectorNIOServer {

    public static void main(String[] args) throws IOException {

		Selector selector = Selector.open(); // selector is open here
 
		// selectable channel for stream-oriented listening sockets
		ServerSocketChannel socketChannel = ServerSocketChannel.open();
		InetSocketAddress address = new InetSocketAddress(SERVER_HOST, SERVER_PORT);
 
		int ops = socketChannel.validOps();
		socketChannel.bind(address);
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, ops, null);

		// Infinite loop..
		// Keep server running
		while (true) {
 
			log("Waiting for new connection and buffer select...");
			// Selects a set of keys whose corresponding channels are ready for I/O operations
			selector.select();
 
			// token representing the registration of a SelectableChannel with a Selector
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = keys.iterator();
 
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
 
				// Tests whether this key's channel is ready to accept a new socket connection
				if (key.isAcceptable()) {
					SocketChannel client = socketChannel.accept();
 
					// Adjusts this channel's blocking mode to false
					client.configureBlocking(false);
 
					// Operation-set bit for read operations
					client.register(selector, SelectionKey.OP_READ);
					log("Connection Accepted: " + client.getLocalAddress() + "\n");
 
					// Tests whether this key's channel is ready for reading
				} else if (key.isReadable()) {
					
					SocketChannel client = (SocketChannel) key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(256);
					client.read(buffer);
					String result = new String(buffer.array()).trim();
 
					log("Message received: " + result + " from " + client.getRemoteAddress());
 
					if (result.equals("Over")) {
						client.close();
						log("\nServer is still running. You can try to establish new connection");
					}
				}
				keyIterator.remove();
			}
		}
	}
 
	private static void log(String str) {
		System.out.println(str);
	}
}