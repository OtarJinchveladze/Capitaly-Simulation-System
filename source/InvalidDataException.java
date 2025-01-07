/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 * This class represents a custom exception that is thrown when invalid data is encountered
 * during the execution of the game logic.
 * 
 * It extends the {@code Exception} class and displays an error message when the exception
 * is thrown.
 * 
 * @author acer
 */
class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
        System.out.println(message);
        
    }
}