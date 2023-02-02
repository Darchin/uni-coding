package Battleship;

import javax.swing.*;

public class Battleship {
    private static GameForm game_form;
    private static boolean FinishedPlacementFlag = false;
    public static void main(String[] args) {
//        new Settings();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    game_form = new GameForm();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public static void addLog(String text) {
        while(game_form == null){
            try {
                // Doesn't work unless you put something in while block
                Thread.sleep(1);
            } catch (Exception e){e.getStackTrace();}
        }
        game_form.addLog(text);
    }
    public static void sendLog(String text) {
        Sender.sendLog(text);
    }
    public static void sendLogUnchecked(String text) {
        Sender.sendLogUnchecked(text);
    }
    public static void displayErrorDialogue(){
        game_form.displayErrorDialogue();
    }
    public static void goToPlacementStage() {
        setCoverText("Opponent is placing their ships...",true);
        addLog("> Ship placement stage <");
        addLog("Select 5 tiles for the Carrier unit");
        setGridActive(true,false);
        setLayoutButtonsVisible(true);
    }

    public static void setCoverText(String cover_text, boolean mode){
        game_form.setCoverText(cover_text, mode);
    }
    public static void setCoverActive(boolean state, boolean mode){
        game_form.setCoverActive(state, mode);
    }
    public static void setLayoutButtonsVisible(boolean state){
        game_form.setLayoutButtonsVisible(state);
    }
    public static void setLayoutButtonsActive(boolean state){
        game_form.setLayoutButtonsActive(state);}
    public static void setGridActive(boolean state, boolean mode){
        game_form.setGridActive(state,mode);}
    public static void setFinishedPlacementFlag(boolean state){FinishedPlacementFlag = state;}
    public static void sendPlacementFinishedFlag(){
        if (!FinishedPlacementFlag){
            addLog("Ship layout set. Waiting for opponent to finish placing their ships...");
            sendLog("ShipPlacement: Complete");
            setGridActive(false,false);
            setLayoutButtonsActive(false);

        } else {
            sendLog("ShipPlacementStage: Complete");
            addLog("Ship layout set");
            addLog("Both sides have finished placing their ships, starting the game...");
            setCoverActive(false,true);
            setGridActive(false,false);
            setGridActive(false,true);
            setGameOverState(false);
            addLog("The opponent will go first.");
            setLayoutButtonsVisible(false);
        }
    }
    public static String hitDetector(int tile_number){
        return game_form.hitDetector(tile_number);
    }
    public static void tileMarker(String result,boolean mode){
        game_form.tileMarker(result, mode);
    }
    public static void endGameScreen(boolean winner){
        game_form.endGameScreen(winner);
    }
    public static void crossOutDestroyedShipsLabel(int type,boolean mode){
        game_form.crossOutDestroyedShipsLabel(type,mode);
    }
    public static void setShipLabelsToDefault(boolean mode){
        game_form.setShipLabelsToDefault(mode);
    }
    public static void setConfirmButtonActive(boolean state){
        game_form.setConfirmButtonActive(state);
    }
    public static void setClearButtonActive(boolean state){
        game_form.setClearButtonActive(state);
    }
    public static void resetGrid(){
        game_form.resetGrid();
    }
    public static boolean showNewGameDialogue(){
        return game_form.showNewGameDialogue();
    }
    public static void setNewGameButtonActive(boolean state){
        game_form.setNewGameButtonActive(state);
    }
    public static void clearLogs(){
        game_form.clearLogs();
    }
    public static void setGameOverState(boolean state){
        game_form.setGameOverState(state);
    }
    public static void restartGame(){
        setCoverText("Opponent is placing their ships...",true);
        setCoverActive(true,true);
        setCoverActive(false,false);
        setGridActive(true,false);
        setLayoutButtonsVisible(true);
        setConfirmButtonActive(false);
        setClearButtonActive(true);
        setFinishedPlacementFlag(false);
        setShipLabelsToDefault(false);
        setShipLabelsToDefault(true);
        resetGrid();
        clearLogs();
        addLog("A new game has begun...");
        addLog("> Ship placement stage <");
        addLog("Select 5 tiles for the Carrier unit");
    }
}