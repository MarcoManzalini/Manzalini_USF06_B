package org.example.TCPServer;

import java.util.ArrayList;
import java.util.List;

public final class ClientManager {

    private static ClientManager INSTANCE;
    static List<ClientHandler> clientList = new ArrayList<>();

    private ClientManager() {
    }

    public static ClientManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientManager();
        }

        return INSTANCE;
    }

    void add(ClientHandler clientHandler){
        clientList.add(clientHandler);
    }

    void remove(ClientHandler clientHandler){
        clientList.remove(clientHandler);
    }

    int nOfClients(){
        return clientList.size();
    }

    void reply(String s, ClientHandler sourceClient){
        sourceClient.write(s);
    }

}

