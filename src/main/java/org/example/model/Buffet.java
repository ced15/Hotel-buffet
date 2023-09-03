package org.example.model;

import java.util.List;

public record Buffet (List<Meal> mealList) {
        public void removeMeal(Meal meal){
            mealList.remove(meal);
        }
}
