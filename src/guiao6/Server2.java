package guiao6;

import java.io.IOException;
import java.net.ServerSocket;

public class Server2 {

    public Server2(int port){
        try {
            ServerSocket server = new ServerSocket(port);
            int id = 0;
            System.out.println("Server Starting");
            while (true) {
                new Thread(new ServerThread(server.accept(), id++)).start() ;
                System.out.println("New Client Connected, id: " + id);

            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        new Server2(12345);
    }
}
