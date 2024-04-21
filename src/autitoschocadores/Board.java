package autitoschocadores;

import java.util.*;

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
        char[][] representation = autito.getCurrentOrientation();
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
}