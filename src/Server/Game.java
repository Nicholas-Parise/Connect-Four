package Server;

/**
 * @author Nicholas Parise
 * @version 1.0
 * @since July 2nd , 2023
 */

public class Game {

    char[][] gameMatrix;
    // blank, p1, p2
    // *, X, O
    public Game(){
        gameMatrix = new char[6][7];
        reset();
    }

    /**
     * simple init for game matrix
     */
    public void reset(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gameMatrix[i][j] = '*';
            }
        }
    }


    public char[][] getGameMatrix(){
        return gameMatrix;
    }


    /**
     *
     * @param col which column to insert
     * @return false if cannot insert at col number
     */
    public boolean insert(int col, char pl){

        if (!canInsert(col)){
            return false;
        }

        for (int i = 5; i >= 0; i--) {
         if(gameMatrix[i][col] == '*'){
             gameMatrix[i][col] = pl;
             break;
         }
        }
        return true;
    }


    /**
     *
     * @return true if every spot is full
     */
    public boolean tieTester() {

        for (int i = 0; i < 7; i++) {
            if(canInsert(i)){
               return false;
            }
        }
        return true;
    }


    /**
     *
     * @param col column of matrix
     * @return true if can insert
     */
    public boolean canInsert(int col){
        if (gameMatrix[0][col] == '*') {
            return true;
        }
        return false;
    }


    /** Solver to see if 4 are connected
     * Since order doesn't matter, and we start in the top left corner
     * only four directions need to be checked
     * in order: down, right, down-right, down-left.
     *
     * @return
     */
    public boolean win(char pl){

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                // for each spot in the grid:

                if(checkDown(i,j,pl)){
                    return true;
                }
                if(checkRight(i,j,pl)){
                    return true;
                }
                if(checkDownRight(i,j,pl)){
                    return true;
                }
                if(checkDownLeft(i,j,pl)){
                    return true;
                }
            }
        }
        return false;
    }

    /** This simple method checks for 4 in a row in the down direction
     *
     * @param i row
     * @param j col
     * @param pl player char
     * @return true or false if found
     */
    private boolean checkDown(int i, int j, char pl){
        if(i>2){
            return false;
        }
        for (int k = 0; k < 4; k++) {
            if(gameMatrix[i+k][j] != pl){
                return false;
            }
        }
        return true;
    }

    /** This simple method checks for 4 in a row in the right direction
     *
     * @param i row
     * @param j col
     * @param pl player char
     * @return true or false if found
     */
    private boolean checkRight(int i, int j, char pl){
        if(j>3){
            return false;
        }
        for (int k = 0; k < 4; k++) {
            if(gameMatrix[i][j+k] != pl){
                return false;
            }
        }
        return true;
    }

    /** This simple method checks for 4 in a row in the down&right diagonal
     *
     * @param i row
     * @param j col
     * @param pl player char
     * @return true or false if found
     */
    private boolean checkDownRight(int i, int j, char pl){
        if(j>3 || i>2){
            return false;
        }
        for (int k = 0; k < 4; k++) {
            if(gameMatrix[i+k][j+k] != pl){
                return false;
            }
        }
        return true;
    }

    /** This simple method checks for 4 in a row in the down&left diagonal
     *
     * @param i row
     * @param j col
     * @param pl player char
     * @return true or false if found
     */
    private boolean checkDownLeft(int i, int j, char pl){
        if(j<3 || i>2){
            return false;
        }
        for (int k = 0; k < 4; k++) {
            if(gameMatrix[i+k][j-k] != pl){
                return false;
            }
        }
        return true;
    }


}
