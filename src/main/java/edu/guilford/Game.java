package edu.guilford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * The Game class encapsulates the logic and state for playing the card game.
 * It manages players, the deck, the discard and stockpile, and game-round behavior.
 *
 * <p>The game progresses through rounds until a win or a draw condition is met.</p>
 *
 * @author 
 * @version 1.0
 */
public class Game {

    // Instance variables
    private ArrayList<Player> players = new ArrayList<Player>();
    private Deck deck;
    private Stack<Card> discard = new Stack<Card>();
    private Queue<Card> stockpile = new LinkedList<>();
    public boolean knocked = false;
    public boolean gameOver = false;

    /**
     * Constructs a new Game instance with the specified list of players.
     *
     * @param players an ArrayList of Player objects participating in the game.
     */
    public Game(ArrayList<Player> players) {
        this.players = players;
        this.deck = new Deck();
    }

    /**
     * Sets up  first round of the game.
     *
     * <p>This method shuffles the deck, resets players' hands, clears the stockpile and discard,
     * deals cards to each player.</p>
     */
    public void roundOneSetup() {
        //Start Of Round, Shuffle Deck, Reset Hands, Clear Stockpile and Discard Pile
        deck = new Deck();
        deck.shuffle();
        // Shuffle the players to randomize the order and Turn Order for Each Round
        //Collections.shuffle(players);

        for (Player player : players) {
            player.resetHand();
        }
        stockpile.clear();
        discard.clear();
        for (Player player : players) {
            for (int i = 0; i < 3; i++) {
                player.getHand().addCard(deck.deal());
            }
        }
        for (Card card : deck.getDeck()) {
            stockpile.add(card);
        }
        discard.push(stockpile.remove());
    }

    /**
     * Plays the subsequent rounds after the first round.
     *
     * <p>This method continues rounds until a knocking event occurs or a player wins by hitting 31.
     * It processes each player's turn, handles knocking, and applies penalties for lowest hand totals.
     * The method resets round counters and checks for win/draw conditions.</p>
     */
    public void subsequentRound() {
        int playerKnocked = 0;
        int round = 1;
        while (!knocked) {
            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                // If a player hits 31, they win the round.
                // All other players lose a life and then we end the round.
                if (player.getHand().getTotalValue() == 31) {
                    System.out.println(player.getName() + " has hit 31!");
                    // Deduct a life from every other player.
                    for (Player other : players) {
                        if (!other.equals(player)) {
                            System.out.println(other.getName() + " loses a life because " + player.getName() + " reached 31.");
                            other.death();
                        }
                    }
                    players.removeIf(p -> p.getLives() <= 0);
                    return; // exit the method to end the round/game
                }
                if (player.getHand().getTotalValue() >= knockThreshold(round)) {
                    knocked = true;
                    System.out.println(player.getName() + " has knocked");
                    playerKnocked = i;
                    break;
                }
                if (round == 1){
                    System.out.println("Initial Hand: ");
                    System.out.println(player);
                }
                    processPlayerTurn(player);
                    System.out.println("Round " + round + ": ");
                    System.out.println(player);
                }
                round++;
            }

