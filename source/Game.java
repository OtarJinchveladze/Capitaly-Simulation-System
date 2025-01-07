/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game, managing players, the game board, and the rounds of play.
 * This class handles the game logic, including starting the game and moving players.
 * 
 * @author acer
 */
public class Game {
    private ArrayList<Player> players; 
    private Board board; 
    private ArrayList<ArrayList<Integer>> diceRolls; 

    public Game(ArrayList<Player> players, Board board, ArrayList<ArrayList<Integer>> diceRolls){
        this.players = players; 
        this.board = board; 
        this.diceRolls = diceRolls;
    }

       /**
     * Starts the game for a specified number of rounds.
     *
     * @param numberOfRounds the number of rounds to play
     */
    public void start(int numberOfRounds){
        for(int i = 0; i < numberOfRounds; i++){
            System.out.println("Round" + (i+1));
            for(int j = 0; j < players.size(); j++){
                Player player = players.get(j);
                int roll = diceRolls.get(j).get(i);
                board.movePlayer(player, roll);
            }
            printPlayerStates();
        }
    }
    
     /**
     * Prints the current states of all players, including their names,
     * the amount of money they have, the number of properties owned,
     * and their current positions on the board.
     */
    private void printPlayerStates() {
        for (Player player : players) {
            System.out.println(player.getName() + " has " + player.getMoney() + " and owns " + player.getOwnedFields().size() + " properties." + " position: " + player.getPosition());
        }
    }

    
    
}
