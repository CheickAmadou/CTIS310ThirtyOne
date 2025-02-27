package edu.guilford;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * The Deck class represents a deck of playing cards.
 * It provides methods to build, shuffle, pick, and deal cards.
 * 
 * @author Cheick Amadou
 * @version 1.0
 */
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private final Random rand = new Random();

    /**
     * Constructs a Deck and builds its cards.
     */
    public Deck() {
        build();
    }

    /**
     * Returns the list of cards in the deck.
     *
     * @return an ArrayList of Card objects representing the deck.
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    /**
     * Clears the deck by removing all cards.
     */
    public void clear() {
        deck.clear();
    }

    /**
     * Builds the deck by creating one card for each suit and rank.
     */
    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck using a random number generator.
     */
    public void shuffle() {
       Collections.shuffle(deck, rand);
    }

    /**
     * Picks and removes the card at the specified index.
     *
     * @param i the index of the card to pick.
     * @return the Card that was removed from the deck.
     */
    public Card pick(int i) {
        Card picked = deck.remove(i);
        return picked;
    }

    /**
     * Deals (removes and returns) the top card of the deck.
     *
     * @return the top Card from the deck.
     */
    public Card deal() {
        return deck.remove(0);
    }

    /**
     * Returns the number of cards remaining in the deck.
     *
     * @return the size of the deck.
     */
    public int size() {
        return deck.size();
    }

    /**
     * Returns a string representation of the deck.
     *
     * @return a String of all cards in the deck separated by newlines.
     */
    public String toString() {
        String deckString = "";
        for (Card card : deck) {
            deckString += card.toString() + "\n";
        }
        return deckString;
    }
}
