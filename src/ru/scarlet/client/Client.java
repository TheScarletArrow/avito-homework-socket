package ru.scarlet.client;

import ru.scarlet.server.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {


        try {
            Socket socket = new Socket("127.0.0.1", Server.SERVER_PORT);
            System.out.println("Connection established");
            Scanner socketInput = new Scanner(socket.getInputStream()); //input from the socket

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true); //Output from the socket
            Scanner in = new Scanner(System.in);
            System.out.println("Welcome to the game server!");


            String serverResp = "";
            do {
                String userInput = in.nextLine();
                printWriter.println(userInput);
                serverResp = socketInput.nextLine();
                System.out.println(serverResp);

            } while (!serverResp.equals("Thank you for playing."));


            printWriter.close();
            in.close();
            socketInput.close();
            socket.close();
        } catch (ConnectException e)
        {
            System.err.println("Could not connect. Ensure server is running.");
            System.exit(-1);
        } catch (IOException e)
        {
            System.err.println("Couldn't get I/O for connection");
            System.exit(-1);
        }

    }
}
