package Server;

/**
 * @author Nicholas Parise
 * @version 1.0
 * @course COSC 2P13
 * @assignment #2
 * @student Id 7242530
 * @since July 2nd , 2023
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UI {

    public UI(){}

    /**
     * prints matrix to user
     *
     * @param gameMatrix matrix
     * @param cli socket out
     */
    public void printMatrix(char[][] gameMatrix, PrintWriter cli){

        for (int j = 0; j < 13; j++) {
            cli.print("_");
        }
        cli.println();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                cli.print(gameMatrix[i][j]+" ");
            }
            cli.println();
        }

        for (int j = 0; j < 13; j++) {
            cli.print("-");
        }
        cli.println();
        for (int j = 1; j < 8; j++) {
            cli.print(j+" ");
        }
        cli.println();
    }

    public void notPlayAgain(PrintWriter cli, PrintWriter clj){
        cli.println("A player chose to NOT play again! Disconnecting....");
        clj.println("A player chose to NOT play again! Disconnecting....");
    }

    public void tie(PrintWriter cli, PrintWriter clj){
        cli.println("All spots filled. TIE");
        clj.println("All spots filled. TIE");
    }


    public void playAgain(PrintWriter cli,PrintWriter clj){
        cli.println("Both players chose to play again! Resetting board....");
        clj.println("Both players chose to play again! Resetting board....");
    }

    public void winner(PrintWriter cli){
        cli.println("WINNER!");
    }

    public void loser(PrintWriter cli){
        cli.println("LOSER");
    }

    public void opponentTurn(PrintWriter cli){
        cli.println("Waiting on opponent....");
    }

    public void yourTurn(PrintWriter cli){
        cli.println("It is now your turn");
    }


    /** User interface loop
     *
     * @param cli
     * @param in1
     * @param g given g to run can insert method
     * @return int to add player to.
     */
    public int promptUser(PrintWriter cli, BufferedReader in1, Game g){
        int col = 0;
        String temp;

        while(true){
            cli.println("Where would you like to insert the coin? (1-7): ");

            try{
                temp = in1.readLine();
                if (temp==null) return 0;
            } catch (IOException e) {
                System.out.println("Connection TERMINATED");
                break;
            }

            try{
                col = Integer.parseInt(temp);
            }
            catch (NumberFormatException ex){
              col = -1; // used to trigger text below
            }

            if(col<1 || col>7) {
                cli.println("Must we within range");
            }else if(!g.canInsert(col-1)){
                cli.println("Cannot insert in full column");
            }else{
                break;
            }
        }
        return col-1;
    }


    /**
     * prompts to reset the game
     *
     * @param cli
     * @param in1
     * @return true if playing again
     */
    public boolean promptReset(PrintWriter cli, BufferedReader in1) {
        String temp = "";
        char ans;

        cli.println("Would you like to play again? (Y/N): ");

        try{
            temp = in1.readLine();
            if (temp==null) return false;
        } catch (IOException e) {
            System.out.println("Connection TERMINATED");
        }

        try {
            ans = temp.charAt(0);

            if(ans == 'y' || ans == 'Y'){
                return true;
            }

        }catch (IndexOutOfBoundsException e){
            return false;
        }

        return false;
    }
}
