package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HotelManager {
    private List<Hotel> hotelList = new ArrayList<>();

    private static HotelManager INSTANCE;


    public HotelManager(){
        hotelList.add(new Hotel("Hotel prova1","Gran Majestic",1,2500.94,true));
        hotelList.add(new Hotel("Sulle colline toscane","Albergo dei Re",34,303.4,false));
        hotelList.add(new Hotel("Albergo con la A","AHotel",44,1200.4,true));
        hotelList.add(new Hotel("Albergo com la B","BHotel",54,1500.4,true));
        hotelList.add(new Hotel("Costoso","Boss Hotel", 20,12200.2,true));
    }

    public static HotelManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HotelManager();
        }
        return INSTANCE;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public List<Hotel> sortHotel(){
        List<Hotel> newHotelList = new ArrayList<>(hotelList);
        newHotelList.sort(Comparator.comparing(Hotel::getName));
        return newHotelList;
    }
}
