package autitoschocadores;

import java.util.*;
/*TODO:
    1. change row numbers to print letters (done)
    2. when a move input is made (A12, where A is row), be able to compute 
            A= row 1, B= row 2, etc.
    3. board is mxm (5x5) but is reallt 20x20 (5x4+5x4) because each cell is 4x4 to accomodate car up/down/left/right
*/

public class AutitosChocadores {

    public static Scanner scan = new Scanner(System.in);
    public static Random random = new Random();

    public static String[][] board;
    public static int m = 0; // board size (mxm)
    public static int n = 0; // amt of cars (x=n;)

    public static int propioM = 0;
    public static int propioN = 0;

    public static void main(String[] args) {
        AutitosChocadores game = new AutitosChocadores();// select option (al azar, propio, predefinido)
        // game.startGame();
    }

    public AutitosChocadores() {
        initializeGameBoard();
    }

    private void initializeGameBoard() {
        Ranking ranking = new Ranking(new ArrayList<>());

        System.out.println("Bienvenidos! Te gustaria jugar Autitos Chocadores? (si/no)");
        String choice = scan.nextLine().toLowerCase();

        while (choice.equals("si")) {
            // Ask the player for the game setup option
            System.out.println("Eligir un opcion:");
            System.out.println("a) Registrar un jugador");
            System.out.println("b) Configurar tablero propio");
            System.out.println("c) Jugar");
            System.out.println("d) Ranking");
            System.out.println("e) Fin");

            char setupOption = scan.nextLine().toLowerCase().charAt(0);

            switch (setupOption) {
                case 'a':// user input to create table
                    ranking.addPlayer(createPlayer());
                    break;
                case 'b':
                    // Create your own table
                    System.out.println("Ingrese el tamaño del tablero (entre 5x5 y 7x7):");
                    m = getValidInput(5, 7); // Validar el tamaño del tablero
                    System.out.println("Ingrese la cantidad de autos (entre 3 y 12):");
                    n = getValidInput(3, 12); // Validar la cantidad de autos

                    board = new String[m][m]; // Inicializa el tablero con el tamaño seleccionado
                    for (int i = 0; i < n; i++) {
                        System.out.println("Ingrese la posición y dirección del auto " + (i + 1) + " (formato A12 2):");
                        String input = scan.nextLine();
                        placeCar(input);
                    }
                    displayGameBoard();
                    break;
                case 'c':
                    // choose two different players from the list of players available
                    // !!Have code to error if there are no players input into the list
                    System.out.println("Eligir su tablero:");
                    System.out.println("1) Tablero Al Azar");
                    System.out.println("2) Tablero Propio");
                    System.out.println("3) Tablero Predefinido");

                    int gameOption = scan.nextInt();
                    scan.nextLine();

                    switch (gameOption) {
                        case '1':// al azar
                            m = random.nextInt(5, 7);
                            n = random.nextInt(3, 12);
                            // generate random car positions
                            generateRandomTable(m, n);
                            displayGameBoard();
                            break;
                        case '2': // tablero propio
                            // load previously entered board size(m), num of cars(n), car positions.
                            // build board using this saved data
                            // !!Have code to error if there is no propio table input in the system
                            break;
                        case '3':// tablero predefinido
                            // load already made table coords?
                            // loadGameDataFromFile("Test/predefinedTable.txt");?
                            playPredefinedGame();
                            break;
                        default:
                            System.out.println("Opcion invalido, saliendo del juego.");
                            System.exit(1);
                    }
                case 'd':
                    ranking.sortByPoints();
                    ranking.displayRanking(); // Display the sorted ranking
                    break;
                case 'e':
                    System.out.println("Saliendo del juego.");
                    System.exit(1);
                default:
                    System.out.println("Opcion invalido, saliendo del juego.");
                    System.exit(1);
            }
            System.out.println("Desea volver al menu principal? (si/no)");
            choice = scan.nextLine().toLowerCase();
        }
    }

    private void playPredefinedGame() {

    }

