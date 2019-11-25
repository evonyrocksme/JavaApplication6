/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *
 * @author charlesurban
 */
public class Main {
    
   public static void main(String args[]) {
       
       /*   Things I changed
       
       -    instead of calling start on the Game class, make an object and call it on that
                it saves you a lot of headache with dealing with static classes and instance
                variables for the Game objects, plus then you don't have to worry about game's
                instance variables getting fucked up or being non-accessible
       */
       
       Game game =  GameSetup.setupGame();
       game.start();
   }
   
   
    
}
