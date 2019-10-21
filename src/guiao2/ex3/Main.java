package guiao2.ex3;

public class Main {
    public static void main(String args[]) {

        Banco banco = new Banco();

        Thread[] threads = new Thread[2];
            for (int i = 0; i < 2; i++) {
                threads[i] = new Thread(new RunnableCliente(i, banco));
                threads[i].start();
            }

            for (int i = 0; i < 2; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
            System.out.println("Consulta final da conta 0 " + banco.consulta(0));
            System.out.println("Consulta final da conta 1 " + banco.consulta(1));

    }

}

