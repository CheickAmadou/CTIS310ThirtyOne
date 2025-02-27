package edu.guilford;

import java.util.ArrayList;

/**
 * Represents a hand of cards held by a player.
 * Provides methods for adding, removing, and evaluating cards in the hand.
 * 
 * @author Cheick Amadou
 * @version 1.0
 */
public class Hand {
    private ArrayList<Card> hand;

    /**
     * Constructs a new Hand object and initializes the card list.
     */
    public Hand() {
        hand = new ArrayList<Card>();
    }

    /**
     * Returns the list of cards in the hand.
     * 
     * @return an ArrayList of Card objects representing the hand.
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Adds a card to the hand.
     * 
     * @param card the Card to be added.
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Removes the specified card from the hand.
     * 
     * @param card the Card to be removed.
     */
    public void removeCard(Card card) {
        hand.remove(card);
    }

    /**
     * Clears the hand, removing all cards.
     */
    public void reset() {
        hand.clear();
    }

    /**
     * Returns the number of cards in the hand.
     * 
     * @return the size of the hand.
     */
    public int size() {
        return hand.size();
    }

    /**
     * Retrieves the card at the specified index in the hand.
     * 
     * @param index the index of the card to retrieve.
     * @return the Card at the specified index.
     */
    public Card getCard(int index) {
        return hand.get(index);
    }

    /**
     * Calculates an array of total values for each suit in the hand.
     * Each array element corresponds to a suit's cumulative card value.
     * 
     * @return an int array where each element is the total value of cards for a suit.
     */
    public int[] getTotalValueArray() {
        int[] values = new int[Card.Suit.values().length];
        for (Card.Suit suit : Card.Suit.values()) {
            values[suit.ordinal()] = 0;
            for (Card card : hand) {
                if (card.getSuit() == suit) {
                    // add the value of the card to the value of the suit
                    switch (card.getRank()) {
                        case ACE:
                            values[suit.ordinal()] += card.getRank().getValue();
                            break;
                        case TWO:
                            values[suit.ordinal()] += card.getRank().getValue();
                            break;
                        case THREE:
                            values[suit.ordinal()] += card.getRank().getValue();
                            break;
                        case FOUR:
                            values[suit.ordinal()] += card.getRank().getValue();
                            break;
                        case FIVE:
                            values[suit.ordinal()] += card.getRank().getValue();
                            break;
                        case SIX:
                            values[suit.ordinal()] += card.getRank().getValue();
                            break;
                        case SEVEN:
                            values[suit.ordinal()] += card.getRank().getValue();
                            break;
                        case EIGHT:
                            values[suit.ordinal()] += card.getRank().getValue();
                            break;
                        case NINE:
                            values[suit.ordinal()] += card.getRank().getValue();
                            break;
                        case TEN:
                        case JACK:
                        case QUEEN:
                        case KING:
                            values[suit.ordinal()] += 10;
                            break;
                    }

                }
            }
        }
        return values;
    }

    /**
     * Calculates the total value of the hand by determining the maximum
     * cumulative value among all suits.
     * 
     * @return the highest total value from the suit totals.
     */
    public int getTotalValue(){
        int values[] = getTotalValueArray();
        int maxValue = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > maxValue){
                maxValue = values[i];
            }
        }
        return maxValue;
    }

    /**
     * Returns a string representation of the hand,
     * listing each card on a new line.
     * 
     * @return a String that represents the hand.
     */
    public String toString() {
        String handString = "";
        for (Card card : hand) {
            handString += card.toString() + "\n";
        }
        return handString;
    }

}
