package guiao6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]){
        try{
            ServerSocket server = new ServerSocket(12345);

            while(true){

                System.out.println("Waiting for client");
                Socket client = server.accept();
                System.out.println("Client connected");
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter pr = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                String received;

                while((received = br.readLine())!= null){
                    System.out.println("Message received: " + received);
                    pr.println(received);
                    pr.flush();
                }
                client.shutdownInput();
                client.shutdownOutput();
                client.close();
                System.out.println("Client disconnected");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
