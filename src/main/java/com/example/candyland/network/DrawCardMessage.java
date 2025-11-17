package com.example.candyland.network;

/**
 * Message sent by a client to request drawing a card.
 */
public class DrawCardMessage extends GameMessage {
    private static final long serialVersionUID = 1L;
    
    private final String playerName;
    
    public DrawCardMessage(String playerName) {
        super(MessageType.DRAW_CARD);
        this.playerName = playerName;
    }
    
    public String getPlayerName() {
        return playerName;
    }
}
