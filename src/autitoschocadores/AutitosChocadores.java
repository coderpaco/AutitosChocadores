package autitoschocadores;

import java.util.*;
// version:4/23/2024 @ 6:53

public class AutitosChocadores 
{
    private List<Jugadores> jugadoresList = new ArrayList<>();

    public static void main(String[] args) 
    {
        AutitosChocadores game = new AutitosChocadores();// select option (al azar, propio, predefinido)
        game.initializeGameBoard();
    }
    public AutitosChocadores() 
    {
        initializeGameBoard();
    }
    public void agregarJugador(Jugadores jugador) {
        jugadoresList.add(jugador);
    }

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static Board board;
    
    public static int m = 0; // board size (mxm)
    public static int n = 0; // amt of cars (x=n;)

    public static int propioM = 0;//WE NEED TO KNOW WHAT IS THIS
    public static int propioN = 0;//SAME

   

   // ELEGIR JUGADORES REGISTRADOS PARA JUGAR
   private Jugadores[] elegirJugadores() {
    System.out.println("Jugadores registrados:");
    for (int i = 0; i < jugadoresList.size(); i++) {
        System.out.println((i + 1) + ". " + jugadoresList.get(i).getAlias());
    }
    System.out.println("Elige dos jugadores (números separados por espacios):");
    int jugador1Index = scanner.nextInt() - 1;
    int jugador2Index = scanner.nextInt() - 1;
    scanner.nextLine(); // Consumir la nueva línea

    // Verificar si se han elegido dos jugadores diferentes
    if (jugador1Index == jugador2Index || jugador1Index < 0 || jugador2Index < 0 || jugador1Index >= jugadoresList.size() || jugador2Index >= jugadoresList.size()) {
        System.out.println("Error: Debes elegir dos jugadores diferentes de la lista.");
        return null;
    }

    Jugadores[] jugadores = new Jugadores[2];
    jugadores[0] = jugadoresList.get(jugador1Index);
    jugadores[1] = jugadoresList.get(jugador2Index);

    return jugadores;
}

   

