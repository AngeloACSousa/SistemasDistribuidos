package guiao4;

public class Main {
    public static void main(String args[]){
        BoundedBuffer bb = new BoundedBuffer(10);
        Thread consumidor = new Thread( new Consumidor(bb));
        Thread produtor = new Thread( new Produtor(bb));

        consumidor.start();
        produtor.start();

        try{
            consumidor.join();
            produtor.join();
        }catch(Exception e){
            e.getMessage();
        }

    }
}
