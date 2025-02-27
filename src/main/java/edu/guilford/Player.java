package edu.guilford;

/**
 * The Player class represents a participant in the game.
 * A Player has a name, a hand of cards, and a number of lives.
 * 
 * @author Cheick Amadou
 * @version 1.0
 */
public class Player {
    private String name;
    private Hand hand;
    private int lives;

    /**
     * Constructs a new Player with the specified name.
     * Initializes the player's hand and sets the starting lives to 3.
     * 
     * @param name the name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.lives = 3;
    }

    /**
     * Returns the player's name.
     * 
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name.
     * 
     * @param name the new name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the player's hand.
     * 
     * @return the hand of the player.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Sets the player's hand.
     * 
     * @param hand the new hand of cards for the player.
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     * Returns the number of lives the player has.
     * 
     * @return the number of lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets the number of lives the player has.
     * 
     * @param lives the new number of lives.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Reduces the player's lives by one.
     */
    public void death() {
        lives--;
    }

    /**
     * Resets the player's hand to empty.
     */
    public void resetHand() {
        hand.reset();
    }

    /**
     * Calculates and returns the best card to discard according to the game rules.
     * It determines the suit with the lowest total value and then returns the card
     * from that suit with the lowest rank value.
     * 
     * @return the Card selected for discard.
     */
    public Card disgardCard() {
        // Get the array of total values per suit
        int[] suitTotals = hand.getTotalValueArray();

        // Determine the suit with the lowest total value
        int minIndex = 0;
        for (int i = 1; i < suitTotals.length; i++) {
            if (suitTotals[i] < suitTotals[minIndex]) {
                minIndex = i;
            }
        }

        // Iterate over the hand and find the card from the suit minIndex that has the lowest rank value
        Card minCard = hand.getCard(0);
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.getCard(i);
            if (card.getSuit().ordinal() == minIndex) {
                if (card.getRank().getValue() < minCard.getRank().getValue()) {
                    minCard = card;
                }
            }
        }
        return minCard;
    }

    /**
     * Determines and returns the best suit based on the player's hand.
     * It calculates the total value per suit and returns the suit with the highest total value.
     * 
     * @return the best Card.Suit based on the player's hand.
     */
    public Card.Suit bestSuit() {
        // Get the array of total values per suit
        int[] suitTotals = hand.getTotalValueArray();

        // Determine the suit with the highest total value
        int maxIndex = 0;
        for (int i = 1; i < suitTotals.length; i++) {
            if (suitTotals[i] > suitTotals[maxIndex]) {
                maxIndex = i;
            }
        }
        return Card.Suit.values()[maxIndex];
    }

    /**
     * Returns a string representation of the player, including the player's name,
     * the number of lives left, and the cards in the player's hand.
     * 
     * @return a String describing the player.
     */
    public String toString() {
        return name + " has " + lives + " lives left and has the following cards in hand:\n" + hand.toString();
    }
}
