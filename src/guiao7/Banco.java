package guiao7;

import com.sun.jdi.Method;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class Banco{
    public Map<Integer,Conta> contas;
    public int newId = 0;
    ReentrantLock bancoLock = new ReentrantLock();

    public Banco() {
        contas = new HashMap<>();
    }

    public int criarConta(float initialBalance) {
        Conta conta = new Conta(initialBalance);
        bancoLock.lock();
        int id = newId;
        contas.put(id,conta);
        newId++;
        bancoLock.unlock();
        return id;
    }


    public double closeAccount(int id) throws Exception {
        if (contas.containsKey(id)) {
            bancoLock.lock();
            Conta conta = contas.get(id);
            conta.contaLock.lock();
            double valor = conta.consultar();
            contas.remove(id);
            bancoLock.unlock();
            conta.contaLock.unlock();
            return valor;
        }
        else throw new Exception("Conta Inválida");
    }



    public double totalBanco(int[] ids){
        double total = 0;
        Arrays.sort(ids);
        List<Conta> contasLock = new ArrayList<>();
        for(int i = 0 ; i < ids.length; i++) {
            bancoLock.lock();
            if (contas.containsKey(ids[i])) {
                Conta c = contas.get(ids[i]);
                c.contaLock.lock();
                contasLock.add(c);
            }
            bancoLock.unlock();
        }
        for(Conta c : contasLock) {
            total += c.consultar();
            c.contaLock.unlock();
        }
        return total;
        }

        public double consultar(int id){
        bancoLock.lock();
        Conta conta = contas.get(id);
        conta.contaLock.lock();
        double res = conta.consultar();
        conta.contaLock.unlock();
        bancoLock.unlock();
        return res;
        }

        public void depositar(int id, double v){
        bancoLock.lock();
        Conta c = contas.get(id);
        c.contaLock.lock();
        c.depositar(v);
        c.movimentos.add(new Movimento(1,"deposito de " + v,c.valor));
        c.contaLock.unlock();
        bancoLock.unlock();
        }

        public void levantar(int id, double v) throws Exception{
            bancoLock.lock();
            Conta c = contas.get(id);
            if(c.consultar() < v) throw new Exception("Saldo Insuficiente");
            c.contaLock.lock();
            c.levantar(v);
            c.movimentos.add(new Movimento(2,"levantamento de: " + v,c.valor));
            c.contaLock.unlock();
            bancoLock.unlock();
        }

        public void transferir(int from, int to, double v) throws Exception{
        int maior,menor;
        if(to < from){
            menor = to;
            maior = from;
        }else{
            menor = from;
            maior = to;
        }
        bancoLock.lock();
        if(!contas.containsKey(menor) || !contas.containsKey(maior)) throw new Exception("Conta Invalida");
        Conta c1 = contas.get(menor);
        Conta c2 = contas.get(maior);

        c1.contaLock.lock();
        c2.contaLock.lock();

        if(contas.get(from).consultar()<v) throw new Exception("Saldo Insuficiente");
        else {
            Conta de = contas.get(from);
            de.levantar(v);
            Conta para = contas.get(to);
            para.depositar(v);
            de.movimentos.add(new Movimento(3,"Transferencia para " + to + " de valor " + v,de.valor));
            para.movimentos.add(new Movimento(3,"Transferencia de " + from + " de valor " + v,para.valor));
        }
        c2.contaLock.unlock();
        c1.contaLock.unlock();
        bancoLock.unlock();
        }

        public String movimentos(int id){
        bancoLock.lock();
        Conta c = contas.get(id);
        c.contaLock.lock();
        bancoLock.unlock();
        String movimentos = "";
            for(Movimento m: c.movimentos){
            movimentos += m.descricao + " ;";
            }
        c.contaLock.unlock();
            return movimentos;
        }

        public static void main(String args[]){
            try {
                Banco b = new Banco();
                ServerSocket s = new ServerSocket(12345);
                System.out.println("Server Starting");
                while(true){
                   new Thread(new BancoThread(s.accept(),b)).start();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
