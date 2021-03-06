package guiao4;

public class BoundedBuffer {
    int buffer[];
    int size;

    public BoundedBuffer(int tamanho) {
        buffer = new int[tamanho];
        size = 0;
    }

    public void put(int v) throws InterruptedException {
        synchronized (this) {
            while (checkFull()) {
                this.wait();
            }
            notifyAll();
            buffer[size] = v;
            System.out.println(buffer[size] + " Inserted");
            printBuffer();
            size++;
        }
    }

    public int get() throws InterruptedException {
        synchronized (this) {

            while (size == 0) {
                this.wait();
            }

            notifyAll();
            size--;
            int res = buffer[size];
            buffer[size] = 0;
            System.out.println(res+ " Removed");
            printBuffer();
            return res;
        }
    }


    public boolean checkFull() {
        return size >= buffer.length;
    }

    public void printBuffer(){
        System.out.print("[");
        for(int i : buffer){
            System.out.print(i +", ");
        }
        System.out.println("]");
    }

}



