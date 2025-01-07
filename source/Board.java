/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game board where players move around fields.
 * The board manages player movement, interactions with fields, and handles the game's bank.
 * 
 * @author acer
 */
public class Board {
    
    private ArrayList<Player> players;
    private ArrayList<Field> fields;
    private Bank bank;
    private int boardSize; 
    
    public ArrayList<Field> getFields(){
        return this.fields;
    }

    public Board(ArrayList<Player> players, ArrayList<Field> fields, Bank bank, int boardSize){
        this.players = players;
        this.fields = fields;
        this.bank = bank;
        this.boardSize = boardSize;
    }    

     /**
     * Moves a player by a specified number of steps around the board.
     * If the player crosses the board size, they wrap around to the beginning.
     * After moving, the player lands on a field, triggering its effect.
     * 
     * @param player the player to be moved
     * @param numberOfSteps the number of steps to move the player
     */
    public void movePlayer(Player player, int numberOfSteps){
        int x = player.getPosition() + numberOfSteps;
        if(x > boardSize){
            x = (x % boardSize);
        }
        
        player.setPosition(x);

        Field LandedField = fields.get(player.getPosition() - 1);
        LandedField.landOn(player);
    }
}
