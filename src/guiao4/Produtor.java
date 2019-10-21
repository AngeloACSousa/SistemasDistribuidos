package guiao4;

public class Produtor implements Runnable {
    BoundedBuffer b;

    public Produtor(BoundedBuffer b){
        this.b = b;
    }

    public void run(){
        for(int i = 0; i < 20; i++){
              try{
                  b.put(i);
              }catch(Exception e){
                  e.getMessage();
              }
        }
    }
}
