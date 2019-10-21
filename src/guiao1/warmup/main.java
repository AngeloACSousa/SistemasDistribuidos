package guiao1.warmup;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class main {
    public static void main(String args[]){
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("> ");
            while((line = in.readLine())!= null){
            System.out.println("Echo: " + line);
            System.out.println("> ");
            }
        }catch (IOException e){
            System.out.print(e.getMessage());
        }
        }

}
