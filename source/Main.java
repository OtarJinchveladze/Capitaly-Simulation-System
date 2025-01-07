    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
     */
    package assignment;

    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.ArrayList;


    
/**
 * The Main class is the entry point of the Capitaly game.
 * It reads the game configuration from a file, initializes the game board, players,
 * and fields, and starts the game.
 * 
 * The input file must follow a specific format:
 * - The first line contains the number of fields.
 * - The following lines describe the fields (either Property, Service, or Lucky).
 * - After the fields, the file contains the number of players, followed by their strategies.
 * - Lastly, the file contains the dice rolls for each player.
 * 
 * @author acer
 */
    public class Main {

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) throws InvalidDataException {
             try {

                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\acer\\Desktop\\Otar\\ELTE\\Semester 3\\Programming Technology\\Assignment_SA\\file.txt"));
                // KNOWN 
                int initialMoney = 10000;
                // READING -> NUMBER 'FOR' FIELDS
                int numberofField = Integer.parseInt(reader.readLine()); 
                
                if(numberofField <= 0){
                    throw new InvalidDataException("Invalid number of fields: " + numberofField);
                }

                // READING -> FIELDS
                ArrayList<Field> fields = new ArrayList<>();
                
                for(int i = 0; i < numberofField; i++){
                    
                    String[] temp = reader.readLine().split(" ");
                    
                    if(temp.length == 0){
                        throw new InvalidDataException("No fields to read on line" + (i + 1));
                    }
                    
                    switch (temp[0]) {
                        case "Property":
                            fields.add(new Property());
                            break;
                        case "Service":
                            if(temp.length < 2 || !isValidInteger(temp[1])){
                                throw new InvalidDataException("Invalid service cost on line " + (i + 1));
                            }   int cost = Integer.parseInt(temp[1]);
                            fields.add(new Service(cost));
                            fields.add(new Service(cost));
                            break;
                        case "Lucky":
                            if (temp.length < 2 || !isValidInteger(temp[1])) {
                                throw new InvalidDataException("Invalid lucky reward on line " + (i + 1));
                            }       int reward = Integer.parseInt(temp[1]);
                            fields.add(new Lucky(reward));
                            break;
                        default:
                            throw new InvalidDataException("Unknown field type on line " + (i + 1));
                    }
                }

                // READING -> NUMBER OF PLAYERS
                int numberofPlayers = Integer.parseInt(reader.readLine());
                
                 if(numberofPlayers <= 0){
                    throw new InvalidDataException("Invalid number of players: " + numberofPlayers);
                }

                // READING -> PLAYERS 
                ArrayList<Player> players = new ArrayList<>();
                
                for(int i = 0; i < numberofPlayers; i++){
                    
                    String[] temp = reader.readLine().split(" ");
                    
                    if(temp.length < 2){
                       throw new InvalidDataException("Invalid player data on line: " + (i+1));
                    }
                    
                    String name = temp[0];
                    String strategy = temp[1];

                    switch (strategy) {
                        case "Greedy":
                            players.add(new Greedy(initialMoney, name));
                            break;
                        case "Careful":
                            players.add(new Careful(initialMoney, name));
                            break;
                        case "Tactical":
                            players.add(new Tactical(initialMoney, name));
                            break;
                        default:
                            throw new InvalidDataException("Unknown strategy on line " + (i + 1));
                    }
                }

                // READING -> ROLLED DICES
                ArrayList<ArrayList<Integer>> diceRolls = new ArrayList<ArrayList<Integer>>();
                for(int i = 0; i < numberofPlayers; i++){
                    String[] rolls = reader.readLine().split(" ");
                    ArrayList<Integer> playerRolls = new ArrayList<Integer>();
                    for(String roll : rolls){
                         if (!isValidInteger(roll)) {
                        throw new InvalidDataException("Invalid dice roll on line " + (i + 1));
                    }
                        playerRolls.add(Integer.parseInt(roll));
                    }
                    diceRolls.add(playerRolls);
                }
                // CLOSING READER 
                reader.close();

                //GAMEPLAY 
                Bank bank = new Bank();
                Board board = new Board(players, fields, bank, numberofField);
                Game game = new Game(players, board, diceRolls);
                
                // In the file, matrix row size is the limit of this number. 
                game.start(5);


            } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
            } catch (InvalidDataException e) {
            System.out.println("Invalid data: " + e.getMessage());
            }
        }
        
        private static boolean isValidInteger(String str) {
        
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

    }
