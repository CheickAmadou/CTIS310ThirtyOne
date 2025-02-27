package edu.guilford;

import java.util.Random;

/**
 * The Card class represents a playing card with a suit and a rank.
 * It implements Comparable to allow cards to be sorted based on suit or rank.
 *
 *
 * @author 
 * @version 1.0
 */
public class Card implements Comparable<Card> {

    /**
     * Represents the four suits in a deck of cards.
     */
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    /**
     * Represents the ranks of the cards.
     * Each rank has an associated integer value.
     */
    public enum Rank {
        ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

        private final int value;

        /**
         * Constructs a Rank with the specified integer value.
         *
         * @param value the integer value associated with the rank.
         */
        Rank(int value) {
            this.value = value;
        }

        /**
         * Returns the integer value of the rank.
         *
         * @return the value of the rank.
         */
        public int getValue() {
            return value;
        }
    }

    // instance variables

    /**
     * The suit of the card.
     */
    private Suit suit;
    
    /**
     * The rank of the card.
     */
    private Rank rank;

    /**
     * Constant indicating that cards should be sorted by suit.
     */
    public static final int SORT_BY_SUIT = 1;

    /**
     * Constant indicating that cards should be sorted by rank.
     */
    public static final int SORT_BY_RANK = 2;
    
    /**
     * Determines the method to sort cards.
     * Can be set to {@code SORT_BY_SUIT} or {@code SORT_BY_RANK}.
     */
    private static int sortMethod = SORT_BY_RANK;

    // constructors

    /**
     * Constructs a Card with the specified suit and rank.
     *
     * @param suit the suit of the card.
     * @param rank the rank of the card.
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Constructs a random Card.
     * The suit and rank are chosen randomly.
     */
    public Card() {
        // random Card
        Random rand = new Random();
        int suit = rand.nextInt(Suit.values().length);
        int rank = rand.nextInt(Rank.values().length);
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    // getters

    /**
     * Returns the suit of the card.
     *
     * @return the suit.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the rank of the card.
     *
     * @return the rank.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Sets the method used for sorting cards.
     *
     * @param sortMethod the sort method to use, either {@code SORT_BY_SUIT} or {@code SORT_BY_RANK}.
     */
    public static void setSortMethod(int sortMethod) {
        Card.sortMethod = sortMethod;
    }

    // toString

    /**
     * Returns a string representation of the card, e.g., "ACE of SPADES".
     *
     * @return a string describing the card.
     */
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Compares this card with another card.
     * The comparison is based on the current sort method (either by suit or rank).
     *
     * @param otherCard the other card to compare against.
     * @return a negative integer, zero, or a positive integer as this card is less than, equal to, or greater than the specified card.
     */
    @Override
    public int compareTo(Card otherCard) {
        if (sortMethod == SORT_BY_SUIT) {
            if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                return 1;
            } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                return -1;
            } else {
                if (this.rank.ordinal() > otherCard.rank.ordinal()) {
                    return 1;
                } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
                    return -1;
                }
            }
        } else {
            if (this.rank.ordinal() > otherCard.rank.ordinal()) {
                return 1;
            } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
                return -1;
            } else {
                if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                    return 1;
                } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                    return -1;
                }
            }
        }

        return 0;
    }

}