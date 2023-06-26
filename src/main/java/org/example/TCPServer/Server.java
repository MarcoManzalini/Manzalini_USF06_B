package org.example.TCPServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server
{
    static final int portNumber = 1234;
    public static void main(String[] args) {
        System.out.println("Server started!");

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket clientSocket = null;
        while (true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            ClientManager.getInstance().add(clientHandler);

            Thread newThred = new Thread(clientHandler);
            newThred.start();
        }
    }
}
