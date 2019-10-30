package guiao4;

public class Barreira {
    private int count;
    private int limit;
    private int round;

    public Barreira(int n){
        this.limit = n;
        this.count = 1;
        this.round = 0;
    }
///VER ESTA COISAS TODA
    public synchronized void esperar() throws InterruptedException{
        int myRound = round;
        int myId = count;

        while(count < limit && round == myRound){
            System.out.println("Thread " + count + "adormeceu, grupo: "+myRound);
            count++;
            this.wait();
        }
        if(myId % limit == 0){
            this.notifyAll();
            round++;
            count = 1;
        }
    }

    public static void main(String args[]){
        Barreira barreira = new Barreira(5);
        Thread[] threads = new Thread[100];

        for(int i = 0; i < 100; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        barreira.esperar();
                    }catch (InterruptedException e){
                        e.getMessage();
                    }
                }
            });
            threads[i].start();
        }
        for(Thread t : threads){
            try {
                t.join();
            }catch (InterruptedException e){
                e.getMessage();
            }
        }
    }
}
