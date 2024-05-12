package autitoschocadores;

public class gameConfiguration {
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
