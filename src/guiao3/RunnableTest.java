package guiao3;

public class RunnableTest implements Runnable{
    Banco b;

    public RunnableTest(Banco b){
        this.b = b;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            System.out.println(b.criarConta(i));
        }
    }
}
