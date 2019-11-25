package Game;


import GameEntities.Pilot;
import GameEntities.Ship;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author charlesurban
 */
public class Game extends GameSetup{
    
    private final Ship ship; //make the Game Objects
    private final Pilot pilot;
    Random random = new Random();
    private double pursuerDistance, pursuerDistanceTravelled;
    private double distanceTravelled;
    private String weather, location, time;
    private int timeOfDay;
    private final int difficulty;
    
    public Game(int difficulty, Ship ship, Pilot pilot) {
        this.difficulty = difficulty;
        this.ship = ship;
        this.pilot = pilot;
    }
    
    public void start() {
        getNewLocation();
        getNewWeather();
        getNewTimeOfDay();
        do {
          processTurn(); 
        } while(checkShipCanTravel() != false);
        
    }
    
    public boolean checkShipCanTravel() {
        boolean bool = (ship.getBatteries() <= 0 || ship.getDamage() >= 100 || ship.getFuel() <= 0);
        return bool;
    }
    
    public boolean checkPilotIsDead() {
        boolean bool = (pilot.getEnergy() <= 0 || pilot.getHunger() <= 0 || pilot.getThirst() <= 0);
        return bool;
    }
    
    private String getNewLocation() {
        int locationPicker = random.nextInt(15);
        if (locationPicker == 0 || locationPicker == 1) {
            location = "Hutt Space";
        }
        
        if (locationPicker == 2 || locationPicker == 3) {
            location = "Western Reaches";
        }
        
        if (locationPicker == 4 || locationPicker == 5) {
            location = "Outer Rim";
        }
        
        if (locationPicker == 6 || locationPicker == 7 || locationPicker == 8 || locationPicker == 9 || locationPicker == 10 || locationPicker == 11) {
            location = "The Core";
        }
        
        else {
            location = "Mid Rim";
        }
        return location;
    }
    
    private String getNewWeather() {
        int weatherPicker = random.nextInt(8);
        switch (weatherPicker) {
            case 0:
                weather = "Solar Winds";
                break;
            case 1:
                weather = "Astroids!";
                break;
            case 2: case 3: case 4: case 5:
                weather = "Clear Space All Around You";
                break;
            default:
                weather = "A huge cluster of ships is around you";
                break;
        }
        return weather;
    }
    
    private String getNewTimeOfDay() {
        if (timeOfDay > 3) {
            timeOfDay = 0;
        }
        else {
        timeOfDay++;
        }
        
        switch (timeOfDay) {
            case 0:
                time = "Morning";
                break;
            case 1:
                time = "Afternoon";
                break;
            case 2:
                time = "Evening";
                break;
            default:
                time = "Night";
                break;    
        }
        
        return null;
    }
    
    private void processTurn() {
            System.out.println("Time: " + time);
            System.out.println("Weather: " + weather);
            System.out.println("Location: " + location);
            System.out.println("What would you like to do next?");
            System.out.println("1. Rest and Repair Ship");
            System.out.println("2. Stop and Search for Food");
            System.out.println("3. Stop and Search for Water");
            System.out.println("4. Fly Carefully");
            System.out.println("5. Fly Regulary");
            System.out.println("6. Jump to LightSpeed");
            System.out.println("7. Check how far away the Galactic Empire is");
            System.out.println("8. Check ship and pilot levels");
            Scanner turn = new Scanner(System.in);
            int processTurn = turn.nextInt();
            switch (processTurn) {
                case 1:
                    processRestAndRepair();
                    break;
                case 2:
                    processFoodSearch();
                    break;
                case 3:
                    processWaterSearch();
                    break;
                case 4:
                    processTravelCarefully();
                    break;
                case 5:
                    processTravelRegularly();
                    break;
                case 6:
                    processJumpToLightSpeed();
                    break;
                case 7:
                    processPursuers();
                    break;
                case 8:
                    processStats();
                    break;
                default:
                    System.out.println("Please select a listed option.");
            }
    }
    
