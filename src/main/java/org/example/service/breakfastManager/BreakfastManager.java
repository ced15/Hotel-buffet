package org.example.service.breakfastManager;


import org.example.model.Buffet;
import org.example.model.Guest;

import java.util.List;

public interface BreakfastManager {
    void serve(List<Guest> guests, List<List<Guest>> guestGroups, Buffet buffet);
}
