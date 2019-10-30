package guiao4;

import guiao1.ex2.Counter;

public class Consumer implements Runnable {
    BoundedBuffer b;
    Counter counter;

    public Consumer(BoundedBuffer b, Counter c){
        this.b = b;
        this.counter = c;
    }

    public void run() {
        while(counter.getCounter() < 100){
              try{
                  counter.increment2();
                  Thread.sleep(5);
                  b.get();
              }catch(Exception e){
                  e.getMessage();
              }
        }
    }
}
