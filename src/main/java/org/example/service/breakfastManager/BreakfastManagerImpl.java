package org.example.service.breakfastManager;
import org.example.model.*;
import org.example.service.buffet.BuffetService;
import org.example.service.buffet.BuffetServiceImpl;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BreakfastManagerImpl implements BreakfastManager {
    private BuffetService buffetService= new BuffetServiceImpl();
    private Logger logger=new LoggerImpl();
    @Override
    public void serve(List<Guest> guests, List<List<Guest>> guestGroups, Buffet buffet) {
        Scanner scanner = new Scanner(System.in);
        int happy = 0;
        int unhappy = 0;
        int sumOfWaste = 0;
        for (int i = 0; i < 8; i++) {

            logger.logInfo("Press enter to start the "+ (i+1)+" cycle");
            String input = scanner.nextLine();
            int cycle = i;
            LocalTime cyclesStart = LocalTime.of(6, 0);
            LocalTime timestamp = cyclesStart.plusMinutes(30*cycle);
            addMealsForCycle(guests.size(), buffet, i);
            System.out.println(buffet.mealList());
            for (Guest guest:guestGroups.get(i)){
                if (buffetService.consumeFreshest(buffet,guest.getRandomPrefMealType())) {
                    happy++;
                }
                else{
                    unhappy++;
                }
            }
            System.out.println(guestGroups.get(i).size());
            timestamp=timestamp.plusMinutes(30);
            sumOfWaste+=buffetService.collectWaste(buffet, MealDurability.SHORT, timestamp.minusMinutes(89));


        }
        logger.logInfo("Today we served "+guestGroups.size()+" clients. "+happy+" of them was happy, and "+unhappy+" unhappy. Also we have a total lost of "+sumOfWaste+" LEI.");
    }

    private void addMealsForCycle(int guestSize, Buffet buffet, int cycle) {
        Random random = new Random();
        int averageGuestPerCycle = guestSize / 8;
        double dropChance = (double) (averageGuestPerCycle/3) / MealType.values().length;

        LocalTime cyclesStart = LocalTime.of(6, 0);
        LocalTime timestamp = cyclesStart.plusMinutes(30*cycle);

        for (MealType mealType : MealType.values()) {
            int numberOfMeal = 0;
            numberOfMeal += Math.floor(dropChance);
            numberOfMeal += random.nextDouble() < (dropChance - numberOfMeal) ? 1 : 0;
            int inStockMealType = 0;
            for(Meal meal : buffet.mealList()){
                if(meal.mealType.equals(mealType)){
                    inStockMealType ++;
                }
            }
            if(inStockMealType<numberOfMeal) {
                numberOfMeal -= inStockMealType;
            }
            else {
                numberOfMeal = 0;
            }
            buffetService.addPortions(buffet, mealType, timestamp , numberOfMeal);
            System.out.println("Added "+numberOfMeal+" "+mealType);

        }
    }
}
