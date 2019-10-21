package guiao2.ex1;
import guiao1.ex2.Counter;

class RunnableIncrement2 implements Runnable{
    int id;
    public int t;
    public Counter counter;

    public RunnableIncrement2(int id, int t, Counter c){
        this.id = id;
        this.t = t;
        counter = c;
    }

    @Override
    public void run() {
        for(int i = 0; i < t; i++){
            counter.increment();
            System.out.println("Sou a thread " + id+ " e tenho valor " + counter.getCounter());
        }
    }
}

public class Main {
    public static void main(String args[]){
        Counter counter = new Counter();
        Thread[] threads = new Thread[10];
        int N = 100;
        int I = 200;
        for(int i = 0; i < N; i++){
            threads[i] = new Thread(new RunnableIncrement2(i,I, counter));
            threads[i].start();
        }
        try{
            for(int i = 0; i < N; i++){
                threads[i].join();
            }
        }catch(InterruptedException e){
            e.getMessage();
        }
    }
}
