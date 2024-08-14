package GUI;

public class ConnectFour {

    GUI gui;

    String sendToServer;
    char[][] matrix;
    boolean canSend;
    boolean dataReady;
    boolean promptPlayAgain;
    boolean GameLoop;
    char player;
    char ghostPlayer;
    int gameState;  // 0 - playing , 1 - won, 2 lose, 3 waiting
    int ghosti, ghostj;

    public ConnectFour(){

        matrix = new char[6][7];
        dataReady = false;
        canSend = false;
        promptPlayAgain = false;
        GameLoop = true;
        gameState = 0;
        sendToServer = " ";

        removeGhost();
        createMatrix();

        gui = new GUI(this);
        gui.setVisible(true);

        new Comms(this).start();

        while (GameLoop){
            gui.update();
        }
        gui.close();
    }

    /**
     * sets the player and the ghost player to be the correct char
     * @param player
     */
    public void setPlayer(char player) {
        this.player = player;
        ghostPlayer = Character.toLowerCase(player);
    }

    /**
     * resets ghost position to be hidden
     */
    public void removeGhost(){
        ghosti = -1;
        ghostj = -1;
    }

    /**
     * inserts the ghost reusing the code to insert players.
     *
     * @param col column to add ghost
     * @return true if can insert
     */
    public boolean insertGhost(int col){

        if (!canInsert(col)){
            return false;
        }
        for (int i = 5; i >= 0; i--) {
            if(matrix[i][col] == '*'){
              //  matrix[i][col] = ghostPlayer;
                ghosti = i;
                ghostj = col;
                break;
            }
        }
        return true;
    }

    /**
     *
     * @param col
     * @return true if can insert in column
     */
    public boolean canInsert(int col){
        if (matrix[0][col] == '*') {
            return true;
        }
        return false;
    }

    /**
     * code to play again
     */
    public void PlayAgain(){
        sendToServer = gui.SpawnPlayAgainBox();

        if(sendToServer.equals("N")){
            GameLoop = false;
        }

        gameState = 0;
        promptPlayAgain = false;
        canSend = false;
        dataReady = true;
        System.out.println(sendToServer+" <- sent to server");
    }

    /**
     * Code to insert and send to server a player into a column
     *
     * @param col current colun
     */
    public void insertAt(int col){
        sendToServer = col+"";
        canSend = false;
        dataReady = true;
        System.out.println(sendToServer+" <- sent to server");
    }

    /**
     * just to prevent null pointers, adds * to all the positions in the matrix
     */
    public void createMatrix(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                matrix[i][j] = '*';
            }
        }
    }

    public String getSendToServer() {
        return sendToServer;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] m) {
        matrix = m;
    }

    public void setDataReady(boolean dataReady) {
        this.dataReady = dataReady;
    }

    public boolean isDataReady() {
        return dataReady;
    }

    public boolean isCanSend(){
        return canSend;
    }

    public void setCanSend(boolean canSend) {   // means console now waiting for commands.
        this.canSend = canSend;
    }

    public boolean getPromptPlayAgain() {
        return promptPlayAgain;
    }

    public void setPromptPlayAgain(boolean promptPlayAgain) {
        this.promptPlayAgain = promptPlayAgain;
    }

    public char getGhostPlayer() {
        return ghostPlayer;
    }

    public int getGhosti() {
        return ghosti;
    }

    public int getGhostj() {
        return ghostj;
    }

    public void setGameState(int g) {
        gameState = g;
    }

    public int getGameState() {
        return gameState;
    }


    public static void main(String[] args) {new ConnectFour();}
}
