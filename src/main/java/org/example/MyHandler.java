package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;

public class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        URI uri = exchange.getRequestURI();

        String method = exchange.getRequestMethod();
        String s = read(is);
        String serverAnswer = "";

        if (method.equalsIgnoreCase("POST")) {
            serverAnswer = response(s);
        } else {
            serverAnswer = response(uri.toString());
        }

        if (serverAnswer.equals("not_found")) {
            exchange.sendResponseHeaders(404, 0);
            OutputStream os = exchange.getResponseBody();
            os.write("".getBytes());
            os.close();
        }
        String response = "<!doctype html>\n" +
                "<html lang=en>\n" +
                "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "}" +
                "</style>" +
                "<meta charset=utf-8>\n" +
                "<title>Hotel</title>\n" +
                "</head>\n" +
                "<body>\n" +
                serverAnswer +
                "</body>\n" +
                "</html>\n";

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String read(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println("\n");
        String received = "";
        while (true) {
            String s = "";
            try {
                if ((s = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            received += s;
        }
        return received;
    }

    public String response(String uri) {
        String[] res = uri.split("[/?&=]");
        ArrayList<String> ls = new ArrayList<>();


        if (res.length == 0) {
            return "No data";
        }

        for (String r : res) {
            if (!r.equals("")) {
                ls.add(r);
            }
        }
        System.out.println(res[0]);
        if (!ls.get(0).equals("command")) {
            return "not_found";
        }

        String s = "";
        if (ls.contains("all")) {
            s += HotelList.getInstance().hotelActions("all", s);

        } else if (ls.contains("most_expensive")) {
            s += HotelList.getInstance().hotelActions("most_expensive", s);

        } else if (ls.contains("all_sorted")) {
            s += HotelList.getInstance().hotelActions("all_sorted", s);

        } else {
            return "<h1> COMANDO ERRATO!!</h1> <p><br> COMANDI -> all, most_expensive, all_sorted </p>";
        }

        return s;
    }
}