        if (knocked) {
            // Let the other players play their turn
            for (int i = playerKnocked + 1; i < players.size() + playerKnocked; i++) {
                Player player = players.get(i % players.size());
                //Can choose not to Play if they like their hand
                //Hypothetical hand based on top of discard pile
                //If the player has a hand that is greater than the knock threshold and the card on top of the discard pile would not increase the value of their hand
                //Then they can choose to not play
               Hand Hypothetical = player.getHand();
               Hypothetical.removeCard(player.disgardCard());
               if(!discard.isEmpty()){
                Hypothetical.addCard(discard.peek());
               }
                if(player.getHand().getTotalValue() > knockThreshold(round) && player.getHand().getTotalValue() > Hypothetical.getTotalValue()){
                    System.out.println(player.getName() + " has a hand greater than the knock threshold and the card on top of the discard pile would not increase the value of their hand, so they choose not to play");    
                }
                else{
                    processPlayerTurn(player);
                }
                System.out.println("Knocked Round " + round + ": ");
                System.out.println(player);
            }
            // Determine the minimum hand total value.
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < players.size(); i++) {
                int total = players.get(i).getHand().getTotalValue();
                if (total < min) {
                    min = total;
                }
            }
            //  Each player whose total equals the minimum loses a life or two if they knocked.
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getHand().getTotalValue() == min) {
                    if (i == playerKnocked) {
                        System.out.println(players.get(i).getName() + " had the lowest value, knocked and loses two lives");
                        players.get(i).death();
                        players.get(i).death();
                    } else {
                        System.out.println(players.get(i).getName() + " had the lowest value and loses a life");
                        players.get(i).death();
                    }
                    if (players.get(i).getLives() <= 0) {
                        System.out.println(players.get(i).getName() + " has no more Lives and is out of the game");
                        players.remove(i);
                    }
                }
            }
            // Reset the knocked flag and round counter
            knocked = false;
            round = 1;

        }
        if (players.size() == 1) {
            System.out.println(players.get(0).getName() + " has won the game");
            gameOver = true;
        }
        if (players.size() == 0) {
            System.out.println("Bad Luck, No one has won the game");
            gameOver = true;
        }
    }

    /**
     * Continuously plays rounds of the game until the game is over.
     *
     * <p>This method repeatedly calls {@code roundOne()} and {@code subsequentRound()}
     * until {@code gameOver} is set to true.</p>
     */
    public void playGame() {
        while (!gameOver) {
            roundOneSetup();
            subsequentRound();
        }
    }

    /**
     * Processes a single player's turn.
     *
     * <p>This method handles discarding a card based on a strategy,
     * replenishing the stockpile/discard pile as needed,
     * and then acquiring a new card either from the discard pile or stockpile.</p>
     *
     * @param player the Player whose turn is to be processed.
     */
    private void processPlayerTurn(Player player) {

        // Discard the suggested card from player's hand
        Card removed = player.disgardCard();
        player.getHand().removeCard(removed);
        // If the card is greater than 5, add it to the stockpile as we don't want other players getting it
        //, otherwise add it to the discard pile
        if (removed.getRank().getValue() > 5) {
            //Add the removed card to bottom of StockPile
            stockpile.add(removed);
            System.out.println(player.getName() + " discarded " + removed + " to the stockpile");
        } else {
            // Add the removed card to the discard pile
            discard.push(removed);
            System.out.println(player.getName() + " discarded " + removed + " to the discard pile");
        }
        // Replenish the stockpile from the discard pile if empty
        if (stockpile.isEmpty()) {
            // Move all cards from discard to stockpile
            for (Card card : discard) {
                stockpile.add(card);
            }
            discard.clear();
            // Preserve the top card by pushing it back to discard after removal from stockpile
            discard.push(stockpile.remove());
            System.out.println("Stockpile is empty, so we took all the cards from the discard pile and added them into the stockpile");
        }
        // Replenish the discard pile from the stockpile if empty, Take the top card from the Stock pile
        if(discard.isEmpty()){
            discard.push(stockpile.remove());
            System.out.println("Discard pile is empty, so we took the top card from the stockpile");
        }
        Card discardTop = discard.peek();
        System.out.println("Top of Discard Pile: " + discardTop);
        //Gets Card from Discard Pile if the card is greater than 5 or the suit is the best suit in the Hand, or if not then we will take
        //our chances at a card from StockPile
        if (discardTop.getRank().getValue() > 5 || discardTop.getSuit() == player.bestSuit()) {
            player.getHand().addCard(discard.pop());
            System.out.println(player.getName() + " picked up " + player.getHand().getCard(player.getHand().size() - 1) + " from the discard pile");
        } else {
            player.getHand().addCard(stockpile.remove());
            System.out.println(player.getName() + " picked up " + player.getHand().getCard(player.getHand().size() - 1) + " from the stockpile");
        }
    }

    /**
     * Calculates the knock threshold for a given round.
     *
     * <p>This method returns a threshold value that increases linearly for the first three rounds,
     * then decreases linearly in subsequent rounds. The threshold determines when a player may knock.</p>
     *
     * @param round the current round number.
     * @return an integer representing the knock threshold for the given round.
     */
    private int knockThreshold(int round) {
        if (round <= 3) {
            // Increase threshold by 3 each round for first three rounds.
            return 18 + (round - 1) * 3;
        } else {
            // After round 3, decrease threshold by 3 each round.
            return 24 - (round - 3) * 3;
        }
    }

}
