package com.wefine.examples.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

    public void start(String ip, int port) throws IOException {
        Socket server = new Socket(ip, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = wt.readLine();
            out.println(str);
            out.flush();
            if (str.equals("end")) {
                break;
            }
            System.out.println(in.readLine());
        }
        server.close();
    }

    public static void main(String[] args) throws IOException {
        new SocketClient().start("localhost", 8090);
    }
}
