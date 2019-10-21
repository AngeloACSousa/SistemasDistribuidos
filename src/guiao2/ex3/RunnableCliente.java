package guiao2.ex3;



class RunnableCliente implements Runnable{
    int tipo;
    Banco b;

    RunnableCliente(int tipo, Banco b){
        this.b= b;
        this.tipo = tipo;
    }
    public void run(){
        if(tipo == 0){
                synchronized (this) {
                    b.transferir(1, 0, 1000);
                    System.out.println("Consulta de Transferencia 0 " + b.contas[0].consultar());
                    System.out.println("Consulta de Transferencia 1 " + b.contas[1].consultar());
                }
        }else{
                synchronized (this) {
                    /*
                    b.levantar(1, 1000);
                    System.out.println("Consulta de levantamento 1 " + b.contas[1].consultar());
                    */
                    b.transferir(0, 1, 1000);
                    System.out.println("Consulta de Transferencia 0 " + b.contas[0].consultar());
                    System.out.println("Consulta de Transferencia 1 " + b.contas[1].consultar());
                }
        }
    }
}
