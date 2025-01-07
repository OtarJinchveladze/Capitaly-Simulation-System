/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 * Represents the bank in the game, which manages the game's money reserve.
 * The bank starts with a default balance that can be accessed by other entities.
 * 
 * @author acer
 */
public class Bank {
    
    private int Balance = 100000; 
    
    public int getBalance(){
        return this.Balance;
    }
    
    public void setBalance(int b){
        this.Balance = b; 
    }
}
