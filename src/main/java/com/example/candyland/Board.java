package com.example.candyland;

import java.util.*;

/**
 * Represents the Candy Land game board.
 */
public class Board {
    private final List<Color> spaces;
    private final Map<String, Integer> specialLocations;
    private final int boardSize;
    
    /**
     * Creates a new Candy Land board.
     */
    public Board() {
        this.boardSize = 134; // Traditional Candy Land board size
        this.spaces = new ArrayList<>(boardSize);
        this.specialLocations = new HashMap<>();
        initializeBoard();
    }
    
    /**
     * Initializes the board with colored spaces and special locations.
     */
    private void initializeBoard() {
        // Create a pattern of colored spaces
        Color[] colorPattern = {Color.RED, Color.PURPLE, Color.YELLOW, 
                               Color.BLUE, Color.ORANGE, Color.GREEN};
        
        for (int i = 0; i < boardSize; i++) {
            spaces.add(colorPattern[i % colorPattern.length]);
        }
        
        // Add special character locations
        specialLocations.put("Plumpy", 8);
        specialLocations.put("Mr. Mint", 17);
        specialLocations.put("Jolly", 33);
        specialLocations.put("Lord Licorice", 47);
        specialLocations.put("Gramma Nutt", 75);
        specialLocations.put("Princess Lolly", 95);
        specialLocations.put("Queen Frostine", 104);
        specialLocations.put("King Kandy", 118);
    }
    
    /**
     * Gets the color of the space at the given position.
     * 
     * @param position the position on the board
     * @return the color of the space, or null if position is invalid
     */
    public Color getSpaceColor(int position) {
        if (position < 0 || position >= boardSize) {
            return null;
        }
        return spaces.get(position);
    }
    
    /**
     * Finds the next space of the given color from the current position.
     * 
     * @param currentPosition the current position
     * @param color the color to find
     * @param findSecond if true, find the second occurrence
     * @return the position of the next space with the given color
     */
    public int findNextColorSpace(int currentPosition, Color color, boolean findSecond) {
        int count = 0;
        for (int i = currentPosition + 1; i < boardSize; i++) {
            if (spaces.get(i) == color) {
                count++;
                if ((!findSecond && count == 1) || (findSecond && count == 2)) {
                    return i;
                }
            }
        }
        // If not found, return the last space
        return boardSize - 1;
    }
    
    /**
     * Gets the position of a special character.
     * 
     * @param characterName the name of the special character
     * @return the position of the character, or -1 if not found
     */
    public int getSpecialCharacterPosition(String characterName) {
        return specialLocations.getOrDefault(characterName, -1);
    }
    
    /**
     * Gets the total size of the board.
     * 
     * @return the board size
     */
    public int getBoardSize() {
        return boardSize;
    }
    
    /**
     * Checks if a position is the winning space.
     * 
     * @param position the position to check
     * @return true if this is the winning position
     */
    public boolean isWinningSpace(int position) {
        return position >= boardSize - 1;
    }
}