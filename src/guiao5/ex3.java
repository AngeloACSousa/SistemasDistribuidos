package guiao5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Reader implements Runnable{
    RWLock rw;

    public Reader(RWLock rw){
        this.rw = rw;

    }
    @Override
    public void run() {
        try {
            rw.readLock();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rw.readUnlock();
        }
    }
}

class Writer implements Runnable{
    RWLock rw;

    public Writer(RWLock rw){
        this.rw = rw;
    }

    @Override
    public void run() {
        try {
            rw.writeLock();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rw.writeUnlock();
        }
    }
}

class RWLock{

    int readers;
    boolean writer;
    Lock rwLock;
    Condition waitWriter;
    Condition waitReader;

    RWLock(){
        readers = 0;
        writer = false;
        rwLock = new ReentrantLock();
        waitWriter = rwLock.newCondition();
        waitReader = rwLock.newCondition();
    }

    void readLock() throws InterruptedException{
        rwLock.lock();
        while(writer){
            waitReader.await();
        }

        readers++;
        System.out.println("Leitor lock");
        System.out.println("Número de leitores: " + readers);
        rwLock.unlock();
    }

    void readUnlock(){
        rwLock.lock();
        readers--;
        if(readers == 0){
            waitWriter.signal();
        }
        System.out.println("Leitor unlock");
        rwLock.unlock();
    }

    void writeLock() throws InterruptedException{
        rwLock.lock();
        while(writer || readers > 0){
            waitWriter.await();
        }
        System.out.println("Escritor lock");
        writer = true;
        rwLock.unlock();
    }

    void writeUnlock(){
        rwLock.lock();
        writer = false;
        waitWriter.signal();
        waitReader.signalAll();
        System.out.println("Escritor unlock");
        rwLock.unlock();
    }
}


class RWLockV2{

    int readers;
    boolean writer;
    Lock rwLock;
    Condition waitWriter;
    Condition waitReader;
    int writersRequest;

    RWLockV2(){
        readers = 0;
        writer = false;
        writersRequest = 0;
        rwLock = new ReentrantLock();
        waitWriter = rwLock.newCondition();
        waitReader = rwLock.newCondition();
    }

    void readLock() throws InterruptedException{
        rwLock.lock();
        while(writer || writersRequest > 0){
            waitReader.await();
        }
        readers++;
        System.out.println("Leitor lock");
        System.out.println("Número de leitores: " + readers);
        rwLock.unlock();
    }

    void readUnlock(){
        rwLock.lock();
        readers--;
        if(readers == 0){
            waitWriter.signal();
        }
        System.out.println("Leitor unlock");
        rwLock.unlock();
    }

    void writeLock() throws InterruptedException{
        rwLock.lock();
        writersRequest++;
        while(writer || readers > 0){
            waitWriter.await();
        }
        writersRequest--;
        System.out.println("Escritor lock");
        writer = true;
        rwLock.unlock();
    }

    void writeUnlock(){
        rwLock.lock();
        writer = false;
        waitWriter.signal();
        waitReader.signalAll();
        System.out.println("Escritor unlock");
        rwLock.unlock();
    }
}

public class ex3 {
    public static void main(String args[]){
        Thread[] readers = new Thread[100];
        Thread[] writers = new Thread[100];
        RWLock rw = new RWLock();

        for(int i = 0; i < 100; i++){
            readers[i] = new Thread(new Reader(rw));
            writers[i] = new Thread(new Writer(rw));
            readers[i].start();
            writers[i].start();
        }

        for(int i = 0; i < 100; i++){
            try{
            readers[i].join();
            writers[i].join();

            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}



