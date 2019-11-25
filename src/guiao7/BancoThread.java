package guiao7;

import java.io.*;
import java.net.Socket;

public class BancoThread implements Runnable {

    Socket s;
    Banco b;

    public BancoThread(Socket s, Banco b){
        this.s = s;
        this.b = b;
    }
    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            String received;
            while((received = input.readLine()) != null) {
                String[] splitted = received.split(" ");
                try {
                    switch (splitted[0]) {
                        case "levantar":
                            b.levantar(Integer.parseInt(splitted[1]),Double.parseDouble(splitted[2]));
                            System.out.println("Levantamento efectuado em " + splitted[1] + " de valor " + splitted[2]);
                            output.println("Levantamento efectuado em " + splitted[1] + " de valor " + splitted[2]);
                            output.flush();
                            break;
                        case "depositar":
                            b.depositar(Integer.parseInt(splitted[1]),Double.parseDouble(splitted[2]));
                            System.out.println("Deposito feito em " + splitted[1] + "com valor " + splitted[2]);
                            output.println("Deposito feito em " + splitted[1] + "com valor " + splitted[2]);
                            output.flush();
                            break;
                        case "transferir":
                            b.transferir(Integer.parseInt(splitted[1]),Integer.parseInt(splitted[2]),Double.parseDouble(splitted[3]));
                            System.out.println("Transferência de : " + splitted[1] + " para " + splitted[2] + " de valor " + splitted[3]);
                            output.println("Transferência de : " + splitted[1] + " para " + splitted[2] + " de valor " + splitted[3]);
                            output.flush();
                            break;
                        case "consultar":
                            double v = b.consultar(Integer.parseInt(splitted[1]));
                            System.out.print("Conta " + splitted[1] + " com saldo de "+ v);
                            output.println("Conta " + splitted[1] + " com saldo de "+ v);
                            output.flush();
                            break;
                        case "criar":
                            int id = b.criarConta(Float.parseFloat(splitted[1]));
                            System.out.println("Conta Criada, id: " + id + " com valor: " + splitted[1]);
                            output.println("Conta Criada, id: " + id + " com valor: " + splitted[1]);
                            output.flush();
                            break;
                        case "fechar":
                            v = b.closeAccount(Integer.parseInt(splitted[1]));
                            System.out.println("Conta Fechada, id: " + splitted[1] + " com valor: " + v);
                            output.println("Conta Fechada, id: " + splitted[1] + " com valor: " + v);
                            output.flush();
                            break;
                        case "movimentos":
                            String m = b.movimentos(Integer.parseInt(splitted[1]));
                            System.out.println(m);
                            output.println(m);
                            output.flush();
                            break;
                        default:
                            System.out.println("Comando '" + splitted[0] + "' não encontrado");
                            output.println("Comando '" + splitted[0] + "' não encontrado");
                            output.flush();
                            break;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    output.println(e.getMessage());
                    output.flush();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
