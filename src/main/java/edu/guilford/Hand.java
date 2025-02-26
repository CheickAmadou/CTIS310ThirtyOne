package edu.guilford;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public void reset() {
        hand.clear();
    }

    public int size() {
        return hand.size();
    }

    public Card getCard(int index) {
        return hand.get(index);
    }

    // Calculate an Array value of the hand
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

    // Calculate the total value of the hand
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



    // Override toString method
    public String toString() {
        String handString = "";
        for (Card card : hand) {
            handString += card.toString() + "\n";
        }
        return handString;
    }

}
