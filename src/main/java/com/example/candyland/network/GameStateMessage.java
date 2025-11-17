package com.example.candyland.network;

import com.example.candyland.Player;
import java.util.List;

/**
 * Message containing the current game state, sent to all clients.
 */
public class GameStateMessage extends GameMessage {
    private static final long serialVersionUID = 1L;
    
    private final List<PlayerState> players;
    private final int currentPlayerIndex;
    private final boolean gameOver;
    private final String winnerName;
    
    public GameStateMessage(List<PlayerState> players, int currentPlayerIndex, 
                           boolean gameOver, String winnerName) {
        super(MessageType.GAME_STATE);
        this.players = players;
        this.currentPlayerIndex = currentPlayerIndex;
        this.gameOver = gameOver;
        this.winnerName = winnerName;
    }
    
    public List<PlayerState> getPlayers() {
        return players;
    }
    
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public String getWinnerName() {
        return winnerName;
    }
    
    /**
     * Represents the state of a player in the game.
     */
    public static class PlayerState implements java.io.Serializable {
        private static final long serialVersionUID = 1L;
        
        private final String name;
        private final int position;
        private final boolean isConnected;
        
        public PlayerState(String name, int position, boolean isConnected) {
            this.name = name;
            this.position = position;
            this.isConnected = isConnected;
        }
        
        public PlayerState(Player player, boolean isConnected) {
            this(player.getName(), player.getPosition(), isConnected);
        }
        
        public String getName() {
            return name;
        }
        
        public int getPosition() {
            return position;
        }
        
        public boolean isConnected() {
            return isConnected;
        }
    }
}
