package guiao3;

public class RunnableCliente1Teste1 implements Runnable {
    Banco b;

    public RunnableCliente1Teste1(Banco b){
        this.b = b;
    }

    @Override
    public void run() {
        try {
            System.out.println(b.closeAccount(1));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
