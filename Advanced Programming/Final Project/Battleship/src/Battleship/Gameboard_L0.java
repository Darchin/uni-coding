package Battleship;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static Battleship.Settings._CELL;

public class Gameboard_L0 extends JPanel {
    private Gameboard_L1 gameboard_layer1;
    private Cover cover;
    private String cover_text;
    private Gameboard_L0 layer_zero_reference;
    private boolean mode;

    public Gameboard_L0(String location){
        this.layer_zero_reference = this;
        this.setLayout(new OverlayLayout(this));
        this.setOpaque(false);
        if(location.equals("left")) mode = false;
        if(location.equals("right")) mode = true;
    }
    private void setCover(){
        cover = new Cover(cover_text);
        cover.setVisible(false);
    }
    private void setBoard(){
        gameboard_layer1 = new Gameboard_L1(mode,layer_zero_reference);
        gameboard_layer1.populateLayer1(mode);
    }
    public void setContents(){
        setCover();
        setBoard();
    }
    public void setCoverText(String cover_text){
        this.cover_text = cover_text;
        if(cover != null){
            cover.setCover_text(cover_text);
//            cover.revalidate();
//            cover.repaint();
        }
    }
    public void populateLayer0(){
        setContents();
        this.add(cover);
        this.add(gameboard_layer1);
    }
    public void setGridActive(boolean state){
        gameboard_layer1.setGridActive(state);}
    public void setGameOverState(boolean state){
        gameboard_layer1.setGameOverState(state);
    }
    public void setLayoutButtonsActive(boolean state){
        gameboard_layer1.setLayoutButtonsActive(state);}
    public void setConfirmButtonActive(boolean state){
        gameboard_layer1.setConfirmButtonActive(state);
    }
    public void setClearButtonActive(boolean state){
        gameboard_layer1.setClearButtonActive(state);
    }
    public void setLayoutButtonsVisible(boolean state){
        gameboard_layer1.setLayoutButtonsVisible(state);
    }
    public void setCoverActive(boolean state, boolean mode){
        if (!mode){
            setLayoutButtonsActive(!state);
        }
        setGridActive(!state);
        cover.setVisible(state);
    }
    public ArrayList<JLabel> getShipTileList(){
        return gameboard_layer1.getShipTileList();
    }
    public void clearShipTileList(){
        gameboard_layer1.clearShipTileList();
    }
    public void addShipToList(){
        gameboard_layer1.addShipToList();
    }
    public String hitDetector(int tile_number){
        return gameboard_layer1.hitDetector(tile_number);
    }
    public void tileMarker(String result,boolean mode){
        gameboard_layer1.tileMarker(result, mode);
    }
    public void crossOutDestroyedShipsLabel(int type){
        gameboard_layer1.crossOutDestroyedShipsLabel(type);
    }
    public void setShipLabelsToDefault(){
        gameboard_layer1.setShipLabelsToDefault();
    }
    public void resetGrid(){
        gameboard_layer1.resetGrid();
    }
}
