package Guess_The_Number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Normal Conversation between server and client.
 *
 * Number to guess 30
 *
 * Server: Hello! Pick a number between 1 and 100. (type ok to proceed)
 * Client: ok
 * Server: Is the number greater than 50. (y for yes/ n for no).
 * Client: N
 * Server: Is the number greater than 25.
 * Client: Y
 * Server: Is the number greater than 37
 * Client: N
 * Server: Is the number greater than 31.
 * Client: N
 * Server: Is the number greater 28.
 * Client: Y
 * Server: Is the number greater than 29.
 * Client: Y
 * Server: The number is 30.
 */


public class Server {

    public static void main(String[] args) {
        int portNumber = 1234;

        try(
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        )
        {
            String inputLine, outputLine;
            GuessNumberProtocol guessNumberProtocol = new GuessNumberProtocol();
            out.println("Hello! Pick a number between 1 and 100." );

            while((inputLine = in.readLine()) != null){

                if(inputLine.equals("Bye")){
                    break;
                }
                if(inputLine.equals("y")){
                    guessNumberProtocol = new GuessNumberProtocol();
                    out.println("Pick a number between 1 and 100");
                    continue;
                }

                System.out.println("Client: "+ inputLine);
                outputLine = guessNumberProtocol.processInput(inputLine);

                if(outputLine.substring(0,4).equals("Won!")){
                    out.println(outputLine+ " Press y to replay or write Bye to exit");
                }
                else{
                    out.println(outputLine);
                }


            }


        }catch (IOException e){
            e.printStackTrace();
        }

    }


}


