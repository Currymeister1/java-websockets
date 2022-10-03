package Guess_The_Number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 1234;

        try(
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

            String fromServer, fromClient;

            while((fromServer = in.readLine()) != null){
                System.out.println("From Server: "+fromServer);

                fromClient = stdin.readLine();

              if(fromClient != null){
                    out.println(fromClient);
                }

            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
