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
