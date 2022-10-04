package Group_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable{


    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private static ArrayList<ServerThread> serverThreads= new ArrayList<>();
    private String userName;
    public ServerThread(Socket socket){
        try{
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(),true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userName = in.readLine();
            out.println("Server: Welcome to the chat "+ userName);
            sendMessage("Server: "+ userName+ " has joined!");
            serverThreads.add(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        String message;
        while (socket.isConnected()){
            try{
                message = in.readLine();
                System.out.println(userName+": "+message);
                sendMessage(message);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(String message){
        for(ServerThread serverThread : serverThreads){
            if(!userName.equals(serverThread.userName))
            serverThread.out.println(message);
        }

    }
}
