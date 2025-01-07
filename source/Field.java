/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 * Abstract class representing a field on the game board.
 * Fields can be landed on by players, and different types of fields have different effects.
 * 
 * @author acer
 */
public abstract class Field {
    
    private String name;

    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public abstract void landOn(Player player);
    
}

/**
 * Represents a property field that players can purchase and upgrade.
 */
class Property extends Field{
    
    protected boolean isPurchased; 
    protected Player owner;
    protected boolean isUpgraded; 
    protected int price = 1000;
    
    public int getPrice(){
        return this.price;
    }
    
    
    public Property(){
        super("Property");
        this.isPurchased = false;
        this.owner = null;
        this.isUpgraded = false;
    }
    
    /**
     * Defines what happens when a player lands on the property.
     * If the property is not purchased and the player has enough money, they can buy it.
     * If it is owned by another player, the player must pay rent.
     * If the owner lands on their own property, they may choose to upgrade it.
     *
     * @param player the player landing on the property
     */
    @Override 
    public void landOn(Player player){
        
        if(isPurchased == false && player.action(this)){
            this.isPurchased = true;
            this.owner = player;
            player.buyField(this);
            player.setMoney(player.getMoney() - price);
        }
        
        if(isPurchased == true && this.owner != player){
            int rent = isUpgraded ? 2000 : 500;
            
        if (player.getMoney() >= rent) {
                player.setMoney(player.getMoney() - rent);
                this.owner.setMoney(this.owner.getMoney() + rent);
        }
        else{
                player.hasLost = true;
                player.lost();  // Player loses all properties
            }
        }
        
        if(isPurchased == true && this.isPurchased == false && this.owner == player && player.action(this) && player.getMoney() > 4000){
            this.isUpgraded = true;
            player.setMoney(player.getMoney() - 4000);
        }
        
         if (player.getMoney() < 0) {
            player.hasLost = true;
            player.lost(); 
        }
    }
}

/**
 * Represents a service field where players must pay a specified amount of money.
 */
class Service extends Field{
    
    protected final int amount; 
    
    public int getAmount(){
        return this.amount;
    }
    
    public Service(int amount){
        super("Service");
        this.amount = amount;
    }
    
    /**
     * Defines what happens when a player lands on the service field.
     * The player must pay the specified service fee.
     *
     * @param player the player landing on the field
     */
    @Override 
    public void landOn(Player player){
        player.payService(getAmount());
    }
}

/**
 * Represents a lucky field where players receive a specified amount of money.
 */
class Lucky extends Field{

    private final int amount; 
    
    public Lucky(int amount){
        super("Lucky");
        this.amount = amount;
    }
    
      /**
     * Defines what happens when a player lands on the lucky field.
     * The player receives the specified amount of money.
     *
     * @param player the player landing on the field
     */
    @Override 
    public void landOn(Player player){
        player.setMoney(player.getMoney() + amount);
    }
}
