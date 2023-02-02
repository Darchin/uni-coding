import java.io.DataInputStream;
import java.io.IOException;

public class Receiver implements Runnable {
    final DataInputStream input_stream;
    String message;
    Receiver(DataInputStream input_stream, String user){
        this.input_stream = input_stream;
    }
    public void run(){
        while(true){
            try{
                message = input_stream.readUTF();
                System.out.println(message);
            } catch(IOException e){
                e.getStackTrace();
            }
        }
    }
}
