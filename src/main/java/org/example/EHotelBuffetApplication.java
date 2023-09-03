package org.example;

import org.example.model.Buffet;
import org.example.model.Guest;
import org.example.service.breakfastManager.BreakfastManager;
import org.example.service.breakfastManager.BreakfastManagerImpl;
import org.example.service.buffet.BuffetService;
import org.example.service.buffet.BuffetServiceImpl;
import org.example.service.guest.GuestService;
import org.example.service.guest.GuestServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EHotelBuffetApplication {

    public static void main(String[] args) {
        int numberOfGuests = 600;
        LocalDate seasonStart = LocalDate.of(2023,5,1);
        LocalDate seasonEnd = LocalDate.of(2023,5,15);


        // Initialize services
        GuestService guestService = new GuestServiceImpl();
        BreakfastManager breakfastManager = new BreakfastManagerImpl();

        // Generate guests for the season
        List<Guest> guestsList = new ArrayList<>();
        for(int i=0; i<numberOfGuests;i++){
            guestsList.add(guestService.generateRandomGuest(seasonStart, seasonEnd));
        }
        LocalDate today = seasonStart;
        while(today.isBefore(seasonEnd)){
            Buffet buffet=new Buffet(new ArrayList<>());
            Set<Guest> guestsForToday = guestService.getGuestsForDay(guestsList, today);

            List<List<Guest> >guestsInCycles = guestService.distributeGuestsIntoCycles(guestsForToday.stream().toList());
            breakfastManager.serve(guestsList, guestsInCycles, buffet);
        }

        // Run breakfast buffet


    }
}
