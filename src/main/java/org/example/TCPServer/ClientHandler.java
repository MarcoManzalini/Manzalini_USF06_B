package org.example.TCPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {
    private static Socket clientSocket = null;
    private PrintWriter out = null; // allocate to write answer to client.

    public ClientHandler(Socket clientSocket) {
        ClientHandler.clientSocket = clientSocket;

        InetAddress address = clientSocket.getInetAddress();
        System.out.println(address);
        int port = clientSocket.getPort();
        System.out.println(port);
    }

    @Override
    public void run() {
        handle();
    }

    Boolean readLoop(BufferedReader in, PrintWriter out) {

        String s = "";
        try {
            try {
                while ((s = in.readLine()) != null) {
                    ClientManager.getInstance().reply(HotelListTCP.getInstance().hotelActions(s), this);
                }
            } catch (SocketException e) {
                InetAddress address = clientSocket.getInetAddress();
                ClientManager.getInstance().remove(this);
                System.out.println("Done on " + address + " now we have: " + ClientManager.getInstance().nOfClients());
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    void handle() {

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            readLoop(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void write(String s) {
        out.println(s);
        out.flush();
    }
}
