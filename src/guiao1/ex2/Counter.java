package guiao1.ex2;

public class Counter {
    public int i;
    public Counter(){
        i = 0;
    }
    public void increment(){
        i++;
    }

    public void increment2(){
        synchronized (this){
            this.i++;
            Thread.currentThread().getName();
        }
    }
    public synchronized int getCounter(){
        return this.i;
    }
}
