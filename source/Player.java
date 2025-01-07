/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a player in the game.
 * Each player has a certain amount of money, a name, and can own fields.
 * Players can perform actions based on their strategies.
 * 
 * @author acer
 */
public abstract class Player {
    protected int money; 
    protected String name; 
    protected ArrayList<Field> ownedFields;
    protected int position = 0; 
    protected boolean hasLost = false;
    
    
    public Player(int money, String name){
        this.money = money;
        this.name = name; 
        this.ownedFields = new ArrayList<>();
    }
    
    public int getMoney(){
        return this.money;
    }
    
    public void setMoney(int balance){
        this.money = balance;
        if(this.money < 0){
            this.lost();
        }
    }
    
    public String getName(){
        return this.name;
    }
    
    public ArrayList<Field> getOwnedFields(){
        return this.ownedFields;
    }
    
    public int getPosition(){
        return this.position;
    }
    
    public void setPosition(int newPosition){
        this.position = newPosition;
    }
    
    public void payService(int amount){
        int newBalance = this.money - amount;
        setMoney(newBalance);
    }
    
    /**
     * Marks the player as lost, clearing their owned fields.
     * All owned properties are reset to indicate they are no longer owned.
     */
    public void lost(){
        for(int i = 0; i < ownedFields.size(); i++){
            Field field = ownedFields.get(i);
            if(field instanceof Property){
                Property property = (Property) field;
                property.isPurchased = false;
                property.owner = null;
                property.isUpgraded = false;
            }
            
        }
        ownedFields.clear();
    }
    
    public void buyField(Property Property){
        this.ownedFields.add(Property);
    }
    
    /**
     * Abstract method that determines the action taken by the player when attempting to
     * purchase a property. Implementations will vary based on player strategy.
     *
     * @param property the property the player is attempting to purchase
     * @return true if the action is successful, false otherwise
     */
    public abstract boolean action(Property property);
    
}


/**
 * Represents a greedy player who always tries to buy properties if they can afford them.
 */
class Greedy extends Player{
    public Greedy(int money, String name){
        super(money, name);
    }

    @Override 
    public boolean action(Property Property){
        return this.money >= Property.getPrice();
    }
}

/**
 * Represents a careful player who only attempts to buy properties
 * if they can afford at least half of the property price.
 */
class Careful extends Player{
    
    public Careful(int money, String name){
        super(money, name);
    }

    @Override 
    public boolean action(Property Property){
       return this.money >= Property.getPrice() / 2;
    }
}

/**
 * Represents a tactical player who alternates their ability to buy properties.
 * They can skip every other turn when trying to buy a property.
 */
class Tactical extends Player{
    
    protected boolean skip = false;
    
    public Tactical(int money, String name){
        super(money, name);
    }

    @Override 
    public boolean action(Property Property){
        if(skip == false){
            skip = true;
            return this.money >= Property.getPrice();
        }else{
            skip = false;
            return false;
        }
    }
}