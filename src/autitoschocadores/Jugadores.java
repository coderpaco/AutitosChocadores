package autitoschocadores;

public class Jugadores {
    private String alias; //no need to ask for player name just username?
    private String nombre;
    private int edad;
    private int partidas;
    private int ganadas;
    private int perdidas;
    private int abandonadas;
    private int puntaje;

    public String getNombre(){
        return nombre;
    }
    
    public int getEdad(){
        return edad;
    }
    
    public String getAlias() {
        return alias;
    }

    public int getPartidas() {
        return partidas;
    }

    public int getGanadas() {
        return ganadas;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public int getAbandonadas() {
        return abandonadas;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setNombre(String nombre){
        this.nombre = alias;
    }
    
    public void setEdad(int edad){
        this.edad = edad;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPartidas(int partidas) {
        this.partidas = partidas;
    }

    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }

    public void setPerdidas(int perdidas) {
        this.perdidas = perdidas;
    }

    public void setAbandonadas(int abandonadas) {
        this.abandonadas = abandonadas;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Jugadores(String nombre, int edad,String alias, int partidas, int ganadas, int perdidas, int abandonadas, int puntaje) {
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
        this.partidas = partidas;
        this.ganadas = ganadas;
        this.perdidas = perdidas;
        this.abandonadas = abandonadas;
        this.puntaje = puntaje;
    }
    
    @Override
    public String toString()
    {
        return " | " + this.getAlias()+ " | " + this.getPartidas() + " | " 
                + this.getGanadas() + " | " + this.getPerdidas() + " | "
                + this.getAbandonadas() + " | " + this.getPuntaje() + " | ";
    }
}
