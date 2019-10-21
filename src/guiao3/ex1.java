package guiao3;

public class ex1 {

    public static void main(String args[]) {

        for (int j = 0; j < 100; j++) {
            Banco banco = new Banco();
            System.out.println(banco.criarConta(1));
            Thread threads[] = new Thread[2];
            for (int i = 0; i < 2; i++) {
                threads[i] = new Thread(new RunnableCliente1Teste1(banco));
                threads[i].start();
            }
            try {
                for (int i = 0; i < 2; i++) {
                    threads[i].join();
                }
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
    }
}
