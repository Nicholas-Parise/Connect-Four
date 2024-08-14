package GUI;

public class Parser {

    /**
     * @author Nicholas Parise
     * @version 1.0
     * @course COSC 2P13
     * @assignment #2
     * @student Id 7242530
     * @since July 2nd , 2023
     */

    ConnectFour conFour;
    Comms com;
    boolean matrixStart;
    int matrixIndex;
    char[][] matrix;

    public Parser(ConnectFour c, Comms coms){
        conFour = c;
        com = coms;
        matrixStart = false;
        matrixIndex = 0;
        matrix = new char[6][7];
    }

    /** uses the first unique two characters to find out what each message
     * from the server is saying it will then run the commands that are required.
     *
     * @param in string in
     */
    public void inputStream(String in){

        String start = in.substring(0,2);

        System.out.println(start);

        if(start.equals("Co")){

            conFour.setPlayer(in.charAt(47));

        }else if(start.equals("A ")){

        }else if(start.equals("Al")){

        }else if(start.equals("Bo")){

        }else if(start.equals("WI")){
            conFour.setPromptPlayAgain(true);
            conFour.setGameState(1);
        }else if(start.equals("LO")){
            conFour.setPromptPlayAgain(true);
            conFour.setGameState(2);
        }else if(start.equals("Wa")){
            conFour.setGameState(3);
        }else if(start.equals("It")){
            conFour.setGameState(0);

        }else if(start.equals("Wh")){
            conFour.setCanSend(true);
            conFour.setDataReady(false);
            com.setCanWrite(true);
            System.out.println("allowed to send now");
        }else if(start.equals("Ca")){

        }else if(start.equals("Wo")){
            conFour.setCanSend(true);
            conFour.setDataReady(false);
            conFour.setPromptPlayAgain(true);
            com.setCanWrite(true);
            conFour.PlayAgain();
        }else if(start.equals("__")){
            matrixStart = true;
        }else if(start.equals("--")){
            matrixStart = false;
            matrixIndex = 0;
            conFour.setMatrix(matrix);
        }else if(matrixStart){

            int temp = 0;

            for (int i = 0; i < 14; i+=2) {
                matrix[matrixIndex][temp] = in.charAt(i);
                temp++;
            }
            matrixIndex++;
        }
    }

    /**
     * "Co" // connected player symbol
     * "A " // not playing again
     * "Al" // tie
     * "Bo" // playing again
     * "WI" // winner
     * "LO" // loser
     * "Wa" // Waiting for your turn
     * "It" // now your turn
     * "Wh" // insert prompt
     * "Ca" // full column
     * "Wo" // play again
     * "__" // start of matrix
     * "--" // end of matrix
     */

}
