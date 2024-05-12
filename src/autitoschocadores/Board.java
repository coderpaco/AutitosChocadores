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
        //char[][] emptyCellBlock = autito.getEmptyCell;
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
            return board[row][col];
        } else {
            //System.out.println("error: null at " + row + " "  + col);
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
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    // CHECK AUTITO DIRECTIONS TO GET NEXT POSITIONS
public boolean checkAutitoInDirections(int row, int col) {
    int[] relevantDirections;
    Autito autito = getAutitoAt(row, col);
    String autitoSavedPos = autito.getCarDirection(); // Guarda la posición del autito

    // Determina las direcciones adyacentes relevantes según la orientación actual del autito
    switch (autitoSavedPos) {
        case "carUp":
            relevantDirections = new int[]{1, 2, 3}; // Derecha, abajo, izquierda
            break;
        case "carRight":
            relevantDirections = new int[]{2, 3, 0}; // Abajo, izquierda, arriba
            break;
        case "carDown":
            relevantDirections = new int[]{3, 0, 1}; // Izquierda, arriba, derecha
            break;
        case "carLeft":
            relevantDirections = new int[]{0, 1, 2}; // Arriba, derecha, abajo
            break;
        default:
            relevantDirections = new int[0]; // Sin direcciones relevantes
    }

    // Itera sobre las direcciones adyacentes relevantes y realiza las operaciones necesarias
    for (int direction : relevantDirections) {
        // Resto del código para verificar colisiones y realizar las operaciones necesarias en cada dirección
        
        // Get the initial position of the Autito
        int currentRow = row;
        int currentCol = col;

        // Move the Autito in the current direction until it reaches the end or finds another Autito
        while (true) {
            // Get the next position after moving in the current direction
            int[] nextPosition = getNextPosition(currentRow, currentCol, direction);
            int nextRow = nextPosition[0];
            int nextCol = nextPosition[1];

            // Check if the next position is within the bounds of the board
            if (!isValidPosition(nextRow, nextCol)) {
                break; // Reached the edge of the board
            }

            // Update the current position
            currentRow = nextRow;
            currentCol = nextCol;

            // Check if there's an Autito at the next position
            if (getAutitoAt(currentRow, currentCol) != null) {
                returnToOriginalPos(autitoSavedPos,row,col);
                return true; // There's an Autito in this direction
            }
          //  returnToOriginalPos(autitoSavedPos,row,col);
        }
    //    returnToOriginalPos(autitoSavedPos,row,col);
    }
    
    return false; // No Autito found in any direction
}



private void returnToOriginalPos(String pos, int row, int col){ //return cars to a saved pos
    Autito autito = getAutitoAt(row,col);
    switch (pos){
        case "carUp":
            autito.setOrientation(0);
            break;
        case "carRight":
            autito.setOrientation(1);
            break;
        case "carLeft":
            autito.setOrientation(3);
            break;
        case "carDown":
            autito.setOrientation(2);
            break;
        case "emptyCell":
            autito.setOrientation(4);
            break;        
    }
        
    
}

   // Helper method to get the next position based on the current position and direction
public int[] getNextPosition(int row, int col, int direction) {
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