package Battleship;

import java.io.DataInputStream;
import java.io.IOException;

public class Receiver implements Runnable {
    static DataInputStream input_stream;
    String message;
    int tile_number;
    private String result = "";
    Receiver(DataInputStream input_stream){
        this.input_stream = input_stream;
    }
    private boolean isNumber(String message){
        try {
            Integer.parseInt(message);
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }
    public void run(){
        while(true){
            try{
                message = input_stream.readUTF();
                if (message.contains("UNCHECKED")) {
                    message = message.substring(9);
                    Battleship.addLog(message);
                    continue;
                }
                if (message.equals("GAMEOVER")){
                    Battleship.setGridActive(false,false);
                    Battleship.setGridActive(false,true);
                    Battleship.endGameScreen(true);
                }
                if (message.equals("NEWGAME")) Battleship.restartGame();
                if (message.equals("ShipPlacement: Complete")){
                    Battleship.addLog("Your opponent has finished placing their ships.");
                    Battleship.setFinishedPlacementFlag(true);
                    Battleship.setCoverText("Opponent is ready.",true);
                } else if (message.equals("ShipPlacementStage: Complete")) {
                    Battleship.addLog("Both sides have finished placing their ships, starting the game...");
                    Battleship.setCoverActive(false,true);
                    Battleship.setGridActive(true,true);
                    Battleship.setGameOverState(false);
                    Battleship.addLog("You will go first.");
                    Battleship.setLayoutButtonsVisible(false);
                } else if (isNumber(message)) {
                    tile_number = Integer.parseInt(message);
                    result = Battleship.hitDetector(tile_number);
                    Battleship.sendLog("Result: "+result);
                    Battleship.addLog("Your turn to attack!");
                    Battleship.setGridActive(true,true);
                    Battleship.tileMarker(result,false);
                } else if (message.contains("Result")) {
                    result = message.substring(8);
                    Battleship.tileMarker(result,true);
                    Battleship.addLog("Opponent's turn to attack!");
                } else if (message.contains("Crossout")) {
                    message = message.substring("Crossout".length());
                    Battleship.crossOutDestroyedShipsLabel(Integer.parseInt(message),true);
                }
            } catch(IOException e){
                e.getStackTrace();
            }
        }
    }
}

