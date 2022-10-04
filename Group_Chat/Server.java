package Group_Chat;

import java.io.IOException;
import java.net.ServerSocket;


public class Server {
    public static void main(String[] args) {
        // Serversocket to accept connections from the client
        try(ServerSocket serverSocket = new ServerSocket(1234);
        )
        {
            while(!serverSocket.isClosed()){
               Thread thread = new Thread(new ServerThread(serverSocket.accept()));
               thread.start();
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
