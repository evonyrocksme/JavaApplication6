
package GameEntities;

/**
 *
 * @author charlesurban
 */
public class Pilot {
    
    public int thirst;
    public int hunger;
    public int energy;
    public String name;
    boolean pilotDead;
    
    public Pilot(String pilotName) {
        name = pilotName;
    }
    
    public Pilot(String pilotName, int thirstLevel, int hungerLevel, int energyLevel) {
        name = pilotName;
        thirst = thirstLevel;
        hunger = hungerLevel;
        energy = energyLevel;
    }
    
    public int getThirst() {
        return this.thirst;
    }
    
    public void adjustThirst(int thirstLevel) {
        thirst = thirstLevel;
    }
    
    public int getEnergy() {
        return this.energy;
    }
    
    public void adjustEnergy(int energyLevel) {
        energy = energyLevel;
    }
    
    public int getHunger() {
        return this.hunger;
    }
    
    public void adjustHunger(int hungerLevel) {
        hunger = hungerLevel;
    }
    
    public boolean isPilotDead(){
        return pilotDead;
    }
    
    
}
