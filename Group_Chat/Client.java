package Group_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;


    public Client(Socket socket){
        try{
            this.socket = socket;
            out = new PrintWriter(this.socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void sendMessage(){
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        while(socket.isConnected()) {
            try{
                out.println(stdin.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (socket.isConnected()){
                    try{
                        message = in.readLine();
                        System.out.println(message);
                    } catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }



    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("localhost",1234);
        Client client = new Client(socket);
        client.listenForMessage();
        client.sendMessage();
    }


}
