package Server;
import java.net.*;
import java.io.*;

/**
 * @author Nicholas Parise
 * @version 1.0
 * @course COSC 2P13
 * @assignment #2
 * @student Id 7242530
 * @since July 2nd , 2023
 */

public class ConnectionThread extends Thread{
    private Socket client1,client2;
    private Game g;
    private UI ui;

    public ConnectionThread(Socket c1, Socket c2) {
        client1=c1; client2=c2;
        g = new Game();
        ui = new UI();
    }

    public void run() {
        try (
                PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
                PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);
                BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
                BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
        ) {
            boolean promptEnd = false;
            out1.println("Connected. You are player one, your symbol is: X");    // send data to client 1
            out2.println("Connected. You are player two, your symbol is: O");    // send data to client 2

            while (true) {

                // simple game control logic

                // ----- player 1 turn

                ui.printMatrix(g.getGameMatrix(),out1);
                ui.printMatrix(g.getGameMatrix(),out2);

                ui.yourTurn(out1);
                ui.opponentTurn(out2);

                g.insert(ui.promptUser(out1,in1,g),'X');

                ui.printMatrix(g.getGameMatrix(),out1);
                ui.printMatrix(g.getGameMatrix(),out2);

                if(g.win('X')){
                    ui.winner(out1);
                    ui.loser(out2);
                    promptEnd = true;
                }

                if(g.tieTester()){
                    ui.tie(out1,out2);
                    promptEnd = true;
                }

                if(promptEnd){
                    promptEnd = false;
                    if(ui.promptReset(out1,in1) && ui.promptReset(out2,in2)){
                        ui.playAgain(out1,out2);
                        g.reset();
                    }else{
                        ui.notPlayAgain(out1,out2);
                        break;
                    }
                }

                // ----- player 2 turn

                ui.printMatrix(g.getGameMatrix(),out1);
                ui.printMatrix(g.getGameMatrix(),out2);

                ui.yourTurn(out2);
                ui.opponentTurn(out1);

                g.insert(ui.promptUser(out2,in2,g),'O');

                ui.printMatrix(g.getGameMatrix(),out1);
                ui.printMatrix(g.getGameMatrix(),out2);

                if(g.win('O')){
                    ui.winner(out2);
                    ui.loser(out1);
                    promptEnd = true;
                }

                if(g.tieTester()){
                    ui.tie(out1,out2);
                    promptEnd = true;
                }

                if(promptEnd){
                    promptEnd = false;
                    if(ui.promptReset(out2,in2) && ui.promptReset(out1,in1)){
                        ui.playAgain(out1,out2);
                        g.reset();
                    }else{
                        ui.notPlayAgain(out1,out2);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Connection TERMINATED");
        }
    }
}