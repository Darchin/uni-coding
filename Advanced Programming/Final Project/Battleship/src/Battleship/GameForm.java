package Battleship;

import javax.swing.*;
import static Battleship.Settings.*;

public class GameForm extends JFrame {
    GamePanel game_panel;
    public GameForm() {
        game_panel = new GamePanel();
        this.setTitle("Battleship");
        this.setIconImage((battleship_titlebar).getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(ADJUSTED_SCREEN_SIZE);
        this.add(game_panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    public void addLog(String text){
        game_panel.addLog(text);
    }
    public void displayErrorDialogue(){
        JOptionPane.showMessageDialog(this, "Connection refused...","Connection Failure",JOptionPane.ERROR_MESSAGE);
    }
    public void setCoverText(String cover_text, boolean mode){
        game_panel.setCoverText(cover_text, mode);
    }
    public void setCoverActive(boolean state, boolean mode){
        game_panel.setCoverActive(state, mode);
    }
    public void setLayoutButtonsVisible(boolean state){
        game_panel.setLayoutButtonsVisible(state);
    }
    public void setLayoutButtonsActive(boolean state){
        game_panel.setLayoutButtonsActive(state);}
    public void setConfirmButtonActive(boolean state){
        game_panel.setConfirmButtonActive(state);
    }
    public void setClearButtonActive(boolean state){
        game_panel.setClearButtonActive(state);
    }
    public void setGridActive(boolean state, boolean mode){
        game_panel.setGridActive(state,mode);}
    public void setGameOverState(boolean state){
        game_panel.setGameOverState(state);
    }
    public String hitDetector(int tile_number){
        return game_panel.hitDetector(tile_number);
    }
    public void tileMarker(String result, boolean mode){
        game_panel.tileMarker(result, mode);
    }
    public void endGameScreen(boolean winner){
        setGameOverState(true);
        if (winner){
            Battleship.addLog("You won!");
            setCoverText("You won!",false);
            setCoverActive(true,false);
        } else {
            Battleship.addLog("You lost!");
            setCoverText("You lost!",false);
            setCoverActive(true,false);
        }
    }
    public void crossOutDestroyedShipsLabel(int type,boolean mode){
        game_panel.crossOutDestroyedShipsLabel(type,mode);
    }
    public void setShipLabelsToDefault(boolean mode){
        game_panel.setShipLabelsToDefault(mode);
    }
    public void resetGrid(){
        game_panel.resetGrid();
    }
    public void setNewGameButtonActive(boolean state){
        game_panel.setNewGameButtonActive(state);
    }
    public boolean showNewGameDialogue(){
        int result = JOptionPane.showConfirmDialog(this,"Are you sure you want to start a new game?", "New game",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            return true;
        }else if (result == JOptionPane.NO_OPTION){
            return false;
        }else {
            return false;
        }
    }
    public void clearLogs(){
        game_panel.clearLogs();
    }
}