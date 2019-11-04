package guiao5;

public class Supplier implements Runnable {

    WareHouse wh;
    public Supplier(WareHouse wh){
        this.wh = wh;
    }
    @Override
    public void run() {
        String[] toSupply = new String[]{"item1","item2","item3"};

        for(int i = 0; i < 100; i++){
            wh.supply(toSupply[0], 1);
            System.out.println(toSupply[0] + " Added 1");
            wh.supply(toSupply[1], 2);
            System.out.println(toSupply[1] + " Added 2");
            wh.supply(toSupply[2],3);
            System.out.println(toSupply[2] + " Added 3");
        }
    }
}
