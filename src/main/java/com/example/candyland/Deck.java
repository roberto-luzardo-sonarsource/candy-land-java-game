package com.example.candyland;

import java.util.*;

/**
 * Represents the deck of cards in the Candy Land game.
 */
public class Deck {
    private final List<Card> cards;
    private final Random random;
    
    /**
     * Creates a new shuffled deck of Candy Land cards.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        this.random = new Random();
        initializeDeck();
        shuffle();
    }
    
    /**
     * Initializes the deck with all the cards.
     */
    private void initializeDeck() {
        // Add color cards (multiple of each color, some double)
        for (Color color : Color.values()) {
            // Add 8 single color cards for each color
            for (int i = 0; i < 8; i++) {
                cards.add(new Card(color, false));
            }
            // Add 2 double color cards for each color
            for (int i = 0; i < 2; i++) {
                cards.add(new Card(color, true));
            }
        }
        
        // Add special character cards
        cards.add(new Card("Plumpy"));
        cards.add(new Card("Mr. Mint"));
        cards.add(new Card("Jolly"));
        cards.add(new Card("Lord Licorice"));
        cards.add(new Card("Gramma Nutt"));
        cards.add(new Card("Princess Lolly"));
        cards.add(new Card("Queen Frostine"));
        cards.add(new Card("King Kandy"));
    }
    
    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards, random);
    }
    
    /**
     * Draws the top card from the deck.
     * 
     * @return the drawn card
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            initializeDeck();
            shuffle();
        }
        return cards.remove(cards.size() - 1);
    }
    
    /**
     * Gets the number of cards remaining in the deck.
     * 
     * @return the number of cards left
     */
    public int size() {
        return cards.size();
    }
    
    /**
     * Checks if the deck is empty.
     * 
     * @return true if the deck is empty
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}