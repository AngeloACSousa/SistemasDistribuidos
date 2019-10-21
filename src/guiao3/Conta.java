package guiao3;

import java.util.concurrent.locks.ReentrantLock;

public class Conta {
    double valor;
    ReentrantLock contaLock;

    public Conta(){
        this.valor = 0;
    }
    public Conta(double valor){
        this.valor = valor;
    }

    public void depositar(double valor){
        contaLock.lock();
        this.valor += valor;
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
