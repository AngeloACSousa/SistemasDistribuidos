package guiao5;

import java.util.HashMap;

 class WareHouse {
    public HashMap<String,Item> stock;

    public WareHouse(){
        stock = new HashMap<>();
    }
    void supply(String item, int quantity){
        Item toSupply = stock.get(item);
        toSupply.supply(quantity);
        System.out.println(item + " Added " + quantity);
    }

    void consume(String[] items){
        for(String s : items){
            Item aConsumir = stock.get(s);
            aConsumir.consume();
            System.out.println(s + " Consumido");
        }
    }
}

