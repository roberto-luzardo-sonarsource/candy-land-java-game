package com.example.candyland;

import javax.swing.ImageIcon;

/**
 * Represents a player in the Candy Land game.
 */
public class Player {
    private final String name;
    private int position;
    private transient ImageIcon avatar;
    private String avatarPath;
    
    /**
     * Creates a new player with the given name.
     * 
     * @param name the player's name
     */
    public Player(String name) {
        this.name = name;
        this.position = 0; // Start at the beginning of the board
        this.avatar = null;
        this.avatarPath = null;
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
    
    /**
     * Gets the player's avatar image.
     * 
     * @return the player's avatar, or null if not set
     */
    public ImageIcon getAvatar() {
        return avatar;
    }
    
    /**
     * Sets the player's avatar image.
     * 
     * @param avatar the avatar image
     */
    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }
    
    /**
     * Gets the path to the player's avatar image file.
     * 
     * @return the avatar file path, or null if not set
     */
    public String getAvatarPath() {
        return avatarPath;
    }
    
    /**
     * Sets the path to the player's avatar image file.
     * 
     * @param avatarPath the avatar file path
     */
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
    
    /**
     * Checks if the player has an avatar set.
     * 
     * @return true if the player has an avatar
     */
    public boolean hasAvatar() {
        return avatar != null;
    }
    
    @Override
    public String toString() {
        return name + " (Position: " + position + ")";
    }
}