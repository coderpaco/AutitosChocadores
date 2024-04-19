package autitoschocadores;

public class Autito {  // It's better to start class names with a capital letter
    private int autitoPosition;
    private char[][] autitoUp = {
        {'o', 'o'},
        {'*', '*'},
        {'*', '*'},
        {'*', '*'}
    };
    private char[][] autitoDown = {
        {'*', '*'},
        {'*', '*'},
        {'*', '*'},
        {'o', 'o'}
    };
    private char[][] autitoLeft = {
        {'o', '*', '*', '*'},
        {'o', '*', '*', '*'}
    };
    private char[][] autitoRight = {
        {'*', '*', '*', 'o'},
        {'*', '*', '*', 'o'}
    };

    // Assuming Tablero is your board class, it might be better not used as an inherited class unless specifically needed.
    // private Tablero board; 

    // Constructor
    public Autito(int startPosition) {
        this.autitoPosition = startPosition;
        // this.board = board;  // If you need to reference the board
    }

    // Accessor methods
    public char[][] getAutitoUp() {
        return autitoUp;
    }

    public char[][] getAutitoDown() {
        return autitoDown;
    }

    public char[][] getAutitoLeft() {
        return autitoLeft;
    }

    public char[][] getAutitoRight() {
        return autitoRight;
    }

    public int getAutitoPosition() {
        return autitoPosition;
    }

public static void main(String() args){
    System.out.println(autitoUp);
}

@Override
public String toString(){
    
}
    // You may need additional methods to handle the movement or behavior of autito
}
