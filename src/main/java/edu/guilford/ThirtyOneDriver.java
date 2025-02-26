package edu.guilford;

import java.util.ArrayList;

public class ThirtyOneDriver {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));
        Game game1 = new Game(players);
        game1.playGame();
      
    }
}