    private void initializeGameBoard() 
    {
        Ranking ranking = new Ranking(new ArrayList<>());
        board = new Board(m); 
        System.out.println("Bienvenidos! Te gustaria jugar Autitos Chocadores? (si/no)");
        String choice = scanner.nextLine().toLowerCase();
        
        while (choice.equals("si")) 
        {
            // Ask the player for the game setup option
            System.out.println("Eligir un opcion:");
            System.out.println("a) Registrar un jugador");
            System.out.println("b) Configurar tablero propio");
            System.out.println("c) Jugar");
            System.out.println("d) Ranking");
            System.out.println("e) Fin");

            char setupOption = scanner.nextLine().toLowerCase().charAt(0);

            switch (setupOption) 
            {
                case 'a':// user input to create table
                    Jugadores newPlayer = createPlayer();
                    ranking.addPlayer(newPlayer); // Add the new player to the ranking
                    agregarJugador(newPlayer); // Add the new player to the list
                    break;
                case 'b':
                    // Create your own table
                    configureCustomBoard();
                    break;
                case 'c':
                    // choose two different players from the list of players available
                    // !!Have code to error if there are no players input into the list
                    if (jugadoresList.size() < 2) {
                        System.out.println("Debes tener al menos dos jugadores registrados para jugar.");
                        break;
                    }

                    // Elige dos jugadores para jugar
                    Jugadores[] jugadores = elegirJugadores();
                    if (jugadores == null) {
                        break;
                    }
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
                            generateRandomTable(m, n);
                            displayGameBoard();
                            break;
                        case 2: 
                        if (customBoardConfiguration != null) {
                            m = customBoardConfiguration.getBoardSize();
                            n = customBoardConfiguration.getNumCars();
                            for (String position : customBoardConfiguration.getCarPositions()) {
                                placeCar(position);
                            }
                            displayGameBoard();
                        } else {
                            System.out.println("Error: No se ha configurado ningún tablero propio.");
                        }
                        break;
                        case 3:// tablero predefinido
                            // load already made table coords?
                            // loadGameDataFromFile("Test/predefinedTable.txt");

                            playPredefinedGame();
                            break;
                        default:
                            System.out.println("Opcion invalido, saliendo del juego.");
                            System.exit(0);
                            break;
                    }
                    break;
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

    private void playPredefinedGame() 
    {
        m = 5;
       
       // board = new char[m][m]; // Initialize the game board IM INITIALIZING UP
        board = new Board(m); 
        // Place the predefined cars on the board
        placeCar("A1 2"); // Car facing down
        placeCar("A2 3"); // Car facing left
        placeCar("A4 2"); // Car facing down
        placeCar("A5 0"); // Car facing up
        placeCar("D5 2"); // Car facing down
        placeCar("E1 0"); // Car facing up
        placeCar("E4 1"); // Car facing right
        placeCar("E5 3"); // Car facing left
    
        displayGameBoard(); // Display the game board with predefined cars
    }

    private void placeCar(String input) 
    {
        try 
        {
            String[] parts = input.split(" ");
            String position = parts[0];
            int direction = Integer.parseInt(parts[1]);

            int row = position.charAt(0) - 'A';
            int col = Integer.parseInt(position.substring(1)) - 1;

            // Check if the calculated indices are within the bounds of the board
            if (row >= 0 && row < m && col >= 0 && col < m) 
            {
            // Create a new Autito object with calculated position and set orientation
                Autito newCar = new Autito(row * m + col);  // Convert row and column to a single index (assuming linear board representation)
                newCar.setOrientation(direction);  // Set the orientation based on user input
                board.placeAutito(row, col, newCar); // Place the Autito object on the board
            } else 
            {
                System.out.println("Posición fuera de límites, intente de nuevo.");
            }
        } catch (Exception e) 
        {
            System.out.println("Error en la entrada. Formato correcto: A12 2");
        }
    }

    private void displayGameBoard() 
    {
        printColumnNumbers(); // Print column numbers at the top
    
        for (int row = 0; row < m; row++) 
        {
            printRowBorder();

            for (int i = 0; i < 4; i++) 
            { // Loop for each row within a cell
                // Print the row border
                if (i == 0) 
                {
                    System.out.print((char) ('A' + row)); // Print the row LETTER (a,b,c,d) only for the first cell in a row
                }
                if(i != 0)
                {
                    System.out.print((char) (' ')); // Print the row LETTER (a,b,c,d) only for the first cell in a row
                }
                System.out.print(" |");
    
                for (int col = 0; col < m; col++) 
                {
                    Autito autito = board.getAutitoAt(row, col);
                    
                    if (autito != null) 
                    {
                        char[][] carRepresentation = autito.getCurrentOrientation();
                        for (int j = 0; j < 1; j++) 
                        { // Loop for each row in a car representation
                            for (int k = 0; k < 4; k++) 
                            { // Loop for each column in a row of car representation
                                System.out.print(carRepresentation[i][k]);
                            }
                            // Add whitespace padding to make each cell the same size
                            System.out.print("");
                        }
                    } else 
                    {
                        // No cars, print empty spaces
                        System.out.print("    ");
                    }
                    System.out.print("|");
                }
                System.out.println(); // Move to the next line for the next row
            }
        }
    
        printRowBorder(); // Print the bottom row border
        System.out.println(); // Add an extra line for clarity
    }
    
    public int getValidInput(int min, int max) 
    {
        int input = 0;
        while (input == 0) {
            try 
            {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= min && value <= max) {
                    input = value;
                } else 
                {
                    System.out.println("Error: Input out of bounds");
                }
            } catch (InputMismatchException e) 
            {
                System.out.println("Error: Invalid Input (InputMismatchException)");
                scanner.nextLine();
            }
        }
        return input;
    }

    private void printColumnNumbers() 
    {
        System.out.print(" "); // whitespace before nums
        for (int j = 0; j < m; j++) 
        {
            System.out.printf("%5d", (j + 1)); // pads nums with spaces tomake them at least 4 chars wide
        }
        System.out.println();
    }

    private void printRowBorder() 
    {
        System.out.print("  +"); // beginning
        for (int j = 0; j < m; j++) 
        {
            System.out.print("----+"); // car can be 2x4 or 4x2 spaces, should make each cell 4 wide
        }
        System.out.println();
    }

public Jugadores createPlayer() 
{
    System.out.println("Ingrese su nombre:");
    String nameInput = scanner.nextLine();
    System.out.println("Ingrese su edad: (1-100)");
    int ageInput = getValidInput(1,100);
    System.out.println("Ingrese el alias:");
    String aliasInput = scanner.nextLine();
    Jugadores newPlayer = new Jugadores(nameInput, ageInput, aliasInput, 0, 0, 0, 0, 0);
    return newPlayer;

    
    
    }
    private GameConfiguration customBoardConfiguration;

    private void configureCustomBoard() {
        System.out.println("Ingrese el tamaño del tablero (entre 5x5 y 7x7):");
        m = getValidInput(5, 7); // Validar el tamaño del tablero
        System.out.println("Ingrese la cantidad de autos (entre 3 y 12):");
        n = getValidInput(3, 12); // Validar la cantidad de autos
        // Inicializa el tablero con el tamaño seleccionado
         board = new Board(m);
    
      String[] carPositions = new String[n];
            for (int i = 0; i < n; i++) {
                System.out.println("Ingrese la posición y dirección del auto " + (i + 1) + " (formato A12 2):");//IT NEEDS AN EXCEPTION
                carPositions[i] = scanner.nextLine();
                placeCar(carPositions[i]);
                } 
    
                //Save game configuration
                customBoardConfiguration =  new GameConfiguration(m, n, carPositions);
                //Display the custom board configuration
            displayGameBoard();
        }



        private void generateRandomTable(int numRowsCols, int numCars) {
            // Genera un tablero aleatorio basado en las filas/columnas
            m = numRowsCols; // El tablero es cuadrado, así que podemos usar 'n' aquí en ambos casos
            n = numCars;
        
            // Inicializa el tablero
            board = new Board(m);
        
            // Coloca un automóvil en cada posición generada aleatoriamente
            for (int i = 0; i < numCars; i++) {
                int randomRow = random.nextInt(m);
                int randomCol = random.nextInt(m);
                int direction = random.nextInt(4); // Dirección aleatoria (0-3)
                String position = Character.toString((char)('A' + randomRow)) + (randomCol + 1) + " " + direction;
                placeCar(position);
            }
            
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
}