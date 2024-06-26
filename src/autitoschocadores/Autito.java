package autitoschocadores;

public class Autito { // It's better to start class names with a capital letter
    private int carPosition;
    private final char[][] carUp = {
            {' ', 'o', 'o', ' '},
            {' ', '*', '*', ' '},
            {' ', '*', '*', ' '},
            {' ', '*', '*', ' '},
    };
    private final char[][] carDown = {
            {' ', '*', '*', ' '},
            {' ', '*', '*', ' '},
            {' ', '*', '*', ' '},
            {' ', 'o', 'o', ' '},
    };
    private final char[][] carLeft = {
            {' ', ' ', ' ', ' '},
            {'o', '*', '*', '*'},
            {'o', '*', '*', '*'},
            {' ', ' ', ' ', ' '},
    };
    private final char[][] carRight = {
            {' ', ' ', ' ', ' '},
            {'*', '*', '*', 'o'},
            {'*', '*', '*', 'o'},
            {' ', ' ', ' ', ' '},
    };
    private final char[][] emptyCell = {
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '}
};

    // Assuming Tablero is your board class, it might be better not used as an
    // inherited class unless specifically needed.
    // private Tablero board;

    private char[][] currentOrientation;

    // Accessor methods
    public char[][] getCarUp() {
        return carUp;
    };

    public char[][] getCarDown() {
        return carDown;
    };

    public char[][] getCarLeft() {
        return carLeft;
    };

    public char[][] getCarRight() {
        return carRight;
    };

    public int getCarPosition() {
        return carPosition;
    };

    public Autito(int startPosition) {
        this.carPosition = startPosition;
        this.currentOrientation = carUp; // Default orientation
    }

    public char[][] getCurrentOrientation() {
        return currentOrientation;
    }

    public void setOrientation(int direction) {
        switch (direction) {
            case 0: // Up
                currentOrientation = carUp;
                break;
            case 1: // Right
                currentOrientation = carRight;
                break;
            case 2: // Down
                currentOrientation = carDown;
                break;
            case 3: // Left
                currentOrientation = carLeft;
                break;
            case 4: // empty
                currentOrientation = emptyCell;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
}
