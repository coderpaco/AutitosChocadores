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

    public static String Player1;
    public static String Player2;

    public static Jugadores player1Position;
    public static Jugadores player2Position;
   
    public static boolean gameWon = false;
   // ELEGIR JUGADORES REGISTRADOS PARA JUGAR
   private Jugadores[] elegirJugadores() {
    System.out.println("Jugadores registrados:");
    for (int i = 0; i < jugadoresList.size(); i++) {
        System.out.println((i + 1) + ". " + jugadoresList.get(i).getAlias());
    }
    System.out.println("Elige dos jugadores (numeros separados por espacios):");
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
    
    Player1 = jugadores[0].getAlias();
    Player2 = jugadores[1].getAlias();
    
    player1Position = jugadores[0];
    player2Position = jugadores[1];
    
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
                            //m = random.nextInt(5, 7); (old, we need to ask for size + cars amt) 
                            //n = random.nextInt(3, 12);
                            System.out.println("Ingrese el tamano del tablero (entre 5x5 y 7x7):");
                            m = getValidInput(5, 7); // Validar el tamaño del tablero
                            System.out.println("Ingrese la cantidad de autos (entre 3 y 12):");
                            n = getValidInput(3, 12); // Validar la cantidad de autos
                            // Inicializa el tablero con el tamaño seleccionado
                            board = new Board(m);
                            // generate random car positions
                            generateRandomTable(m, n);
                            displayGameBoard();
                            startGame();
                            break;
                        case 2: 
                        if (customBoardConfiguration != null) {
                            m = customBoardConfiguration.getBoardSize();
                            n = customBoardConfiguration.getNumCars();
                            for (String position : customBoardConfiguration.getCarPositions()) {
                                placeCar(position);
                            }
                            displayGameBoard();
                            startGame();
                        } else {
                            System.out.println("Error: No se ha configurado ningún tablero propio.");
                        }
                        break;
                        case 3:// tablero predefinido
                            // load already made table coords?
                            // loadGameDataFromFile("Test/predefinedTable.txt");

                            playPredefinedGame();
                            startGame();
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
        while (!choice.equals("si") && !choice.equals("no")) {
            System.out.println("Respuesta no válida. Por favor, responda 'si' o 'no'.");
            choice = scanner.nextLine().toLowerCase();
        }
        if (choice.equals("no")){
            System.out.println("Saliendo del juego.");
                    System.exit(0);
        }
    }
}

    private void startGame()
    {
        boolean player1 = true;
        System.out.println("players are: " + currentPlayerName(player1) + " and " + currentPlayerName(!player1));

        while (gameWon !=true) {
            System.out.println("Player " + currentPlayerName(player1) +  " Input move or press S R X");
            String chosenMove = scanner.nextLine().toUpperCase();
            
            switch(chosenMove){
                case "S":
                    getMovesList();
                    editPlayerStats(player1).changePoints(1); //subtract a point for asking for help like a nerd
                    //System.out.println("Player " + currentPlayerName(player1) +  " Input move or press S R X");
                    //scanner.nextLine().toUpperCase();
                    break;
                case "R":
                    rotateBoard();
                    player1 = !player1;
                    break;
                case "X":
                    System.out.println(currentPlayerName(player1) + " has forfeit the game, " + currentPlayerName(!player1)  + " wins!");
                    editPlayerStats(player1).addForfeit(); //add forfeit
                    editPlayerStats(!player1).addWin(); //add win
                    gameWon = true;
                    break;
                default:
                    handleMove(chosenMove);
                    player1 = !player1;
                    break;
            }
            if (checkMovesExist() == false){
                    System.out.println(currentPlayerName(player1) + " no more moves, " + currentPlayerName(!player1)  + " wins!");
                    editPlayerStats(player1).addLoss();//add forfeit
                    editPlayerStats(!player1).addWin(); //add win
                    gameWon = true;
            }
            
            
        }

    }
    
    private Jugadores editPlayerStats(boolean value){
        Jugadores playerPos;
        if (value == true){
            //it is player 1's turn, give their pos
            playerPos = player1Position;
        }else{
            //must be false, player 2 pos
            playerPos = player2Position;
        }
        return playerPos;
    }
    
    private String currentPlayerName(boolean value){
        String player;
        if (value == true){
            //player 1's turn
            player = Player1;
        }else{
            //player 2's turn
            player = Player2;
        }
        return player;
    }
    
    private void getMovesList(){
        ArrayList<String> movesList = new ArrayList<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < m; j++){
                //System.out.println("checking "+ i + "" + j); //DEBUGGING
                Autito autito = board.getAutitoAt(i, j);
                if (autito != null){
                            if (board.checkAutitoInDirections(i,j) == true){
                        String newI = Character.toString((char) (i + 'A'));
                        System.out.println("Available move: " + newI + " " + (j+1));
                        movesList.add(i + " " + j);
                    } 
                }
            }
        }
        /*
        for (int k = 0; k < movesList.size(); k++){
            System.out.println(k);
        }
        */
    }
    private boolean checkMovesExist(){
        ArrayList<String> movesList = new ArrayList<>();
        boolean movesExist = true;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < m; j++){
                Autito autito = board.getAutitoAt(i, j);
                if (autito != null){
                        if (board.checkAutitoInDirections(i,j) == true){
                        movesList.add(i + " " + j);
                        movesExist = true;
                    } 
                }
            }
        }
        if (movesList.size()< 1){
            System.out.println("no moves loser");
            movesExist = false;
        }
        return movesExist;
    }

    private void handleMove(String move) {
        String receivedMove = move.toUpperCase(); // Convertir el movimiento a mayúsculas para consistencia
        boolean validMove = false;
    
        while (!validMove) {
            try {
                // Dividir el movimiento en partes
                String[] parts = receivedMove.split("");
                String position = parts[0]; // Letra de la posición
                int direction = Integer.parseInt(parts[1]); // Dirección del autito
    
                // Calcular la posición del autito en el tablero
                int row = position.charAt(0) - 'A'; // Convertir la letra a un índice de fila (0 para 'A', 1 para 'B', etc.)
                int col = direction - 1; // Restar 1 porque las direcciones comienzan desde 1 en la entrada del usuario
    
                // Verificar si la posición está dentro de los límites del tablero
                if (board.isValidPosition(row, col)) {
                    Autito autito = board.getAutitoAt(row, col); // Obtener el autito en la posición especificada
    
                    // Verificar si el autito es seleccionable (tiene otro autito alrededor después de girar en sentido horario)
                    if (board.checkAutitoInDirections(row, col)) {
                        // Mover el autito al primer autito que encuentre chocando en sentido horario
                        moveAutitoToCollision(autito, row, col);
    
                        // Indicar que el movimiento fue válido
                        validMove = true;
    
                        // Mostrar el tablero actualizado
                        displayGameBoard();
                    } else {
                        System.out.println("No hay autitos alrededor de esta posicion para chocar. Intente de nuevo.");
                        receivedMove = scanner.nextLine().toUpperCase(); // Solicitar un nuevo movimiento
                    }
                } else {
                    System.out.println("Posición fuera de límites. Intente de nuevo.");
                    receivedMove = scanner.nextLine().toUpperCase(); // Solicitar un nuevo movimiento
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Formato de movimiento incorrecto. Intente de nuevo. (Formato correcto: A1)");
                receivedMove = scanner.nextLine().toUpperCase(); // Solicitar un nuevo movimiento
            }
        }
    }


private void moveAutitoToCollision(Autito autito, int row, int col) {
    String autitoDirection = autito.getCarDirection();
    //System.out.println(autitoDirection);
    int[] relevantDirections;

    //System.out.println(autitoDirection);
    // Determina las direcciones adyacentes relevantes según la orientación actual del autito
    switch (autitoDirection) {
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

      // Iterate over all relevant directions
      for (int direction : relevantDirections) {
        // Get the initial position of the Autito
        int currentRow = row;
        int currentCol = col;

        // Move the Autito in the current direction until it reaches the end or finds another Autito
        while (true) {
            // Get the next position after moving in the current direction
            int[] nextPosition = board.getNextPosition(currentRow, currentCol, direction);
           // System.out.println("Im checking" + "Row" + currentRow + "Column" + currentCol + "Direction" + direction);
            int nextRow = nextPosition[0];
            int nextCol = nextPosition[1];

            // Check if the next position is valid
            if (!board.isValidPosition(nextRow, nextCol)) {
               // System.out.println("im not a valid position");
                break; // Reached the edge of the board
            }

            // Retrieve the Autito object at the next position
            Autito nextAutito = board.getAutitoAt(nextRow, nextCol);

            // If there's a collision, move the Autito
            if (nextAutito != null) {
                // Move the current Autito to the collision position
                autito.setOrientation(direction);
                board.placeAutito(nextRow, nextCol, autito);
                // Remove the Autito from the original position
                board.placeAutito(row, col, null);

                //System.out.println("Collision detected! Autito moved to position " + (char) ('A' + nextRow) + " " + (nextCol + 1));
                return;
            }

            // Update the current position
            currentRow = nextRow;
            currentCol = nextCol;
        }
    }

    // No collisions found in any direction
    System.out.println("No se encontraron autitos para chocar. El autito no se movió.");
}
     
    private void rotateBoard() {
        //System.out.println("Rotating the board...");
    
        // Create a new board to store the rotated cells
        Board rotatedBoard = new Board(m);
    
        // Rotate the board by swapping rows and columns
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                Autito autito = board.getAutitoAt(i, j);
                if (autito != null) {
                    int newRow = j; // Rotate columns
                    int newCol = m - 1 - i; // Rotate rows
                    rotatedBoard.placeAutito(newRow, newCol, autito); // Place the Autito in the rotated position
                }
            }
        }
    
        // Update the original board with the rotated board
        board = rotatedBoard;
    
        // Rotate all cars on the board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                Autito autito = board.getAutitoAt(i, j);
                if (autito != null) {
                    autito.rotateClockwise(); // Rotate the car
                }
            }
        }
        displayGameBoard();
        //System.out.println("Board and cars rotated successfully.");
    }
    
    private void playPredefinedGame() 
    {
        m = 5;
       
       // board = new char[m][m]; // Initialize the game board IM INITIALIZING UP
        board = new Board(m); 
        // Place the predefined cars on the board
        placeCar("A12"); // Car facing down
        placeCar("A23"); // Car facing left
        placeCar("A42"); // Car facing down
        placeCar("A50"); // Car facing up
        placeCar("D52"); // Car facing down
        placeCar("E10"); // Car facing up
        placeCar("E41"); // Car facing right
        placeCar("E53"); // Car facing left
    
        displayGameBoard(); // Display the game board with predefined cars
    }

    private void placeCar(String input) 
    {
        try 
        {
            String[] parts = input.split("");
            String position = parts[0];
            //System.out.println("position: " + position);
            
            int direction = Integer.parseInt(parts[2]);
            //System.out.println("direction: " + direction);
            int row = position.charAt(0) - 'A';
            //System.out.println("row: " + row);
            int col = Integer.parseInt(parts[1]) - 1;
            //System.out.println("col: " + col);
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
            System.out.println("Error en la entrada. Formato correcto: A122 (fila,col,orrientacion");
        }
    }

    private void displayGameBoard() {
    printColumnNumbers(); // Print column numbers at the top

    for (int row = 0; row < m; row++) {
        printRowBorder();
        for (int i = 0; i < 4; i++) { // Loop for each row within a cell
            System.out.print((i == 0 ? (char) ('A' + row) : ' ') + " |"); // Print the row letter only for the first cell in a row

            for (int col = 0; col < m; col++) {
                Autito autito = board.getAutitoAt(row, col);
                
                if (autito != null) {
                    System.out.print(autito.getCarColor()); // Add color
                    char[][] carRepresentation = autito.getOrientation();
                    for (int k = 0; k < 4; k++) {
                        System.out.print(carRepresentation[i][k]);
                    }
                    System.out.print("\033[0m"); // Reset to default color
                } else {
                    System.out.print("    "); // No cars, print empty spaces
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
                System.out.println("Ingrese la posición y dirección del auto " + (i + 1) + " (formato A124):");//IT NEEDS AN EXCEPTION
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

//Diego Pereira Puig - 329028
//Davit Dostourian Erbe, 281665