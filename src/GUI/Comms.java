package GUI;

/**
 * @author Nicholas Parise
 * @version 1.0
 * @course COSC 2P13
 * @assignment #2
 * @student Id 7242530
 * @since July 2nd , 2023
 */

import java.net.*;
import java.io.*;
import java.lang.Thread;

public class Comms extends Thread{

    String strOut;
    boolean canWrite;
    Parser parser;
    ConnectFour confour;

    public Comms(ConnectFour con){
        confour = con;
    }

    String hostName="localhost";
    int portNumber=1024;

    public void run() {

        canWrite = false;
        strOut = "";
        parser = new Parser(confour,this);

        try (
                Socket conn = new Socket(hostName, portNumber);
                PrintWriter sockOut = new PrintWriter(conn.getOutputStream(),true);
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        ) {
            while (true) {

                if(!canWrite) {
                    String fromServer = sockIn.readLine();    // read data from server
                    System.out.println(fromServer);         // print data to terminal.

                    if(fromServer == null){
                        break;
                    }

                    parser.inputStream(fromServer);
                }

                // I'm not 100% sure why, but this sleep is needed or else the
                // thread will hang from time to time.
                try{
                    Thread.sleep(10);
                }catch (Exception e){}

                if(canWrite && confour.isDataReady()){
                    canWrite = false;
                    strOut = confour.getSendToServer();
                    sockOut.println(strOut);
                    //confour.clearSendToServer();
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("I think there's a problem with the host name.");
        } catch (IOException e) {
            System.out.println("Had an IO error for the connection.");
        }
    }

    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }
}
