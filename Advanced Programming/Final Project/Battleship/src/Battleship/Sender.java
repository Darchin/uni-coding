package Battleship;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Sender implements Runnable{
    static DataOutputStream output_stream;
//    String message;
//    static Scanner Reader = new Scanner(System.in);

    Sender(DataOutputStream output_stream){
        this.output_stream = output_stream;
    }
    public static void sendLog(String text){
        try{output_stream.writeUTF(text);}
        catch (IOException e) {e.getStackTrace();}
    }
    public static void sendLogUnchecked(String text){
        try{output_stream.writeUTF("UNCHECKED"+text);}
        catch (IOException e) {e.getStackTrace();}
    }
    public void run(){
/*        while(true){
            try{
                message = Reader.nextLine();
                output_stream.writeUTF(message);
            } catch(IOException e){
                e.getStackTrace();
            }
        }*/
    }
}
