package Battleship;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BattleshipClient {
    Socket socket;
    DataInputStream input_stream;
    DataOutputStream output_stream;
    Scanner Reader = new Scanner(System.in);
    static Thread game_thread;
    private static Thread SenderThread;
    private static Thread ReceiverThread;
    public BattleshipClient(String ip, int port) {
        Battleship.addLog("Connecting...");
        try {
            socket = new Socket(ip, port);
            input_stream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output_stream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            Battleship.displayErrorDialogue();
            System.exit(0);
        }
        Battleship.addLog("Connected.");
        Battleship.goToPlacementStage();
        SenderThread = new Thread(new Sender(output_stream));
        ReceiverThread = new Thread(new Receiver(input_stream));
        SenderThread.start();
        ReceiverThread.start();
        Battleship.setNewGameButtonActive(true);
    }

    public static void main(String[] args) {
        game_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Battleship.main(args);
            }
        });
        game_thread.start();
            new BattleshipClient("127.0.0.1",5000);
    }
}