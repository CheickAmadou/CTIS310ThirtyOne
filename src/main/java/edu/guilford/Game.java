package edu.guilford;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Game {
    private ArrayList <Player> players = new ArrayList<Player>();
    private Deck deck;
    private Stack<Card> discard = new Stack<Card>();
    private Queue<Card> stockpile = new LinkedList<>();
    public boolean knocked = false;
    public boolean gameOver = false;    

    // Constructor
    public Game(ArrayList<Player> players){
        this.players = players;
        this.deck = new Deck();
    }


    // Round One
    public void roundOne(){
         //Start Of Round, Shuffle Deck, Reset Hands, Clear Stockpile and Discard Pile
        deck = new Deck();
        deck.shuffle();
        for (Player player : players) {
            player.resetHand();
        }
        stockpile.clear();
        discard.clear();
        for(Player player : players){
            for (int i = 0; i < 3; i++) {
                player.getHand().addCard(deck.deal());
            }        
        }
        for(Card card : deck.getDeck()){
            stockpile.add(card);
        }
        discard.push(stockpile.remove());

        // Play the first round
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println("Pre-Play Round 1 "+player);
            processPlayerTurn(player);
            System.out.println("Post-Play Round 1 "+player);
        }
    }

    // subsequental Rounds
    public void subsequentRound(){
        int playerKnocked = 0;
        int maxValue = 0;
        int round = 1;
        while(!knocked&&maxValue<31){
            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                System.out.println("Pre-Play Other Round"+player);
                if (player.getHand().getTotalValue() >= 15+round) {
                    knocked = true;
                    System.out.println(player.getName() + " has knocked");
                    playerKnocked = i;
                    break;
                }
                processPlayerTurn(player);
                System.out.println("Post-Play Other Round"+player);
            }
            round++;
            /*
            //Check if the game is over due to Player Reaching 31
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).getHand().getTotalValue() >= 31){
                    break;
                }
            }
                 */
        }
        if(knocked){
        // Let the other players play their turn
        for(int i = playerKnocked + 1; i < players.size()+playerKnocked; i++){
            Player player = players.get(i % players.size());
            System.out.println("Pre-Play Finising up as Player Knocked "+player);
            processPlayerTurn(player);
            System.out.println("Post-Play Finising up as Player Knocked "+player);
        }
        //See who has lowest Value
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getHand().getTotalValue() < min){
                min = players.get(i).getHand().getTotalValue();
                minIndex = i;
            }
        }
        //Remove a life from the player with the lowest value or two lives if the player who knocked has the lowest value
        if(minIndex == playerKnocked){
            System.out.println(players.get(minIndex).getName() + " has the lowest value and knocked and loses two lives");
            players.get(minIndex).death();
            players.get(minIndex).death();
        } else {
            System.out.println(players.get(minIndex).getName() + " has the lowest value and loses a life");
            players.get(minIndex).death();
        }
        if(players.get(minIndex).getLives() <= 0){
            System.out.println(players.get(minIndex).getName() + " has no more Lives and is out of the game");
            players.remove(minIndex);
        }
        knocked = false;
        round = 1;

}
if(players.size() == 1){
    System.out.println(players.get(0).getName() + " has won the game");
    gameOver = true;
}
}
    // Play the game
    public void playGame(){      
        while(!gameOver){
            roundOne();
            subsequentRound();
        }
    }
    private void processPlayerTurn(Player player) {
        // Discard the suggested card from player's hand
        Card removed = player.disgardCard();
        player.getHand().removeCard(removed);
        discard.push(removed);
        
        // Replenish the stockpile from the discard pile if empty
        if (stockpile.isEmpty()) {
            // Move all cards from discard to stockpile
            for (Card card : discard) {
                stockpile.add(card);
            }
            discard.clear();
            // Preserve the top card by pushing it back to discard after removal from stockpile
            discard.push(stockpile.remove());
        }
        // Draw a new card from the stockpile and add it to the hand
        player.getHand().addCard(stockpile.remove());
    }
    
}
