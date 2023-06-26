package org.example.TCPServer;
import com.google.gson.Gson;
import org.example.Hotel;
import org.example.HotelManager;

import java.util.ArrayList;
import java.util.Comparator;

public class HotelListTCP {
    private static HotelListTCP INSTANCE;
    private static final Gson gson = new Gson();

    public static HotelListTCP getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HotelListTCP();
        }

        return INSTANCE;
    }

    private String toJSON() {
        return gson.toJson(HotelManager.getInstance().getHotelList());
    }

    private String moreExpensive(){
        String jsonHotel = "";
        double maxPrice = 0;
        for(Hotel hotel : HotelManager.getInstance().getHotelList()){
            if(hotel.getPrice() > maxPrice && hotel.isSuite()){
                maxPrice = hotel.getPrice();
                jsonHotel = gson.toJson(hotel);
            }
        }
        return jsonHotel;
    }

    private String allSorted(){
        return gson.toJson(HotelManager.getInstance().sortHotel());
    }

    String hotelActions(String command) {
        return switch (command.toLowerCase()) {
            case "all" -> toJSON();
            case "more_expensive" -> moreExpensive();
            case "all_sorted" -> allSorted();
            default -> "Comando Errato i comandi sono all, all_sorted, more_expensive";
        };
    }
}