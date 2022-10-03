package Guess_The_Number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try(
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                )
        {
            String inputLine, outputLine;
            GuessNumberProtocol guessNumberProtocol = new GuessNumberProtocol();
            out.println("Hello! Pick a number between 1 and 100.");

            while ((inputLine = in.readLine()) != null) {

                if (inputLine.equals("Bye")) {
                    break;
                }
                if (inputLine.equals("y")) {
                    guessNumberProtocol = new GuessNumberProtocol();
                    out.println("Pick a number between 1 and 100");
                    continue;
                }

                System.out.println("Client: " + inputLine);
                outputLine = guessNumberProtocol.processInput(inputLine);

                if (outputLine.substring(0, 4).equals("Won!")) {
                    out.println(outputLine + " Press y to replay or write Bye to exit");
                } else {
                    out.println(outputLine);
                }


            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
