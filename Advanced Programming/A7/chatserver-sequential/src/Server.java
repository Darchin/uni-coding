import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server implements Runnable{
    private ServerSocket entry_socket;
    Socket socket;
    DataInputStream input_stream;
    DataOutputStream output_stream;
    Scanner Reader = new Scanner(System.in);

    Server(int port) throws IOException {
        entry_socket = new ServerSocket(port);
        System.out.println("Server started, awaiting client...");
        try {
            socket = entry_socket.accept();
            System.out.println("Client connected.\n");
            input_stream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output_stream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            socket.close();
            System.out.println(e);
        }
    }

    public void run(){
        String message;
        while(true){
            String message_sent = "";
            String message_received = "";
            try {
                message_received = input_stream.readUTF();
                System.out.println("Client: "+message_received);
                if (message_received.equals("EXIT")){
                    break;
                }
                System.out.print("Server: ");
                message_sent = Reader.nextLine();
                output_stream.writeUTF(message_sent);
            }
            catch (IOException e){
                e.getStackTrace();
            }
        }
        System.out.println("EXIT command received, closing connection...");
        try {
            socket.close();
            input_stream.close();
            output_stream.close();
            System.exit(0);
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        Thread server = new Thread(new Server(500));
        server.start();
    }
}