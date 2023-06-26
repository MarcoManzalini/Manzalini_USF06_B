package org.example.HTTPServer;

import com.sun.net.httpserver.HttpServer;
import org.example.HTTPServer.MyHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Hello world!
 *
 */
public class Server
{
    public static void main( String[] args )
    {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8001), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Tutto quello che c'è dopo questo URL lo gestisco io
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
