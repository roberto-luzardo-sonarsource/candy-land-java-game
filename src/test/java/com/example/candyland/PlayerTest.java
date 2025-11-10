package com.example.candyland;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Player class.
 */
class PlayerTest {
    private Player player;
    
    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer");
    }
    
    @Test
    void testPlayerCreation() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(0, player.getPosition());
    }
    
    @Test
    void testSetPosition() {
        player.setPosition(10);
        assertEquals(10, player.getPosition());
    }
    
    @Test
    void testMoveForward() {
        player.moveForward(5);
        assertEquals(5, player.getPosition());
        
        player.moveForward(3);
        assertEquals(8, player.getPosition());
    }
    
    @Test
    void testToString() {
        String expected = "TestPlayer (Position: 0)";
        assertEquals(expected, player.toString());
        
        player.setPosition(15);
        expected = "TestPlayer (Position: 15)";
        assertEquals(expected, player.toString());
    }
}