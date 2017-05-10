package com.wefine.examples.socket.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

import static com.wefine.examples.socket.server.Constants.SERVER_HOST;
import static com.wefine.examples.socket.server.Constants.SERVER_PORT;


public class NIOClient {
 
	public static void main(String[] args) throws IOException, InterruptedException {
 
		InetSocketAddress address = new InetSocketAddress(SERVER_HOST, SERVER_PORT);
        SocketChannel socketChannel = SocketChannel.open(address);
 
		log("Connecting to Server on port ..." + SERVER_PORT);
 
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
 
			// wait for 2 seconds before sending next message
			Thread.sleep(2000);
		}
		socketChannel.close();
	}
 
	private static void log(String str) {
		System.out.println(str);
	}
}