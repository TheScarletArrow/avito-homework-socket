package ru.scarlet.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 50001;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

            boolean isOk = true;
            while (isOk){
                new ServerThread(serverSocket.accept()).start();
            }


            serverSocket.close();
            System.out.println("connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
