import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    Socket socket;
    DataInputStream input_stream;
    DataOutputStream output_stream;
    Scanner Reader = new Scanner(System.in);
    Thread sender;
    Thread receiver;

    Client(String ip, int port){
        try {
            Socket socket = new Socket(ip,port);
            System.out.println("Connected to server.\n");
            input_stream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output_stream = new DataOutputStream(socket.getOutputStream());
        }
        catch (UnknownHostException e){
            System.out.println("Could not find host with given IP address.");
            System.exit(-1);
        }
        catch (IOException e){
            System.out.println("Connection to server has failed, exiting...");
            System.exit(-1);
        }

        sender = new Thread(new Runnable(){
            public void run(){
                String message;
                while(true){
                    try{
                        message = Reader.nextLine();
                        output_stream.writeUTF("Client: "+message);
                        if(message.equals("EXIT")){
                            break;
                        }
                    } catch(IOException e){
                        e.getStackTrace();
                    }
                }
                try {
                    input_stream.close();
                    output_stream.close();
                    System.out.println("Connection closed, exiting...");
                    System.exit(0);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });

        receiver = new Thread(new Runnable(){
            public void run(){
                String message;
                while(true){
                    try{
                        message = input_stream.readUTF();
                        System.out.println(message);
                    } catch(IOException e){
                        e.getStackTrace();
                    }
                }
            }
        });
        receiver.start();
        sender.start();
    }
    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1",500);
    }
}
