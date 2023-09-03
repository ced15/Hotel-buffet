package org.example.model;
import java.time.LocalTime;
import java.util.UUID;

public class Meal {
    public UUID id = UUID.randomUUID();
    public LocalTime timeStamp;
    public MealType mealType;

    public Meal(LocalTime creationTime, MealType mealType) {
        this.timeStamp = creationTime;
        this.mealType = mealType;
    }
    public LocalTime getCreationTime() {
        return timeStamp;
    }

    public MealType getMealType() {
        return mealType;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "timeStamp=" + timeStamp +
                ", mealType=" + mealType +
                '}';
    }
}
