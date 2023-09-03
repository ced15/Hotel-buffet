package org.example.service.buffet;
import org.example.model.Buffet;
import org.example.model.Meal;
import org.example.model.MealDurability;
import org.example.model.MealType;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class BuffetServiceImpl implements BuffetService{

    @Override
    public void addPortions(Buffet buffet, MealType type, LocalTime timestamp, int amount) {
        for(int i=0;i<amount;i++){
            Meal meal = new Meal(timestamp,type);
            buffet.mealList().add(meal);
        }
    }

    @Override
    public boolean consumeFreshest(Buffet buffet, MealType mealType) {
        List<Meal> desireMeal = new ArrayList<>();
        for(Meal meal:buffet.mealList()){
            if(meal.mealType.equals(mealType)){
                desireMeal.add((meal));
            }
        }
        if(desireMeal.size()==0){
            return false;
        }
       Meal freshMeal = desireMeal.get(0);
        for (Meal meal:desireMeal){
            if(meal.timeStamp.isAfter(freshMeal.timeStamp)){
                freshMeal = meal;
            }
        }
        buffet.mealList().remove(freshMeal);
        System.out.println("Ate "+mealType);
        return true;

    }

    @Override
    public int collectWaste(Buffet buffet, MealDurability durability, LocalTime timestamp) {
        int sumOfWaste = 0;
        for(int i =0; i<buffet.mealList().size(); i++){
            if (buffet.mealList().get(i).mealType.getDurability().equals(durability) && timestamp.isAfter(buffet.mealList().get(i).timeStamp)) {
                sumOfWaste +=buffet.mealList().get(i).mealType.getCost();
                buffet.removeMeal(buffet.mealList().get(i));
                i--;
            }

        }
        return sumOfWaste;
    }
}
