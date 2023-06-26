package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HotelList {
    private static HotelList INSTANCE;

    public HotelList(){
    }

    public static HotelList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HotelList();
        }

        return INSTANCE;
    }

    private String toJSON(String s) {
        s += "<table>\n" +
                "<tr>\n" + "<th>" + "ID" + "</th>" +
                "<th>" + "Nome" + "</th>" +
                "<th>" + "Descrizione" + "</th>" +
                "<th>" + "Prezzo" + "</th>" +
                "<th>" + "Suite" + "</th>" +
                "</tr>\n";
        for (Hotel hotel : HotelManager.getInstance().getHotelList()) {
            s +=
                    "<tr><td>" + hotel.getId() + "</td> <td>"
                            + hotel.getName() + "</td> <td>"
                            + hotel.getDescription() + "</td> <td>"
                            + hotel.getPrice() + "</td> <td>"
                            + hotel.isSuite() + "</td> </tr>";

        }
        s += "</table>";
        return s;
    }

    private String moreExpensive(String s) {
        Hotel maxHotel = null;
        double maxPrice = 0;
        s += "<table>\n" +
                "<tr>\n" + "<th>" + "ID" + "</th>" +
                "<th>" + "Nome" + "</th>" +
                "<th>" + "Descrizione" + "</th>" +
                "<th>" + "Prezzo" + "</th>" +
                "<th>" + "Suite" + "</th>" +
                "</tr>\n";

        for (Hotel hotel : HotelManager.getInstance().getHotelList()) {
            if (hotel.getPrice() > maxPrice && hotel.isSuite()) {
                maxPrice = hotel.getPrice();
                maxHotel = hotel;
            }
        }
        if (maxHotel != null) {
            s += "<tr><td>" + maxHotel.getId() + "</td> <td>"
                    + maxHotel.getName() + "</td> <td>"
                    + maxHotel.getDescription() + "</td> <td>"
                    + maxHotel.getPrice() + "</td> <td>"
                    + maxHotel.isSuite() + "</td> </tr>";
        }
        s += "</table>";
        return s;
    }

    private String allSorted(String s) {
        s += "<table>\n" +
                "<tr>\n" + "<th>" + "ID" + "</th>" +
                "<th>" + "Nome" + "</th>" +
                "<th>" + "Descrizione" + "</th>" +
                "<th>" + "Prezzo" + "</th>" +
                "<th>" + "Suite" + "</th>" +
                "</tr>\n";

        for (Hotel hotel : HotelManager.getInstance().sortHotel()) {
            s += "<tr><td>" + hotel.getId() + "</td> <td>"
                    + hotel.getName() + "</td> <td>"
                    + hotel.getDescription() + "</td> <td>"
                    + hotel.getPrice() + "</td> <td>"
                    + hotel.isSuite() + "</td> </tr>";

        }
        s += "</table>";
        return s;
    }

    public String hotelActions(String command, String s) {
        return switch (command.toLowerCase()) {
            case "all" -> toJSON(s);
            case "most_expensive" -> moreExpensive(s);
            case "all_sorted" -> allSorted(s);
            default -> "Comando Errato";
        };
    }
}