package guiao7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Conta {
    double valor;
    List<Movimento> movimentos;
    ReentrantLock contaLock;

    public Conta(){
        this.valor = 0;
        contaLock = new ReentrantLock();
        movimentos = new ArrayList<>();
    }
    public Conta(double valor){
        this.valor = valor;
        contaLock = new ReentrantLock();
        movimentos = new ArrayList<>();
    }

    public void depositar(double valor){
        contaLock.lock();
        this.valor += valor;
        System.out.print("aqui");
        contaLock.unlock();
    }

    public void levantar(double valor) throws Exception{

            contaLock.lock();
            if (this.valor >= valor) {
                this.valor -= valor;

            }else throw new Exception("Saldo Insuficiente");

            contaLock.unlock();
    }

    public double consultar(){
        double consulta = 0;
        contaLock.lock();
        consulta = this.valor;
        contaLock.unlock();
        return consulta;
    }
}
