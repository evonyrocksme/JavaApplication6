/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

/**
 *
 * @author charlesurban
 */
public class Ship {
    
    public int fuel;
    public int batteries;
    public int damage;
    public String name;
    boolean isShipDestroyed;

    public Ship(String shipName) {
        this.name = shipName;
        this.fuel = 100;
        this.batteries = 100;
        this.damage = 0;
    }
    
    public Ship(String shipName, int shipFuel, int shipBatteries, int shipDamage) {
        this.name = shipName;
        this.fuel = shipFuel;
        this.batteries = shipBatteries;
        this.damage = shipDamage;
    }

    public Ship() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getFuel() {
        return fuel;
    }

    public void adjustFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getBatteries() {
        return batteries;
    }

    public void adjustBatteries(int batteries) {
        this.batteries = batteries;
    }

    public int getDamage() {
        return damage;
    }

    public void adjustDamage(int damage) {
        this.damage = damage;
    }
    
    
    
}
