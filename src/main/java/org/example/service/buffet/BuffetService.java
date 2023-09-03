package org.example.service.buffet;

import org.example.model.Buffet;
import org.example.model.Meal;
import org.example.model.MealDurability;
import org.example.model.MealType;

import java.time.LocalDate;
import java.time.LocalTime;

public interface BuffetService {
    void addPortions(Buffet buffet, MealType type, LocalTime timestamp, int amount);
    boolean consumeFreshest(Buffet buffet, MealType type);
    int collectWaste(Buffet buffet, MealDurability durability, LocalTime timestamp);

}
