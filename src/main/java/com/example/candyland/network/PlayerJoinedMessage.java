package com.example.candyland.network;

/**
 * Message sent when a player joins the game.
 */
public class PlayerJoinedMessage extends GameMessage {
    private static final long serialVersionUID = 1L;
    
    private final String playerName;
    private final int playerIndex;
    private final int totalPlayers;
    
    public PlayerJoinedMessage(String playerName, int playerIndex, int totalPlayers) {
        super(MessageType.PLAYER_JOINED);
        this.playerName = playerName;
        this.playerIndex = playerIndex;
        this.totalPlayers = totalPlayers;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public int getPlayerIndex() {
        return playerIndex;
    }
    
    public int getTotalPlayers() {
        return totalPlayers;
    }
}
