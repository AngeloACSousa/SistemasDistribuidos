package guiao2.ex2;

public class Banco {
    public double contas[]; //index corresponde ao numero da conta, double ao valor de saldo

    public Banco(){
        this.contas = new double[2];

    }

    public synchronized double consulta(int i){
        return this.contas[i];
    }

    public synchronized void levantar(int i,double valor){
        this.contas[i] = this.contas[i] - valor;
    }

    public synchronized void depositar(int i, double valor){
        this.contas[i] = this.contas[i]+valor;
    }

    public void transferir(int para, int de, double valor){
        this.levantar(de,valor);
        this.depositar(para,valor);

    }
}

class RunnableCliente implements Runnable{
    int tipo;
    Banco b;
    int conta;

    RunnableCliente(int tipo, Banco b){
        this.b= b;
        this.tipo = tipo;//1 levanta, 0 poe
    }
    public void run(){
        /*
        for(int i = 0; i < 1000; i++){
            if (tipo == 1) {
                b.levantar(conta, 5);
            } else {
                b.depositar(conta, 5);
            }
        }
        */
        if(tipo == 0){
            for(int i = 0; i < 1000; i++) {
                b.transferir(1, 0, 1);
            }
        }else{
            for(int i = 0; i < 1000; i++){
                b.levantar(1,1);
            }
        }

    }
}

class Main{

    public static void main(String args[]){

        Banco banco = new Banco();
        Thread[] threads = new Thread[2];
        banco.contas[0] = 1000;
        for(int i = 0; i < 2; i++){
            threads[i] = new Thread(new RunnableCliente(i,banco));
            threads[i].start();
        }

        for(int i = 0; i < 2; i++){
            try{
                threads[i].join();
            }catch (InterruptedException e){
                e.getMessage();
            }
        }
        System.out.println(banco.consulta(0));
        System.out.println(banco.consulta(1));
    }


}
