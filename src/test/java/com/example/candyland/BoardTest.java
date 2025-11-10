package com.example.candyland;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Board class.
 */
class BoardTest {
    private Board board;
    
    @BeforeEach
    void setUp() {
        board = new Board();
    }
    
    @Test
    void testBoardSize() {
        assertEquals(134, board.getBoardSize());
    }
    
    @Test
    void testGetSpaceColor() {
        // Test valid positions
        assertNotNull(board.getSpaceColor(0));
        assertNotNull(board.getSpaceColor(50));
        
        // Test invalid positions
        assertNull(board.getSpaceColor(-1));
        assertNull(board.getSpaceColor(134));
    }
    
    @Test
    void testSpecialCharacterPositions() {
        assertEquals(8, board.getSpecialCharacterPosition("Plumpy"));
        assertEquals(17, board.getSpecialCharacterPosition("Mr. Mint"));
        assertEquals(33, board.getSpecialCharacterPosition("Jolly"));
        assertEquals(-1, board.getSpecialCharacterPosition("NonExistent"));
    }
    
    @Test
    void testFindNextColorSpace() {
        int nextRed = board.findNextColorSpace(0, Color.RED, false);
        assertTrue(nextRed > 0);
        assertEquals(Color.RED, board.getSpaceColor(nextRed));
    }
    
    @Test
    void testIsWinningSpace() {
        assertFalse(board.isWinningSpace(0));
        assertFalse(board.isWinningSpace(100));
        assertTrue(board.isWinningSpace(133));
        assertTrue(board.isWinningSpace(150)); // Beyond board
    }
}