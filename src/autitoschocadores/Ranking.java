package autitoschocadores;

import java.util.*;

public class Ranking {
    private ArrayList<Jugadores> players;

    public Ranking() {
        players = new ArrayList<>();
    }

    public void addPlayer(Jugadores player) {
        players.add(player);
    }

    // Sort players by points
    public void sortByPoints() {
        Collections.sort(players, Comparator.comparingInt(Jugadores::getPoints).reversed());
    }

    private void displayRanking(List<Jugadores> players) {
        System.out.println("+------------+----------+--------+---------+----------+---------+");
        System.out.println("| Alias      | Games    | Wins   | Losses  | Forfeits | Points  |");
        System.out.println("+------------+----------+--------+---------+----------+---------+");
    
        for (int i = 0; i < players.size(); i++) {
            Jugadores player = players.get(i);
            System.out.printf("| %-10s | %-8d | %-6d | %-7d | %-8d | %-7d |%n", 
                              player.getAlias(), player.getGames(), player.getWins(), 
                              player.getLosses(), player.getForfeits(), player.getPoints());
            System.out.println("+------------+----------+--------+---------+----------+---------+");
        }
    }
    
}