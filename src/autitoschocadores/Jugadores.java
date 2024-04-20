package autitoschocadores;

public class Jugadores {
    private String alias; // no need to ask for player name just username?
    private String name;
    private int age;
    private int games;
    private int wins;
    private int losses;
    private int forfeits;
    private int points;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAlias() {
        return alias;
    }

    public int getGames() {
        return games;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getForfeits() {
        return forfeits;
    }

    public int getPoints() {
        return points;
    }

    public void setName(String playerName) {
        this.name = playerName;
    }

    public void setEdad(int playerAge) {
        this.age = playerAge;
    }

    public void setAlias(String playerAlias) {
        this.alias = playerAlias;
    }

    public void setPartidas(int playerGames) {
        this.games = playerGames;
    }

    public void setGanadas(int playerWins) {
        this.wins = playerWins;
    }

    public void setPerdidas(int playerLosses) {
        this.losses = playerLosses;
    }

    public void setAbandonadas(int playerForfeits) {
        this.forfeits = playerForfeits;
    }

    public void setPuntaje(int playerPoints) {
        this.points = playerPoints;
    }

    public Jugadores(String playerName, int playerAge, String playerAlias, int playerGames, int playerWins,
            int playerLosses, int playerForfeits, int playerPoints) {
        this.name = playerName;
        this.age = playerAge;
        this.alias = playerAlias;
        this.games = playerGames;
        this.wins = playerWins;
        this.losses = playerLosses;
        this.forfeits = playerForfeits;
        this.points = playerPoints;
    }

    @Override
    public String toString() {
        return " | " + this.getAlias() + " | " + this.getGames() + " | "
                + this.getWins() + " | " + this.getLosses() + " | "
                + this.getForfeits() + " | " + this.getPoints() + " | ";
    }
}
