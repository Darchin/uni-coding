import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Sender implements Runnable{
    final DataOutputStream output_stream;
    String message;
    static Scanner Reader = new Scanner(System.in);

    Sender(DataOutputStream output_stream, String user){
        this.output_stream = output_stream;
    }
    public void run(){
        while(true){
            try{
                message = Reader.nextLine();
                output_stream.writeUTF(message);
            } catch(IOException e){
                e.getStackTrace();
            }
        }
    }
}
