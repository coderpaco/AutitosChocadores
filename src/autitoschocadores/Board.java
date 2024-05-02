package autitoschocadores;

//import java.util.*;

public class Board {
    private Autito[][] board;
    private int size;

    public Board(int size) {
        this.size = size;
        this.board = new Autito[size][size];
        initializeBoard();
    }

    // Initialize the board with empty cells
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = null;
            }
        }
    }

    // Place an Autito on the board at the specified position
    public void placeAutito(int row, int col, Autito autito) {
        if (isValidPosition(row, col)) {
            board[row][col] = autito;
        } else {
            System.out.println("Invalid position for placing Autito.");
        }
    }

    public Autito getAutitoAt(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
            return board[row][col];
        } else {
            return null;
        }
    }

    // Display the current state of the board
    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    displayAutito(board[i][j]);
                } else {
                    System.out.print("- "); // Empty cell
                }
            }
            System.out.println();
        }
    }

    // Display the representation of the Autito
    private void displayAutito(Autito autito) {
        char[][] representation = autito.getOrientation();
        for (char[] row : representation) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    // Check if the position is within the bounds of the board
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
// CHECK AUTITO DIRECTIONS TO GET NEXT POSITIONS
public boolean checkAutitoInDirections(int row, int col) {
    int[] directions = {0, 1, 2, 3}; // Directions: 0 (right), 1 (down), 2 (left), 3 (up)
    
    // Iterate over all directions
    for (int direction : directions) {
        Autito autito = getAutitoAt(row, col); // Get the Autito at the specified position
        
        // Rotate the Autito clockwise
        autito.rotateClockwise();
        
        // Move the Autito in the current direction until it reaches the end or finds another Autito
        while (true) {
            // Get the next position after moving in the current direction
            int[] nextPosition = getNextPosition(row, col, direction);
            int nextRow = nextPosition[0];
            int nextCol = nextPosition[1];
            
            // Check if the next position is out of bounds
            if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) {
                break; // Reached the edge of the board
            }
            
            // Check if there's an Autito at the next position
            if (getAutitoAt(nextRow, nextCol) != null) {
                return true; // There's an Autito in this direction
            }
            
            // Move to the next position
            row = nextRow;
            col = nextCol;
        }
    }
    
    return false; // No Autito found in any direction
}
    // Helper method to get the next position based on the current position and direction
private int[] getNextPosition(int row, int col, int direction) {
    int[] nextPosition = new int[2];
    
    switch (direction) {
        case 0: // Right
            nextPosition[0] = row;
            nextPosition[1] = col + 1;
            break;
        case 1: // Down
            nextPosition[0] = row + 1;
            nextPosition[1] = col;
            break;
        case 2: // Left
            nextPosition[0] = row;
            nextPosition[1] = col - 1;
            break;
        case 3: // Up
            nextPosition[0] = row - 1;
            nextPosition[1] = col;
            break;
        default:
            throw new IllegalArgumentException("Invalid direction");
    }
    
    return nextPosition;
}
}