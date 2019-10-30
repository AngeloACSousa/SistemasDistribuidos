package guiao4;

import guiao1.ex2.Counter;

public class Producer implements Runnable {
    BoundedBuffer b;
    Counter counter;

    public Producer(BoundedBuffer b, Counter c){
        this.b = b;
        this.counter = c;
    }

    public void run(){
        while(counter.getCounter() < 100){
              try{
                  counter.increment2();
                  Thread.sleep(10);
                  b.put(counter.getCounter());
              }catch(Exception e){
                  e.getMessage();
              }
        }
    }
}
