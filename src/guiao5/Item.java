package guiao5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Item {

    ReentrantLock itemLock;
    Condition isEmpty;
    int quantity;

    public Item(){
        itemLock = new ReentrantLock();
        isEmpty = itemLock.newCondition();
        quantity = 0;
    }
    public void supply(int quantity){
        itemLock.lock();
        this.quantity += quantity;
        isEmpty.signalAll();
        itemLock.unlock();
    }

    public void consume(){
        itemLock.lock();
        try {
            while(this.quantity == 0) {
                System.out.println("à espera");
                isEmpty.await();
            }
            this.quantity--;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            itemLock.unlock();
        }


    }

}
