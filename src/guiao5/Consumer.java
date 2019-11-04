package guiao5;


public class Consumer implements Runnable{

    WareHouse wh;

    public Consumer(WareHouse wh){
        this.wh = wh;
    }
    @Override
    public void run() {
        String[] toConsume = new String[]{"item1","item2","item3"};

        for(int i = 0; i < 100; i++){
            wh.consume(toConsume);
        }
    }
}
