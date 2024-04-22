private void displayRandomCar() {
    char[][] randomAutito = getRandomCar();
    for (char[] row : randomAutito) {
        for (char c : row) {
            System.out.print(c);
        }
        System.out.println();
    }
}*/

private void printColoredSymbol(String cell) {
    // TODO: make it print multiple lines in the cell for the car?
    String representation = autito.getRepresentation;
    String colorCode = getColorCode(parts[1]);
    System.out.print("  " + colorCode + parts[0] + "\u001B[0m"); // default color
    System.out.print("   |");
}

private String getColorCode(String symbol) {
    // Return ANSI escape code for color based on the symbol
    // TODO: make the headlights yellow, \x1b[43m
    // https://talyian.github.io/ansicolors/

    switch (symbol) {
        case "R":
            return "\u001B[31m"; // Red
        case "A":
            return "\u001B[34m"; // Blue
        default:
            return ""; // No color (default)
    }
}

/*  private char getRandomSymbol() {
    // Return a random symbol as a car direction placeholder ('^', 'v', '<-', '->')
    // TODO: make multiple lines // specify headlights/rest of car
    char[] symbols = { '^', 'v', '<', '>' };
    return symbols[random.nextInt(symbols.length)];
}*/

private char[][] getRandomCar() {
    Autito autito = new Autito(0); // Assumes position is utilized somehow
    char[][][] autitos = { autito.getCarUp(), autito.getCarDown(), autito.getCarLeft(), autito.getCarRight() }; 
    return autitos[random.nextInt(autitos.length)];
}

private char getRandomColor() {
    // Return a random color for the car (red or blue)
    return random.nextBoolean() ? 'R' : 'A';
}

private void configureCustomBoard() {
    System.out.println("Ingrese el tamaño del tablero (entre 5x5 y 7x7):");
    m = getValidInput(5, 7); // Validar el tamaño del tablero
    System.out.println("Ingrese la cantidad de autos (entre 3 y 12):");
    n = getValidInput(3, 12); // Validar la cantidad de autos
    // Inicializa el tablero con el tamaño seleccionado
     board = new Autito[m][m];

  //  String[] carPositions = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Ingrese la posición y dirección del auto " + (i + 1) + " (formato A12 2):");
            carPositions[i] = scanner.nextLine();
            placeCar(carPositions[i]);
            }

            //Save game configuration
            customGameConfig = new GameConfiguration(m, n, carPositions);
            //Display the custom board configuration
        displayGameBoard();
    }



//TO SAVE THE GAME CONFIGURATION
public class GameConfiguration {
    private int boardSize;
    private int numCars;
    private String[] carPositions;

    public GameConfiguration(int boardSize, int numCars, String[] carPositions) {
        this.boardSize = boardSize;
        this.numCars = numCars;
        this.carPositions = carPositions;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getNumCars() {
        return numCars;
    }

    public String[] getCarPositions() {
        return carPositions;
    }
}

private void generateRandomTable(int numRowsCols, int numCars) {
    // generate a random table based on the rows/cols
    m = numRowsCols;// board is square so we can use n here in both cases
    n = numCars;

    /*
     * TODO:
     * 1. generate random pos on the board
     * 2. place a random car position there
     * 3. verify theres a possible move, if not redo process?
     */

     board = new Autito[m][m];
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < m; j++) {
            board[i][j] = getRandomCar() + "" + getRandomColor();
        }
    }
}
