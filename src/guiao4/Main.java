package guiao4;

import guiao1.ex2.Counter;

public class Main {
    public static void main(String args[]){
        Counter c = new Counter();
        BoundedBuffer bb = new BoundedBuffer(10);
        Thread consumer = new Thread( new Consumer(bb,c));
        Thread producer = new Thread( new Producer(bb,c));
        consumer.start();
        producer.start();

        try{
            consumer.join();
            producer.join();
        }catch(Exception e){
            e.getMessage();
        }

    }
}
