package guiao4;

public class Consumidor implements Runnable {
    BoundedBuffer b;

    public Consumidor(BoundedBuffer b){
        this.b = b;
    }

    public void run(){
        for(int i = 0; i < 20; i++){
              try{
                  b.get();
              }catch(Exception e){
                  e.getMessage();
              }
        }
    }
}
