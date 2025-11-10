package com.example.candyland;

/**
 * Represents a player in the Candy Land game.
 */
public class Player {
    private final String name;
    private int position;
    
    /**
     * Creates a new player with the given name.
     * 
     * @param name the player's name
     */
    public Player(String name) {
        this.name = name;
        this.position = 0; // Start at the beginning of the board
    }
    
    /**
     * Gets the player's name.
     * 
     * @return the player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the player's current position on the board.
     * 
     * @return the player's position
     */
    public int getPosition() {
        return position;
    }
    
    /**
     * Sets the player's position on the board.
     * 
     * @param position the new position
     */
    public void setPosition(int position) {
        this.position = position;
    }
    
    /**
     * Moves the player forward by the specified number of spaces.
     * 
     * @param spaces the number of spaces to move
     */
    public void moveForward(int spaces) {
        this.position += spaces;
    }
    
    @Override
    public String toString() {
        return name + " (Position: " + position + ")";
    }
}