package guiao7;

import java.io.*;
import java.net.Socket;

public class Client {

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
        System.out.println("Para executar escrevar o nome do comando e\n" +
                " em seguida os argumentos recebidos separados por espaço, exemplo:\n"
                +"'criar 10'");
        System.out.println("Comandos disponiveis: criar (valor inicial)" +
                        "\n fechar (id de conta)" +
                        "\n consultar (id de conta)" +
                        "\n levantar (id de conta) (saldo)" +
                        "\n depositar (id de conta) (saldo)" +
                        "\n transferir (id de conta que transfere) (id de conta que recebe) (saldo)");
        String input;
        while(!(input = in.readLine()).equals("Quit")){
            pw.println(input);
            pw.flush();
            System.out.println("Mensagem do Servidor: " + br.readLine());
        }
        socket.shutdownOutput();
        socket.shutdownInput();
        socket.close();
        System.out.println("Disconnected");
    }

    public static void main(String args[]){
        try {
            new Client("127.0.0.1", 12345);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
