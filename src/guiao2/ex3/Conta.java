package guiao2.ex3;

public class Conta {
    double valor;

    public Conta(){
        this.valor = 0;
    }
    public Conta(double valor){
        this.valor = valor;
    }
    public synchronized void depositar(double valor){
        this.valor += valor;
    }

    public synchronized void levantar(double valor){
        this.valor -= valor;
    }

    public synchronized double consultar(){
        return this.valor;
    }
}
