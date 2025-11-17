package com.example.candyland.network;

import java.io.Serializable;

/**
 * Base class for all network messages exchanged between client and server.
 */
public abstract class GameMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final MessageType type;
    private final long timestamp;
    
    public enum MessageType {
        JOIN_GAME,
        PLAYER_JOINED,
        GAME_STATE,
        DRAW_CARD,
        CARD_DRAWN,
        GAME_OVER,
        ERROR,
        DISCONNECT
    }
    
    protected GameMessage(MessageType type) {
        this.type = type;
        this.timestamp = System.currentTimeMillis();
    }
    
    public MessageType getType() {
        return type;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
}
