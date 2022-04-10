package ru.scarlet.client;

import ru.scarlet.server.Server;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {


        try
        {
            Socket socket = new Socket("127.0.0.1", Server.SERVER_PORT); 		 //The IP address of the server and port number
            System.out.println("Connection established");	   		 //Let the user know connection has been made
            Scanner socketInput = new Scanner(socket.getInputStream()); //This is the input from the socket

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true); //Output from the socket
            Scanner in = new Scanner(System.in); 				 //Sets the keyboard as input for user
            System.out.println("Welcome to the game server!");


            String serverResp = "";
            //Close the connection to server
            while (true)
                 {
                String userInput = in.nextLine();        //Get input from the keyboard
                printWriter.println(userInput);                    //Send user input to the socket
                serverResp = socketInput.nextLine();     //Response from socket
                System.out.println(serverResp);          //Print server response to screen

                     if(serverResp.equals("Thank you for playing."))
                         break;
            }
            System.out.println("Thank you for playing.");

            //Close all connections and sockets to server
            printWriter.close();
            in.close();
            socketInput.close();
            socket.close();
        }
        catch (ConnectException e) //Will throw error if there is no connection to the server
        {
            System.err.println("Could not connect. Ensure server is running.");
            System.exit(-1);
        }
        catch (IOException e) 	   //Will throw error if there is no input
        {
            System.err.println("Couldn't get I/O for connection");
            System.exit(-1);
        }

    }
}
