package guiao5;

public class ex2 {
    public static void main(String args[]) {
        WareHouse ware = new WareHouse();

        ware.stock.put("item1", new Item());
        ware.stock.put("item2", new Item());
        ware.stock.put("item3",new Item());

        Thread consumer = new Thread(new Consumer(ware));
        Thread supplier = new Thread(new Supplier(ware));

        consumer.start();
        supplier.start();
        try{
            consumer.join();
            supplier.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
