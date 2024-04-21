package autitoschocadores;

public class Autito { // It's better to start class names with a capital letter
    private int carPosition;
    private char[][] carUp = {
            {'|', ' ', 'o', 'o', ' ', '|'},
            {'|', ' ', '*', '*', ' ', '|'},
            {'|', ' ', '*', '*', ' ', '|'},
            {'|', ' ', '*', '*', ' ', '|'}
    };
    private char[][] carDown = {
            {'|', ' ', '*', '*', ' ', '|'},
            {'|', ' ', '*', '*', ' ', '|'},
            {'|', ' ', '*', '*', ' ', '|'},
            {'|', ' ', 'o', 'o', ' ', '|'}
    };
    private char[][] carLeft = {
            {'|', ' ', ' ', ' ', ' ', '|'},
            {'|', 'o', '*', '*', '*', '|'},
            {'|', 'o', '*', '*', '*', '|'},
            {'|', ' ', ' ', ' ', ' ', '|'},
    };
    private char[][] carRight = {
            {'|', ' ', ' ', ' ', ' ', '|'},
            {'|', '*', '*', '*', 'o', '|'},
            {'|', '*', '*', '*', 'o', '|'},
            {'|', ' ', ' ', ' ', ' ', '|'},
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
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
}
