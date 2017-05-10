package com.wefine.examples.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiClientEchoServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Start of main");

        int portNumber = Constants.ECHO_SERVER_PORT;
        ExecutorService executor = null;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            executor = Executors.newFixedThreadPool(5);
            System.out.println("Waiting for clients");

            while (true) {
                Socket socket = serverSocket.accept();
                Runnable worker = new RequestHandler(socket);
                executor.execute(worker);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
    }
}

class RequestHandler implements Runnable {
    private Socket socket;

    RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            System.out.println("Thread started with name:" + Thread.currentThread().getName());
            String userInput;

            while ((userInput = in.readLine()) != null) {
                userInput = userInput.replaceAll("[^A-Za-z0-9 ]", "");
                System.out.println("Received message from " + Thread.currentThread().getName() + " : " + userInput);
                writer.write("You entered : " + userInput);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("I/O exception: " + e);
        } catch (Exception ex) {
            System.out.println("Exceprion in Thread Run. Exception : " + ex);
        }
    }

}

