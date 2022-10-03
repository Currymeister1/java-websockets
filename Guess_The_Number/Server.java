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

    public static void main(String[] args) throws IOException {
        int portNumber = 1234;
        boolean listening = true;
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);

        ) {
            while(listening){
                new ServerThread( serverSocket.accept()).start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}


