package com.example.candyland;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for the CandyLandGame class.
 */
class CandyLandGameTest {
    private CandyLandGame game;
    
    @BeforeEach
    void setUp() {
        game = new CandyLandGame();
    }
    
    @Test
    void testAddPlayer() {
        assertEquals(0, game.getPlayers().size());
        
        Player player1 = new Player("Player 1");
        game.addPlayer(player1);
        assertEquals(1, game.getPlayers().size());
        assertEquals("Player 1", game.getPlayers().get(0).getName());
        
        game.addPlayer("Player 2");
        assertEquals(2, game.getPlayers().size());
        assertEquals("Player 2", game.getPlayers().get(1).getName());
    }
    
    @Test
    void testGetPlayers() {
        List<Player> players = game.getPlayers();
        assertTrue(players.isEmpty());
        
        game.addPlayer("Test Player");
        players = game.getPlayers();
        assertEquals(1, players.size());
    }
    
    @Test
    void testGameInitialization() {
        assertNotNull(game.getBoard());
        assertNotNull(game.getDeck());
        assertNotNull(game.getPlayers());
    }
    
    @Test
    void testGameHasPlayers() {
        // Initially no players
        assertEquals(0, game.getPlayers().size());
        
        game.addPlayer("Test Player");
        assertEquals(1, game.getPlayers().size());
        
        // Verify player is at starting position
        assertEquals(0, game.getPlayers().get(0).getPosition());
    }
}