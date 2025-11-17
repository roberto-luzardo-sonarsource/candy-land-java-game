package com.example.candyland;

import java.io.Serializable;

/**
 * Represents a card in the Candy Land game.
 */
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final Color color;
    private final boolean isDouble;
    private final String specialCharacter;
    
    /**
     * Creates a regular color card.
     * 
     * @param color the color of the card
     * @param isDouble whether this is a double color card (move to second occurrence)
     */
    public Card(Color color, boolean isDouble) {
        this.color = color;
        this.isDouble = isDouble;
        this.specialCharacter = null;
    }
    
    /**
     * Creates a special character card.
     * 
     * @param specialCharacter the name of the special character
     */
    public Card(String specialCharacter) {
        this.color = null;
        this.isDouble = false;
        this.specialCharacter = specialCharacter;
    }
    
    /**
     * Gets the color of the card.
     * 
     * @return the card's color, or null for special character cards
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Checks if this is a double color card.
     * 
     * @return true if this is a double color card
     */
    public boolean isDouble() {
        return isDouble;
    }
    
    /**
     * Gets the special character name.
     * 
     * @return the special character name, or null for regular color cards
     */
    public String getSpecialCharacter() {
        return specialCharacter;
    }
    
    /**
     * Checks if this is a special character card.
     * 
     * @return true if this is a special character card
     */
    public boolean isSpecialCharacter() {
        return specialCharacter != null;
    }
    
    @Override
    public String toString() {
        if (isSpecialCharacter()) {
            return "Special: " + specialCharacter;
        } else {
            return color + (isDouble ? " (Double)" : "");
        }
    }
}