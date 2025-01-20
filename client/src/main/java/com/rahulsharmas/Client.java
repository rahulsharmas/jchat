package com.rahulsharmas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.net.Socket;

public class Client {

    private static final String DEFAULT_SERVER_HOST = "localhost";
    private static final int DEFAULT_SERVER_PORT = 11111;

    public static void main(String[] args) {

        String serverAddress = DEFAULT_SERVER_HOST;
        int serverPort = DEFAULT_SERVER_PORT;

        if (args.length == 2) {
            serverAddress = args[0];
            serverPort = Integer.parseInt(args[1]);
        }

        try (Socket server = new Socket(serverAddress, serverPort);
             BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()), true)) {

            String input;
            while ((input = reader.readLine()) != null) {
                System.out.format("Server : %s%n", input);
                writer.println("Received");
            }

        } catch (Exception e) {
            System.err.format("Couldn't connect to server%nAddress : %s%nPort : %d%n", serverAddress, serverPort);
        }
    }

}
