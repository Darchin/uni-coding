import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {

    // Fields
    private ServerSocket entry_socket;
    Socket socket;
    DataInputStream input_stream;
    DataOutputStream output_stream;
    Scanner Reader = new Scanner(System.in);
    Thread sender;
    Thread receiver;

    // Constructors
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

        sender = new Thread(new Runnable() {
            public void run() {
                String message;
                while (true) {
                    try {
                        message = Reader.nextLine();
                        output_stream.writeUTF("Server: " + message);
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                }
            }
        });
        receiver = new Thread(new Runnable() {
            public void run() {
                String message;
                while (true) {
                    try {
                        message = input_stream.readUTF();
                        System.out.println(message);
                        if (message.equals("Client: EXIT")) {
                            break;
                        }
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                }
                try {
                    System.out.println("Client sent exit command, closing the connection...");
                    input_stream.close();
                    output_stream.close();
                    socket.close();
                    System.out.println("Connection Closed, exiting...");
                    System.exit(0);
                } catch (IOException e) {
                    System.out.println("Failed to close connection, terminating program.");
                    System.exit(-1);
                }

            }
        });
        receiver.start();
        sender.start();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(500);
    }
}