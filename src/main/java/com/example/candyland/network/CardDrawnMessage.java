package com.example.candyland.network;

import com.example.candyland.Card;

/**
 * Message sent by server when a card is drawn, containing the card and move details.
 */
public class CardDrawnMessage extends GameMessage {
    private static final long serialVersionUID = 1L;
    
    private final String playerName;
    private final Card card;
    private final int oldPosition;
    private final int newPosition;
    private final String moveDescription;
    
    public CardDrawnMessage(String playerName, Card card, int oldPosition, 
                           int newPosition, String moveDescription) {
        super(MessageType.CARD_DRAWN);
        this.playerName = playerName;
        this.card = card;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
        this.moveDescription = moveDescription;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public Card getCard() {
        return card;
    }
    
    public int getOldPosition() {
        return oldPosition;
    }
    
    public int getNewPosition() {
        return newPosition;
    }
    
    public String getMoveDescription() {
        return moveDescription;
    }
}
