package com.temple;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {
    public static void main(String args[]) throws IOException {
        int port = 8000;

        ServerSocket sSocket = new ServerSocket(port);
        Socket socket = sSocket.accept();

        String IP = socket.getInetAddress().toString();
        Date date = new Date();
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        try {
            File fp = new File("log.txt");

            if (fp.createNewFile()) {
                System.out.println("File created: " + fp.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("log.txt");
            writer.write("Files in Java might be tricky, but it is fun enough!");
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("\r\n");
        out.println("<p> Your IP address: </p>" + IP);
        out.println("<p> Current date and time: </p>" + date.toString());
        out.flush();
        out.close();

    }
}
