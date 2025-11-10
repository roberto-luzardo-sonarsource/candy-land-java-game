package com.example.candyland;

import java.util.Random;

/**
 * Represents the different colors on the Candy Land board.
 */
public enum Color {
    RED,
    PURPLE,
    YELLOW,
    BLUE,
    ORANGE,
    GREEN;
    
    private static final Random random = new Random();
    
    /**
     * Returns a random color for card drawing.
     * 
     * @return a random Color
     */
    public static Color getRandomColor() {
        Color[] colors = values();
        return colors[random.nextInt(colors.length)];
    }
}