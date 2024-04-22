package autitoschocadores;

import java.util.*;
/*TODO:
    1. change row numbers to print letters (done)
    2. when a move input is made (A12, where A is row), be able to compute 
            A= row 1, B= row 2, etc.
    3. board is mxm (5x5) but is reallt 20x20 (5x4+5x4) because each cell is 4x4 to accomodate car up/down/left/right
*/

public class AutitosChocadores {

    public static void main(String[] args) {
        AutitosChocadores game = new AutitosChocadores();// select option (al azar, propio, predefinido)
        game.initializeGameBoard();
    }
    public AutitosChocadores() {
        initializeGameBoard();
    }

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static Board board;
    
    public static int m = 0; // board size (mxm)
    public static int n = 0; // amt of cars (x=n;)

    public static int propioM = 0;//WE NEED TO KNOW WHAT IS THIS
    public static int propioN = 0;//SAME

   

   

    private void initializeGameBoard() {
        Ranking ranking = new Ranking(new ArrayList<>());
        board = new Board(m); 
        System.out.println("Bienvenidos! Te gustaria jugar Autitos Chocadores? (si/no)");
        String choice = scanner.nextLine().toLowerCase();
        
        while (choice.equals("si")) {
            // Ask the player for the game setup option
            System.out.println("Eligir un opcion:");
            System.out.println("a) Registrar un jugador");
            System.out.println("b) Configurar tablero propio");
            System.out.println("c) Jugar");
            System.out.println("d) Ranking");
            System.out.println("e) Fin");

            char setupOption = scanner.nextLine().toLowerCase().charAt(0);

            switch (setupOption) {
                case 'a':// user input to create table
                    ranking.addPlayer(createPlayer());
                    break;
                case 'b':
                    // Create your own table
                   // configureCustomBoard();
                    break;
                case 'c':
                    // choose two different players from the list of players available
                    // !!Have code to error if there are no players input into the list
                    System.out.println("Eligir su tablero:");
                    System.out.println("1) Tablero Al Azar");
                    System.out.println("2) Tablero Propio");
                    System.out.println("3) Tablero Predefinido");

                    int gameOption = scanner.nextInt();
                    scanner.nextLine();

                    switch (gameOption) {
                        case 1:// al azar
                            m = random.nextInt(5, 7);
                            n = random.nextInt(3, 12);
                            // generate random car positions
                           // generateRandomTable(m, n);
                            displayGameBoard();
                            break;
                        case 2: 
                       // configureCustomBoard();
                        break;
                        case 3:// tablero predefinido
                            // load already made table coords?
                            // loadGameDataFromFile("Test/predefinedTable.txt");
                            playPredefinedGame();
                            break;
                        default:
                            System.out.println("Opcion invalido, saliendo del juego.");
                            System.exit(0);
                    }
                case 'd':
                    ranking.sortByPoints();
                    ranking.displayRanking(); // Display the sorted ranking
                    break;
                case 'e':
                    System.out.println("Saliendo del juego.");
                    System.exit(0);
                default:
                    System.out.println("Opcion invalido, saliendo del juego.");
                    System.exit(0);
            }
            System.out.println("Desea volver al menu principal? (si/no)");
            choice = scanner.nextLine().toLowerCase();
        }
    }

    private void playPredefinedGame() {
        m = 5;
       
       // board = new char[m][m]; // Initialize the game board IM INITIALIZING UP
        board = new Board(m); 
        // Place the predefined cars on the board
        placeCar("A1 1"); // Car facing down
        placeCar("A2 3"); // Car facing left
        placeCar("A4 1"); // Car facing down
        placeCar("A5 2"); // Car facing up
        placeCar("D5 1"); // Car facing down
        placeCar("E1 2"); // Car facing up
        placeCar("E4 4"); // Car facing right
        placeCar("E5 3"); // Car facing left
    
        displayGameBoard(); // Display the game board with predefined cars
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
            board.placeAutito(row, col, newCar); // Place the Autito object on the board

            } else {
                System.out.println("Posición fuera de límites, intente de nuevo.");
            }
        } catch (Exception e) {
            System.out.println("Error en la entrada. Formato correcto: A12 2");
        }
    }

    private void displayGameBoard() {
        printColumnNumbers(); // Print column numbers at the top
    
        for (int row = 0; row < m; row++) {
            printRowBorder();

            for (int i = 0; i < 4; i++) { // Loop for each row within a cell
                // Print the row border
                if (i == 0) {
                    System.out.print((char) ('A' + row)); // Print the row LETTER (a,b,c,d) only for the first cell in a row
                }
                System.out.print(" |");
    
                for (int col = 0; col < m; col++) {
                    Autito autito = board.getAutitoAt(row, col);
                    
                    if (autito != null) {
                        char[][] carRepresentation = autito.getCurrentOrientation();
                        for (int j = 0; j < 4; j++) { // Loop for each row in a car representation
                            for (int k = 0; k < 4; k++) { // Loop for each column in a row of car representation
                                System.out.print(carRepresentation[i][k]);
                            }
                            // Add whitespace padding to make each cell the same size
                            System.out.print(" ");
                        }
                    } else {
                        // No cars, print empty spaces
                        System.out.print("            ");
                    }
                    System.out.print("|");
                }
                System.out.println(); // Move to the next line for the next row
            }
        }
    
        printRowBorder(); // Print the bottom row border
        System.out.println(); // Add an extra line for clarity
    }
    
    public int getValidInput(int min, int max) {
        int input = 0;
        while (input == 0) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= min && value <= max) {
                    input = value;
                } else {
                    System.out.println("Error: Input out of bounds");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input (InputMismatchException)");
                scanner.nextLine();
            }
        }
        return input;
    }

    private void printColumnNumbers() {
        System.out.print("         "); // whitespace before nums
        for (int j = 0; j < m; j++) {
            System.out.printf("%6d", (j + 1)); // pads nums with spaces tomake them at least 4 chars wide
        }
        System.out.println();
    }

    private void printRowBorder() {
        System.out.print("  +"); // beginning
        for (int j = 0; j < m; j++) {
            System.out.print(" - - - - +"); // car can be 2x4 or 4x2 spaces, should make each cell 4 wide
        }
        System.out.println();
    }

public Jugadores createPlayer() {
    System.out.println("Ingrese su nombre:");
    String nameInput = scanner.nextLine();
    System.out.println("Ingrese su edad: (1-100)");
    int ageInput = getValidInput(1,100);
    System.out.println("Ingrese el alias:");
    String aliasInput = scanner.nextLine();
    Jugadores newPlayer = new Jugadores(nameInput, ageInput, aliasInput, 0, 0, 0, 0, 0);
    return newPlayer;
}
}