    private void processRestAndRepair() {
        pilot.adjustEnergy(pilot.getEnergy() + random.nextInt(20)+1);
        System.out.println("Hopefully this quick nap wont allow the Galactic Empire to catch us.");
        System.out.println("Z... Z... Z... Z...");
        System.out.println("Rise and Shine! That nap was great! My energy level is now up to " + pilot.getEnergy() + ".");
        ship.adjustDamage(ship.getDamage()-5);
        System.out.println("Looks like Chewwie worked on the ship while I napped. Now our damage level is at " +ship.getDamage() + ".");
        pursuerDistanceTravelled += random.nextInt(150) + 150;
        getNewTimeOfDay();
        getNewWeather();
    }
    
    private void processFoodSearch() {
        System.out.println("You landed on the closest planet, hopefully there is some food here.");
        Random food = new Random();
        int randFood = food.nextInt(30);
        System.out.println("You found " + randFood + " lbs of food.");
        pilot.adjustHunger(randFood + pilot.getHunger());
        System.out.println("Your hunger level is now at " + pilot.getHunger() + ".");
        pursuerDistanceTravelled += random.nextInt(150) + 150;
        getNewTimeOfDay();
        getNewWeather();
    }
    
    private void processWaterSearch() {
        System.out.println("You landed on the closest planet, hopefully there is some food here.");
        Random water = new Random();
        int randWater = water.nextInt(30);
        System.out.println("You found " + randWater + " cups of water.");
        pilot.adjustThirst(pilot.getThirst() + randWater);
        System.out.println("Your thirst level is now at " + pilot.getThirst() + ".");
        pursuerDistanceTravelled += random.nextInt(150) + 150;
        getNewTimeOfDay();
        getNewWeather();
    }
    
    private void processTravelCarefully() { //Travel 50 - 100 miles
        System.out.println("Sit back and relax! We're going to take this nice and slow today.");
        distanceTravelled += random.nextInt(50) + 50; 
       
        //Ship Adjustments
        ship.adjustFuel(ship.getFuel() - (random.nextInt(5) + 1)); //Adjust Fuel Levels
        ship.adjustBatteries(ship.getBatteries() - (random.nextInt(5) + 1)); //Adjust Battery Levels
        if (null == weather) {
            ship.adjustDamage(ship.getDamage() + 5);
        }
        else //Damage With Weather
        switch (weather) {
            case "Astroids!":
                ship.adjustDamage(ship.getDamage() + random.nextInt(5)+1); // Deal 1-5 damage
                break;
            case "Solar Winds":
                ship.adjustDamage(ship.getDamage() + random.nextInt(6)); //Deal 0-5 Damage
                break;
            case "A huge cluster of ships is around you":
                ship.adjustDamage(ship.getDamage() + random.nextInt(6)); //Deal 0-5 Damage
                break;
            default:
                ship.adjustDamage(ship.getDamage() + 5);
                break;
        }
       
        //Pilot Adjustments
        pilot.adjustHunger(pilot.getHunger() - (random.nextInt(5)+1));//Adjust Hunger Levels
        pilot.adjustThirst(pilot.getThirst() - (random.nextInt(5)+1));//Adjust Thirst Levels
        pilot.adjustEnergy(pilot.getEnergy() - (random.nextInt(5)+1));//Adjust Energy Levels
        pursuerDistanceTravelled += random.nextInt(150) + 150; //Pursuer Travels 150-300 miles
        getNewTimeOfDay(); //Advance Forward in the day.
        getNewWeather();
        getNewLocation();
    }
    
