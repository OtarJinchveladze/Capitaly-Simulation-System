package assignment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * GameTest class contains unit test cases for testing the Capitaly game logic.
 * Each test case is designed to test different scenarios such as property buying,
 * bankruptcy, and player behavior.
 * 
 * @author acer
 */
public class GameTest {

    public static void main(String[] args) {
        // Run all the test cases
        test_LandsOnOwnUpgradedProperty();
        test_BuyPropertyWithExactMoney();
        test_BankruptcyWithMultipleProperties();
        test_ServiceFieldExactFee();
        test_TacticalPlayerSkipsTurn();
    }
    public static void test_LandsOnOwnUpgradedProperty() {
        Player player = new Greedy(5000, "Robert");
        Property property = new Property();
        
        // buying property
        player.buyField(property);
        property.isPurchased = true;
        property.owner = player;
        property.isUpgraded = true; // upgraded

        // landing on his property.
        property.landOn(player);

        // Expected: No change in player's money.
        System.out.println(player.getMoney()); // 5000
        // Actual : 5000. 
    }

    public static void test_BuyPropertyWithExactMoney() {
        Player player = new Greedy(1000, "Lucas");
        Property property = new Property();

        System.out.println("Money before buying a property: " + player.getMoney());
        property.landOn(player);

        // Expected: Player buys the property, money becomes 0
        System.out.println("Lucas'es money after buying property: " + player.getMoney()); // Should print 0
        System.out.println("Lucas owns: " + player.getOwnedFields().size() + " properties"); // Should print 1
    }

    public static void test_BankruptcyWithMultipleProperties() {
        Player player = new Greedy(2000, "Johna");
        Property property1 = new Property();
        Property property2 = new Property();

        // Player buys multiple properties
        player.buyField(property1);
        property1.isPurchased = true;
        property1.owner = player;

        player.buyField(property2);
        property2.isPurchased = true;
        property2.owner = player;

        // Player lands on a property with high rent and goes bankrupt.
        player.setMoney(500);  // Player has 500.
        player.payService(1000);  // High rent causes bankruptcy.

        // Expected: Player should lose all properties, null.
        System.out.println("Johna owns " + player.getOwnedFields().size() + "properties"); // 0
        System.out.println("Owner of first property : " + property1.owner); // null
        System.out.println("Owner of second property : " + property2.owner); // null
    }

    public static void test_ServiceFieldExactFee() {
        Player player = new Greedy(500, "Aziz");
        Service service = new Service(500);

        // Player lands on service field with a fee of 500
        service.landOn(player);

        // Expected: Player should have 0 money after paying the fee
        System.out.println("Aziz money: " + player.getMoney()); // Should print 0
    }

    public static void test_TacticalPlayerSkipsTurn() {
        Player tacticalPlayer = new Tactical(5000, "Kris");
        Property property1 = new Property();
        Property property2 = new Property();

        // First turn: Tactical player buys the property
        property1.landOn(tacticalPlayer);
        System.out.println("Kris'es properties: " + tacticalPlayer.getOwnedFields().size()); // Should print 1 (bought)

        // Second turn: Tactical player skips buying
        property2.landOn(tacticalPlayer);
        System.out.println("After skipping: " + tacticalPlayer.getOwnedFields().size()); // Should print 1 (skipped)
    }
}

