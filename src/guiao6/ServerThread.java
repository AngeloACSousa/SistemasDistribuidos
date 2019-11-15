package guiao6;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable{
    int id;
    Socket socket;

    public ServerThread(Socket s,int id){
        this.id = id;
        socket = s;
    }

    @Override
    public void run() {
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String received;
            while((received = input.readLine())!= null){
                System.out.println("Message received: " + received + " from client :" + id);
                output.println(received);
                output.flush();
            }
            System.out.println("Client "+ id +" disconnecting");
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
