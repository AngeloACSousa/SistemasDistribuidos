package guiao1.ex2;

class RunnableIncrement implements Runnable{
    int id;
    public int t;
    public Counter counter;

    public RunnableIncrement(int id, int t, Counter c){
        this.id = id;
        this.t = t;
        counter = c;
    }

    @Override
    public void run() {
        for(int i = 0; i < t; i++){
        counter.increment();
        System.out.println("Sou a thread " + id+ " e tenho valor " + counter.i);
        }
    }
}

public class main {


    public static void main(String args[]){
        Counter counter = new Counter();
        Thread[] threads = new Thread[100];
        int N = 100;
        int I = 200;
        for(int i = 0; i < N; i++){
            threads[i] = new Thread(new RunnableIncrement(i,I, counter));
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
