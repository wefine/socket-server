package com.wefine.examples.socket.reactor;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        String sentence;

        Socket clientSocket = new Socket("localhost", 7070);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        outToServer.writeBytes("Hello ..." + '\n');
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = inFromServer.readLine();
        System.out.println("Response from Server : " + sentence);
        clientSocket.close();
    }

    private String getUserInput() throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
//      String modifiedSentence;
//      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        return inFromUser.readLine();
    }
}
