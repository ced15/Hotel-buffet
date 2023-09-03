package org.example.model;

import java.time.LocalDate;
import java.util.Random;

public record Guest(String name, GuestType guestType, LocalDate checkIn, LocalDate checkOut) {
    public MealType getRandomPrefMealType(){
        Random random = new Random();
        int randomIndex = random.nextInt(0, guestType.getMealPreferences().size());
        MealType mealType =guestType.getMealPreferences().get(randomIndex);
        return mealType;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", guestType=" + guestType +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
