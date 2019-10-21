package guiao1.ex1;

public class MinhaThread extends Thread{
    int i;
    Thread thread;

    public MinhaThread(int n){
        i = n;
        thread = new Thread();
    }

    @Override
    public void run() {
        for(int j = 0; j < 10; j++){
            System.out.println("Sou a thread: " + this.i + " e imprimi: " + j);
        }
    }
}
