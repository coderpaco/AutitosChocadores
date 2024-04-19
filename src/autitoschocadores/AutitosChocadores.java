package autitoschocadores;
import java.io.*; 
import java.util.*;
/*TODO:
    1. change row numbers to print letters
    2. when a move input is made (A12, where A is row), be able to compute 
            A= row 1, B= row 2, etc.
*/


public class AutitosChocadores 
{
    
    public static Scanner scan = new Scanner(System.in);
    public static String[][] board;
    public static int m = 0; //board size (mxm)
    public static int n = 0; //amt of cars (x=n;)
    
    public static void main(String[] args) 
    {
        AutitosChocadores game = new AutitosChocadores();// select option (al azar, propio, predefinido)
        //game.startGame();
    }
    
    public AutitosChocadores() 
    {
        initializeGameBoard();
    }
     
     private char[][] getRandomAutito() {
        Autito autito = new Autito(0); // Assumes position is utilized somehow
        char[][][] autitos = {autito.getAutitoUp(), autito.getAutitoDown(), autito.getAutitoLeft(), autito.getAutitoRight()};
        Random random = new Random();
        return autitos[random.nextInt(autitos.length)];
    }

    private void displayRandomAutito() {
        char[][] randomAutito = getRandomAutito();
        for (char[] row : randomAutito) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    private void initializeGameBoard() 
    {

        System.out.println("Bienvenidos! Te gustaria jugar Autitos Chocadores? (si/no)");
        String choice = scan.nextLine().toLowerCase();

        if (choice.equals("si")) 
        {
            // Ask the player for the game setup option
            System.out.println("Eligir un opcion:");
            System.out.println("a) Usar tablero propio");
            System.out.println("b) Usar tablero predefinido");
            System.out.println("c) Generar un tablero al azar");
            System.out.println("d) Salir del juego");

            char setupOption = scan.nextLine().toLowerCase().charAt(0);

            switch (setupOption) 
            {
                case 'a'://user input to create table
                    crearPersona();
                    System.out.println("Tamano? (mxm)");
                    m = getValidInput(5,7);
                    //scan.nextLine();
                    System.out.println("Cantidad de autitos (n)?"); 
                    n = getValidInput(3,12);
                    generateRandomTable(m, n);
                    displayGameBoard();
                    //code to print out list of players
                    break;
                case 'b':
                    // Use a predefined table
                    //usePredefinedTable();
                    System.out.println("generate a table based on saved params");
                    //displayGameBoard(); //print the table
                    break;
                case 'c':
                    //crearPersona();
                    // Generate a random table
                    Random rand = new Random();
                    m = rand.nextInt(5,7);
                    n = rand.nextInt(3,12);
                    
                    generateRandomTable(m, n);
                    displayGameBoard();
                    break;
                case 'd':
                    System.out.println("Saliendo del juego.");
                    System.exit(1);
                default:
                    System.out.println("Opcion invalido, saliendo del juego.");
                    System.exit(1);
            }

        }else{
            System.out.println("Opcion invalido, saliendo del juego.");
        }
    }
    
    private void generateRandomTable(int numRowsCols, int numCars) 
    {
        // generate a random table based on the rows/cols
        m = numRowsCols;//board is square so we can use n here in both cases
        n = numCars;
        
        /* TODO:
            1. generate random pos on the board
            2. place a random car position there
            3. verify theres a possible move, if not redo process?
        */
        
        board = new String[m][m];
        
        for (int i = 0; i < m; i++) 
	{
            for (int j = 0; j < m; j++) 
            {
                board[i][j] = getRandomSymbol() + "" + getRandomColor();
            }
        }
    }
    
    private void displayGameBoard() 
    {
  
        printColumnNumbers(); // Print column numbers at the top

        for (int i = 0; i < m; i++) 
	{
            printRowBorder(); // Print the row border
            System.out.print((i + 1) + " |"); // Print the row number

            for (int j = 0; j < m; j++) 
            {
                String cell = board[i][j];
                printColoredSymbol(cell);
            }

            System.out.println(); // Move to the next line for the next row
        }

        printRowBorder(); // Print the bottom row border
        System.out.println(); // Add an extra line for clarity
    }
    
    private void printColoredSymbol(String cell) 
    {
        //TODO: make it print multiple lines in the cell for the car?
        String[] parts = cell.split(""); 
        String colorCode = getColorCode(parts[1]);
        System.out.print("  " + colorCode + parts[0] + "\u001B[0m"); //default color
        System.out.print("   |"); 
    }
    
    private String getColorCode(String symbol) 
    {
        // Return ANSI escape code for color based on the symbol
        //TODO: make the headlights yellow, \x1b[43m
        //https://talyian.github.io/ansicolors/
        
        switch (symbol) {
            case "R":
                return "\u001B[31m"; // Red
            case "A":
                return "\u001B[34m"; // Blue
            default:
                return ""; // No color (default)
        }
    }
    
    private char getRandomSymbol() 
    {
        // Return a random symbol as a car direction placeholder ('^', 'v', '<-', '->')
        // TODO: make multiple lines // specify headlights/rest of car
        char[] symbols = {'^', 'v', '<', '>'};
        Random random = new Random();
        return symbols[random.nextInt(symbols.length)];
    }

    private char[][] getRandomAutito() 
    {
        Autito autito = new autito;
        char[][] autitos = {getAutitoUp, getAutitoDown, getAutitoLeft, getAutitoRight};
        Random random = new Random();
        return autito[random.nextInt[][](autitos.length)];
    }
    
    private char getRandomColor() 
    {
        // Return a random color for the car (red or blue)
        Random random = new Random();
        return random.nextBoolean() ? 'R' : 'A';
    }

    private void printColumnNumbers() 
    {
        System.out.print("       "); //whitespace before nums
        for (int j = 0; j < m; j++) {
            System.out.printf("%-7d", (j + 1)); //pads nums with spaces tomake them at least 4 chars wide
        }
        System.out.println();
    }
    
    private void printRowBorder() 
    {
        System.out.print("  +"); //beginning
        for (int j = 0; j < m; j++) {
            System.out.print("------+"); //car is 4x2 spaces, make it 6 leaving 2
        }
        System.out.println();
    }
    
    public int getValidInput(int min, int max)
    {
        int input = 0;
        while (input == 0)
        {
            try
            {
                int value = scan.nextInt();
                scan.nextLine();
                if (value >= min && value <= max)
                {
                    input = value;
                }else
                {
                    System.out.println("invalid input");
                }
            }catch (InputMismatchException e)
            {
                System.out.println("invalid input (inputmismatchexception)");
                scan.nextLine();
            }
        }
        return input;
    }  
        
    public static Jugadores crearPersona()
    {
        System.out.print("Ingrese su nombre");
        String nombre = scan.nextLine();
        System.out.print("Ingrese su edad");
        int edad = scan.nextInt();
        scan.nextLine();
        System.out.print("Ingrese el alias: ");
        String alias = scan.nextLine();
        Jugadores unaPersona = new Jugadores(nombre,edad, alias, 0, 0, 0, 0, 0);
        return unaPersona;
    }
}