    private void placeCar(String input) {
        try {
            String[] parts = input.split(" ");
            String position = parts[0];
            int direction = Integer.parseInt(parts[1]);

            int row = position.charAt(0) - 'A';
            int col = Integer.parseInt(position.substring(1)) - 1;

            // Check if the calculated indices are within the bounds of the board
            if (row >= 0 && row < m && col >= 0 && col < m) {
            // Create a new Autito object with calculated position and set orientation
            Autito newCar = new Autito(row * m + col);  // Convert row and column to a single index (assuming linear board representation)
            newCar.setOrientation(direction);  // Set the orientation based on user input
            board[row][col] = newCar;  // Place the Autito object on the board

            } else {
                System.out.println("Posición fuera de límites, intente de nuevo.");
                i--; // Reintenta si la posición es inválida
            }
        } catch (Exception e) {
            System.out.println("Error en la entrada. Formato correcto: A12 2");
            i--; // Reintenta si ocurre un error
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

        board = new String[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = getRandomSymbol() + "" + getRandomColor();
            }
        }
    }

    private void displayGameBoard() {

        printColumnNumbers(); // Print column numbers at the top

        for (int i = 0; i < m; i++) {
            printRowBorder(); // Print the row border
            System.out.print((char) ('A' + i) + " |"); // Print the row LETTER (a,b,c,d)

            for (int j = 0; j < m; j++) {
                String cell = board[i][j];
                printColoredSymbol(cell);
            }

            System.out.println(); // Move to the next line for the next row
        }

        printRowBorder(); // Print the bottom row border
        System.out.println(); // Add an extra line for clarity
    }

    private void displayRandomCar() {
        char[][] randomAutito = getRandomCar();
        for (char[] row : randomAutito) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private void printColoredSymbol(String cell) {
        // TODO: make it print multiple lines in the cell for the car?
        String[] parts = cell.split("");
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

    private char getRandomSymbol() {
        // Return a random symbol as a car direction placeholder ('^', 'v', '<-', '->')
        // TODO: make multiple lines // specify headlights/rest of car
        char[] symbols = { '^', 'v', '<', '>' };
        return symbols[random.nextInt(symbols.length)];
    }

    private char[][] getRandomCar() {
        Autito autito = new Autito(0); // Assumes position is utilized somehow
        char[][][] autitos = { autito.getCarUp(), autito.getCarDown(), autito.getCarLeft(), autito.getCarRight() }; 
        return autitos[random.nextInt(autitos.length)];
    }

    private char getRandomColor() {
        // Return a random color for the car (red or blue)
        return random.nextBoolean() ? 'R' : 'A';
    }

    public int getValidInput(int min, int max) {
        int input = 0;
        while (input == 0) {
            try {
                int value = scan.nextInt();
                scan.nextLine();
                if (value >= min && value <= max) {
                    input = value;
                } else {
                    System.out.println("invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println("invalid input (inputmismatchexception)");
                scan.nextLine();
            }
        }
        return input;
    }

    private void printColumnNumbers() {
        System.out.print("       "); // whitespace before nums
        for (int j = 0; j < m; j++) {
            System.out.printf("%-7d", (j + 1)); // pads nums with spaces tomake them at least 4 chars wide
        }
        System.out.println();
    }

    private void printRowBorder() {
        System.out.print("  +"); // beginning
        for (int j = 0; j < m; j++) {
            System.out.print("------+"); // car is 4x2 spaces, make it 6 leaving 2
        }
        System.out.println();
    }

    public static Jugadores createPlayer() {
        System.out.print("Ingrese su nombre");
        String nameInput = scan.nextLine();
        System.out.print("Ingrese su edad");
        int ageInput = scan.nextInt();
        scan.nextLine();
        System.out.print("Ingrese el alias: ");
        String aliasInput = scan.nextLine();
        Jugadores newPlayer = new Jugadores(nameInput, ageInput, aliasInput, 0, 0, 0, 0, 0);
        return newPlayer;
    }

}
