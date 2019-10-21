package guiao2.ex3;

public class Banco {
    public Conta contas[]; //index corresponde ao numero da conta, double ao valor de saldo

    public Banco(){
        contas = new Conta[2];
        contas[0] = new Conta(1000);
        contas[1] = new Conta();
    }

    public double consulta(int i){
        return contas[i].consultar();
    }

    public void levantar(int i,double valor){
        contas[i].levantar(valor);
    }

    public void depositar(int i, double valor){
        contas[i].depositar(valor);
    }

    public void transferir(int para, int de, double valor){
        int maior = para;
        int menor = de;
        if(maior < menor){
            menor = para;
            maior = de;
        }
        synchronized (contas[menor]){
            synchronized (contas[maior]){
                contas[de].levantar(valor);
                contas[para].depositar(valor);
            }
        }
    }
}
