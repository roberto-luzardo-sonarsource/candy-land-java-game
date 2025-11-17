package com.example.candyland.network;

/**
 * Message sent when the game is over.
 */
public class GameOverMessage extends GameMessage {
    private static final long serialVersionUID = 1L;
    
    private final String winnerName;
    private final int winnerPosition;
    
    public GameOverMessage(String winnerName, int winnerPosition) {
        super(MessageType.GAME_OVER);
        this.winnerName = winnerName;
        this.winnerPosition = winnerPosition;
    }
    
    public String getWinnerName() {
        return winnerName;
    }
    
    public int getWinnerPosition() {
        return winnerPosition;
    }
}