    private void processTravelRegularly() { //Travel 200-300 miles
        System.out.println("We won't get too crazy flying today.");
        distanceTravelled += random.nextInt(100) + 200; 
       
        //Ship Adjustments
        ship.adjustFuel(ship.getFuel() - (random.nextInt(15) + 1)); //Adjust Fuel Levels
        ship.adjustBatteries(ship.getBatteries() - (random.nextInt(15) + 1)); //Adjust Battery Levels
        if (null == weather) {
            ship.adjustDamage(ship.getDamage() + random.nextInt(5));
        }
        else //Damage With Weather
        switch (weather) {
            case "Astroids!":
                ship.adjustDamage(ship.getDamage() + random.nextInt(10)+15); // Deal 15-25 damage
                break;
            case "Solar Winds":
                ship.adjustDamage(ship.getDamage() + random.nextInt(5)+10); //Deal 10-15 Damage
                break;
            case "A huge cluster of ships is around you":
                ship.adjustDamage(ship.getDamage() + random.nextInt(5)+15); //Deal 15-20 Damage
                break;
            default:
                ship.adjustDamage(ship.getDamage() + random.nextInt(5));
                break;
        }
              
        //Pilot Adjustments
        pilot.adjustHunger(pilot.getHunger() - (random.nextInt(15)+1));//Adjust Hunger Levels
        pilot.adjustThirst(pilot.getThirst() - (random.nextInt(15)+1));//Adjust Thirst Levels
        pilot.adjustEnergy(pilot.getEnergy() - (random.nextInt(15)+1));//Adjust Energy Levels
        pursuerDistanceTravelled += random.nextInt(150) + 150; //Pursuer Travels 150-300 miles
        getNewTimeOfDay(); //Advance Forward in the day.
        getNewWeather();
        getNewLocation();
    }
    
    private void processJumpToLightSpeed() { //Travel 500-750 miles
        System.out.println("Everyone buckle up, we're jumping to light speed!");
        distanceTravelled += random.nextInt(250) + 500; 
        
        //Ship Adjustments
        ship.adjustFuel(ship.getFuel() - (random.nextInt(5) + 1)); //Adjust Fuel Levels
        ship.adjustBatteries(ship.getBatteries() - (random.nextInt(5) + 1)); //Adjust Battery Levels
        if (null == weather) {
            ship.adjustDamage(ship.getDamage() + 5);
        }
        else //Damage based of Weather
        switch (weather) {
            case "Astroids!":
                ship.adjustDamage(ship.getDamage() + random.nextInt(10)+25); // Deal 25-35 damage
                break;
            case "Solar Winds":
                ship.adjustDamage(ship.getDamage() + random.nextInt(10)+10); //Deal 10-20 Damage
                break;
            case "A huge cluster of ships is around you":
                ship.adjustDamage(ship.getDamage() + random.nextInt(15)+10); //Deal 10-25 Damage
                break;
            default:
                ship.adjustDamage(ship.getDamage() + 5);
                break;
        }
       
        //Pilot Adjustments
        pilot.adjustHunger(pilot.getHunger() - (random.nextInt(10)+10));//Adjust Hunger Levels
        pilot.adjustThirst(pilot.getThirst() - (random.nextInt(10)+10));//Adjust Thirst Levels
        pilot.adjustEnergy(pilot.getEnergy() - (random.nextInt(10)+10));//Adjust Energy Levels
        pursuerDistanceTravelled += random.nextInt(150) + 150; //Pursuer Travels 150-300 miles
        getNewTimeOfDay(); //Advance Forward in the day.
        getNewWeather();
        getNewLocation();
    }
    
    private void processPursuers() {
        pursuerDistance = distanceTravelled - pursuerDistanceTravelled;
        System.out.append("Keep an eye out, the Galactic Empire is " + pursuerDistance + " miles behind us.");
    }
    
    private void processStats() {
        System.out.println("Ship Fuel Levels: " + ship.getFuel());
        System.out.println("Ship Battery Levels: " + ship.getBatteries());
        System.out.println("Ship Damage Levels: " + ship.getDamage());
        System.out.println("Pilot Hunger Levels: " + pilot.getHunger());
        System.out.println("Pilot Thirst Levels: " + pilot.getThirst());
        System.out.println("Pilot Energy Levels: " + pilot.getEnergy());
    }
    
}
