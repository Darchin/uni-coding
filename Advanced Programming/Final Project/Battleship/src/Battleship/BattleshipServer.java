package Battleship;

import java.io.*;
import java.net.*;

public class BattleshipServer {
    private final ServerSocket entry_socket;
    Socket socket;
    DataInputStream input_stream;
    DataOutputStream output_stream;
//    Scanner Reader = new Scanner(System.in);
    static Thread game_thread;
    private static Thread SenderThread;
    private static Thread ReceiverThread;
    public BattleshipServer(int port) throws IOException {
        entry_socket = new ServerSocket(port);
        Battleship.addLog("Waiting for opponent to join...");
        try {
            socket = entry_socket.accept();
            Battleship.addLog("Client connected.");
            input_stream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output_stream = new DataOutputStream(socket.getOutputStream());
            Battleship.goToPlacementStage();
        } catch (Exception e) {
            socket.close();
            e.printStackTrace();
        }
        SenderThread = new Thread(new Sender(output_stream));
        ReceiverThread = new Thread(new Receiver(input_stream));
        SenderThread.start();
        ReceiverThread.start();
        Battleship.setNewGameButtonActive(true);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        game_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Battleship.main(args);
            }
        });
        game_thread.start();
        new BattleshipServer(5000);
    }
}
