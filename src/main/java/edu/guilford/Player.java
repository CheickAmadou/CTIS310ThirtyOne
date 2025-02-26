package edu.guilford;

public class Player {
    private String name;
    private Hand hand;
    private int lives;

    //Constructor
    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.lives = 3;
    }

    //Getters and Setters 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    //Methods
    public void death() {
        lives--;
    }
    //Reset the player's hand to empty
    public void resetHand() {
        hand.reset();
    }

    //Method that calculates best card to disgard according to the rules of the game
    public Card disgardCard(){
        // Get the array of total values per suit
        int[] suitTotals = hand.getTotalValueArray();
    
        // Determine the suit with the lowest total value
        int minIndex = 0;
        for (int i = 1; i < suitTotals.length; i++) {
            if(suitTotals[i] < suitTotals[minIndex]) {
                minIndex = i;
            }
        }
        
        // Iterate over the hand and find the card from the suit minIndex that has the lowest rank value
        Card minCard = hand.getCard(0);
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.getCard(i);
            if(card.getSuit().ordinal() == minIndex) {
                if(card.getRank().getValue() < minCard.getRank().getValue()){
                    minCard = card;
                }
            }
        }
        return minCard;
    }

    //Give me a toString method that returns the player's name and the number of lives they have left and cards in hand
    public String toString() {
        return name + " has " + lives + " lives left and has the following cards in hand: " + hand.toString();
    }

    


}
