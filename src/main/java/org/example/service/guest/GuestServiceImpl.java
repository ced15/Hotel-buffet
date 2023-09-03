package org.example.service.guest;

import org.example.model.GuestType;
import org.example.model.Guest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class GuestServiceImpl implements GuestService{
    List<String> lastNames = Arrays.asList(
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Miller", "Davis", "Garcia", "Rodriguez", "Martinez",
            "Lopez", "Hernandez", "Gonzalez", "Wilson", "Anderson"
    );
    List<String> firstNames = Arrays.asList(
            "Emma", "Liam", "Olivia", "Noah", "Ava",
            "Oliver", "Isabella", "Sophia", "Mia", "William",
            "James", "Evelyn", "Alexander", "Amelia", "Michael"
    );
    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
        String randomLastName = getRandomElement(lastNames);
        String randomFirstName = getRandomElement(firstNames);
        String name = randomFirstName+randomLastName;
        GuestType guestType = getRandomGuestType();

        LocalDate checkIn = getRandomDateBetween(seasonStart, seasonEnd);
        LocalDate checkOut = checkIn.plusDays(new Random().nextInt(7));
        if(checkOut.isAfter(seasonEnd)){
            checkOut = seasonEnd;
        }

        return new Guest(name, guestType, checkIn, checkOut);
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        Set<Guest> guestForToday= new HashSet<>();
        for(Guest guest: guests){
            if((guest.checkIn().isBefore(date)&&guest.checkOut().isAfter(date))||guest.checkOut().isEqual(date)){
                guestForToday.add(guest);
            }
        }
        return guestForToday;
    }

    @Override
    public List<List<Guest>> distributeGuestsIntoCycles(List<Guest> guests) {
        List<List<Guest>> breakfastCycles = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            breakfastCycles.add(new ArrayList<>());
        }
        Random random = new Random();
        for(Guest guest: guests){
            int randomIndex = random.nextInt(0,7);
            breakfastCycles.get(randomIndex).add(guest);
        }
        return breakfastCycles;
    }

    private GuestType getRandomGuestType() {
        GuestType[] guestTypes = GuestType.values();
        Random random = new Random();
        int randomIndex = random.nextInt(guestTypes.length);
        return guestTypes[randomIndex];
    }

    private LocalDate getRandomDateBetween(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("'start' date must be before 'end' date.");
        }
        Random random = new Random();
        long differenceDays = start.until(end, ChronoUnit.DAYS);
        long randomDays = random.nextLong() % (differenceDays + 1);
        return start.plusDays(Math.max(randomDays, 0));
    }

    private static String getRandomElement(List<String> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }


}
