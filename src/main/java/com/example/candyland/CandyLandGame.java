package com.example.candyland;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class for the Candy Land game.
 * Manages the game flow and player interactions.
 */
public class CandyLandGame {
    private static final Logger logger = LoggerFactory.getLogger(CandyLandGame.class);
    private final List<Player> players;
    private final Board board;
    private final Deck deck;
    private int currentPlayerIndex;
    private Player winner;
    private final Scanner scanner;
    
    /**
     * Creates a new Candy Land game.
     */
    public CandyLandGame() {
        this.players = new ArrayList<>();
        this.board = new Board();
        this.deck = new Deck();
        this.currentPlayerIndex = 0;
        this.winner = null;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Sets up the game with players.
     */
    public void setupGame() {
        logger.info("Welcome to Candy Land!");
        logger.info("Enter the number of players (1-3):");
        
        int numPlayers;
        do {
            logger.info("Number of players: ");
            numPlayers = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } while (numPlayers < 1 || numPlayers > 3);
        
        for (int i = 0; i < numPlayers; i++) {
            logger.info("Enter name for Player {}: ", (i + 1));
            String name = scanner.nextLine();
            players.add(new Player(name));
        }
        
        logger.info("Game setup complete!");
        displayPlayers();
    }
    
    /**
     * Starts and runs the main game loop.
     */
    public void playGame() {
        while (winner == null) {
            playTurn();
            if (winner != null) {
                logger.info("🎉 {} wins the game! 🎉", winner.getName());
                break;
            }
            nextPlayer();
        }
    }
    
    /**
     * Plays a single turn for the current player.
     */
    private void playTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        logger.info("{}'s turn!", currentPlayer.getName());
        logger.info("Current position: {}", currentPlayer.getPosition());
        logger.info("Press Enter to draw a card...");
        scanner.nextLine();
        
        Card drawnCard = deck.drawCard();
        logger.info("Card drawn: {}", drawnCard);
        
        int newPosition = calculateNewPosition(currentPlayer, drawnCard);
        currentPlayer.setPosition(newPosition);
        
        logger.info("{} moves to position {}", currentPlayer.getName(), newPosition);
        
        if (board.isWinningSpace(newPosition)) {
            winner = currentPlayer;
        }
    }
    
    /**
     * Calculates the new position for a player based on the drawn card.
     * 
     * @param player the player making the move
     * @param card the card that was drawn
     * @return the new position
     */
    public int calculateNewPosition(Player player, Card card) {
        int currentPosition = player.getPosition();
        
        if (card.isSpecialCharacter()) {
            // Move to special character location
            int specialPosition = board.getSpecialCharacterPosition(card.getSpecialCharacter());
            return Math.max(currentPosition, specialPosition); // Never move backward
        } else {
            // Move to next space of the card's color
            return board.findNextColorSpace(currentPosition, card.getColor(), card.isDouble());
        }
    }
    
    /**
     * Moves to the next player.
     */
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    
    /**
     * Gets the game board.
     * 
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }
    
    /**
     * Gets the card deck.
     * 
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }
    
    /**
     * Sets the winner of the game.
     * 
     * @param winner the winning player
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    
    /**
     * Displays all players and their positions.
     */
    private void displayPlayers() {
        logger.info("Current player positions:");
        for (Player player : players) {
            logger.info("{}", player);
        }
    }
    
    /**
     * Gets the current player.
     * 
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    
    /**
     * Gets the winner of the game.
     * 
     * @return the winner, or null if game is still ongoing
     */
    public Player getWinner() {
        return winner;
    }
    
    /**
     * Gets the list of players.
     * 
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
    
    /**
     * Adds a player to the game.
     * 
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        if (players.size() < 3) {
            players.add(player);
        }
    }
    
    /**
     * Adds a player to the game by name.
     * 
     * @param name the player's name
     * @return the newly created Player object
     */
    public Player addPlayer(String name) {
        Player player = new Player(name);
        addPlayer(player);
        return player;
    }
    
    /**
     * Main method to run the game.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        CandyLandGame game = new CandyLandGame();
        game.setupGame();
        game.playGame();
        
        logger.info("Thanks for playing Candy Land!");
    }
}