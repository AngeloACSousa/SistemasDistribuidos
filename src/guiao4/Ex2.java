package guiao4;

import guiao1.ex2.Counter;

public class Ex2 {
    public static void main(String args[]) throws InterruptedException{

        long[] melhorDebito = {0,0,0};
        long bestTime = Long.MAX_VALUE;
        for(int c = 1; c < 10; c++){
            Counter counterC = new Counter();
            Counter counterP = new Counter();
            long timeStart = System.currentTimeMillis();
            Thread[] consumers = new Thread[c];
            Thread[] producers = new Thread[10 - c];
            BoundedBuffer bb = new BoundedBuffer(10);


            for(int i = 0; i < 10; i++){
                if(i < c){
                    consumers[i] = new Thread(new Consumer(bb,counterC));
                    consumers[i].start();
                }
                if(i < 10-c) {
                    producers[i] = new Thread(new Producer(bb, counterP));
                    producers[i].start();
                }
            }
            for(int i = 0; i < 10; i++){
                if(i < c){
                    consumers[i].join();
                }
                if(i < 10-c) {
                    producers[i].join();
                }
            }

            long timeEnd = System.currentTimeMillis();
            long timeTaken = timeEnd-timeStart;

            if(timeTaken < bestTime) {
                bestTime = timeTaken;
                melhorDebito[0] = c;
                melhorDebito[1] = 10 - c;
                melhorDebito[2] = bestTime;
            }
        }
        System.out.println("Consumidores: " +melhorDebito[0]);
        System.out.println("Produtores: "+ melhorDebito[1]);
        System.out.println("Debito: "+melhorDebito[2]);
    }
}
