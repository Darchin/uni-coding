package Battleship;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Battleship.Settings.*;


public class Gameboard_L1 extends JPanel {
    public BoardSidePanel board_side_panel;
    private Gameboard_L2 gameboard_layer2;
    private JPanel inner_left;
    private JPanel inner_right;
    private final boolean mode;
    private Gameboard_L0 layer_zero_reference;

    public Gameboard_L1(boolean mode, Gameboard_L0 layer_zero_reference){
        this.layer_zero_reference = layer_zero_reference;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(500,PANEL_HEIGHT));
        this.setOpaque(false);
        this.mode = mode;
        initializeInnerPanels();
        this.add(inner_left);
        this.add(inner_right);
        initializeLayer2();
        initializeBoardSidePanel();
    }
    private void initializeInnerPanels(){
        inner_left = new JPanel();
        inner_left.setOpaque(false);
        inner_right = new JPanel();
        inner_right.setOpaque(false);
        if(!mode){
            inner_left.setBounds(0,0,175,PANEL_HEIGHT);
            inner_right.setBounds(175,0,325,PANEL_HEIGHT);
        }
        else {
            inner_left.setBounds(0,0,325,PANEL_HEIGHT);
            inner_right.setBounds(325,0,175,PANEL_HEIGHT);
        }
    }
    private void initializeLayer2(){
        gameboard_layer2 = new Gameboard_L2(mode,layer_zero_reference);
    }
    private void initializeBoardSidePanel(){
        board_side_panel = new BoardSidePanel(mode,layer_zero_reference);
    }
    public void setGridActive(boolean state){gameboard_layer2.setGridActive(state);}
    public void setGameOverState(boolean state){
        gameboard_layer2.setGameOverState(state);
    }
    public void setLayoutButtonsActive(boolean state){board_side_panel.setLayoutButtonsActive(state);}
    public void setConfirmButtonActive(boolean state){
        board_side_panel.setConfirmButtonActive(state);
    }
    public void setClearButtonActive(boolean state){
        board_side_panel.setClearButtonActive(state);
    }
    public void populateLayer1(boolean mode){
        if(!mode){
            inner_right.add(gameboard_layer2);
            inner_left.add(board_side_panel);
        }
        else {
            inner_left.add(gameboard_layer2);
            inner_right.add(board_side_panel);
        }
    }
    public void setLayoutButtonsVisible(boolean state){
        board_side_panel.setLayoutButtonsVisible(state);
    }
    public ArrayList<JLabel> getShipTileList(){
        return gameboard_layer2.getShipTileList();
    }
    public void clearShipTileList(){
        gameboard_layer2.clearShipTileList();
    }
    public void addShipToList(){
        gameboard_layer2.addShipToList();
    }
    public String hitDetector(int tile_number){
        return gameboard_layer2.hitDetector(tile_number);
    }
    public void tileMarker(String result,boolean mode){
        gameboard_layer2.tileMarker(result, mode);
    }
    public void crossOutDestroyedShipsLabel(int type){
        board_side_panel.crossOutDestroyedShipsLabel(type);
    }
    public void setShipLabelsToDefault(){
        board_side_panel.setShipLabelsToDefault();
    }
    public void resetGrid(){
        gameboard_layer2.resetGrid();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints qualityHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );
        qualityHints.put(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY );
        g2d.setRenderingHints( qualityHints );

        if(!mode){
            g2d.setColor(_BOARDS);
            g2d.fillRoundRect(0,0,500,PANEL_HEIGHT,30,30);
            g2d.setColor(_CELL);
            g2d.fillRoundRect(175,0,325,PANEL_HEIGHT,30,30);
        }
        else {
            g2d.setColor(_BOARDS);
            g2d.fillRoundRect(0,0,500,PANEL_HEIGHT,30,30);
            g2d.setColor(_CELL);
            g2d.fillRoundRect(0,0,325,PANEL_HEIGHT,30,30);
        }
    }
}
