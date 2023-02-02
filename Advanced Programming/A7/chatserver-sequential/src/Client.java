import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client implements Runnable{
    Socket socket;
    DataInputStream input_stream;
    DataOutputStream output_stream;
    Scanner Reader = new Scanner(System.in);

    Client(String ip, int port) {
        System.out.println("Connecting...");
        try {
            socket = new Socket(ip, port);
            input_stream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output_stream = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
            System.out.println(e);
        }
        System.out.println("Connected.\n");
    }

    public void run(){
        String message_sent = "";
        String message_received = "";
        while(true){
            try {
                System.out.print("Client: ");
                message_sent = Reader.nextLine();
                output_stream.writeUTF(message_sent);
                if (message_sent.equals("EXIT")){
                    break;
                }
                message_received = input_stream.readUTF();
                System.out.println("Server: "+message_received);
            }
            catch (IOException e){
                e.getStackTrace();
            }
        }
        System.out.println("Sent exit command, closing connection...");
        try {
            input_stream.close();
            output_stream.close();;
            System.exit(0);
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        Thread client = new Thread(new Client("127.0.0.1", 500));
        client.start();
    }
}
