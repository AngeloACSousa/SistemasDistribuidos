package guiao6;

import java.io.*;
import java.net.Socket;

public class Client{

    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private BufferedReader in;


    public Client(String address, int port)throws IOException {

        socket = new Socket(address, port);
        System.out.println("Connected");

        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(System.in));

        String input;

        while(!(input = in.readLine()).equals("quit")){
            pw.println(input);
            pw.flush();
            System.out.println("Echo: " + br.readLine());
        }
        socket.shutdownOutput();
        socket.shutdownInput();
        socket.close();
        System.out.println("Disconnected");
    }

    public static void main(String args[]){
        try{
            new Client("127.0.0.1",12345);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}