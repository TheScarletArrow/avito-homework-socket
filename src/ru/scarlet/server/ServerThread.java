package ru.scarlet.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread {
    public static int number = 250;
    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.err.println("Client connected");
        String input;
        int guess;
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                input = in.nextLine();
                final String[] s = input.split(" ");
                if(!s[0].equals("guess")){
                    out.println("Did not understand");
                }
                guess = Integer.parseInt(s[1]);


                if (guess == number) {
                    out.println("Correct");


                    if (input.equalsIgnoreCase("y")) {
                        out.println(1);
                        number = (int) (Math.random() * 10);

                    } else if (input.equalsIgnoreCase("n")) {
                        out.println("Thank you for playing.");
                        break;
                    }

                }
                 else if (guess < number) {
                    out.println("The number is too low");
                }
                else if (guess > number) {
                    out.println("The number is too high");
                }
                else if (input.equals("quit")) {
                    out.println("Thank you for playing.");
                    break;
                }

            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
        }
    }
}
