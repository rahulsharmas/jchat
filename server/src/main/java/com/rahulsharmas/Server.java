package com.rahulsharmas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.ServerSocket;

public class Server {

    private static final int DEFAULT_SERVER_PORT = 11111;

    public static void main(String[] args) {

        int serverPort = DEFAULT_SERVER_PORT;

        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        try (ServerSocket server = new ServerSocket(serverPort);
             Socket client = server.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true)) {

            System.out.format("Client Connected%nAddress : %s%nPort : %d%n%n", client.getInetAddress(), client.getPort());
            writer.println("Connected");

            String input;
            while ((input = reader.readLine()) != null) {
                System.out.format("Client : %s%n", input);
            }

        } catch (Exception e) {
            System.err.format("Error Occurred : %s", e);
        }
    }

}
