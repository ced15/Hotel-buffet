package org.example.service.guest;
import org.example.model.Guest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface GuestService {

    Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd);

    Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date);

    List<List<Guest>> distributeGuestsIntoCycles(List<Guest> guests);
}
