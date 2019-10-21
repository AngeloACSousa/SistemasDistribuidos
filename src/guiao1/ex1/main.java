package guiao1.ex1;


public class main {
    public static void main(String args[]){
        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++){
            threads[i] = new MinhaThread(i);
            threads[i].start();
        }
        for(Thread t : threads){
            try{
                t.join();
            }
            catch (InterruptedException e){
                e.getMessage();
            }
        }

    }


}
