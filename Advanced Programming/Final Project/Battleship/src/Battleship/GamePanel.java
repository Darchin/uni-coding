package Battleship;

import javax.swing.*;

import java.awt.*;

import static Battleship.Settings.*;

public class GamePanel extends JPanel {
    static JPanel TOP;
    static JPanel LEFT;
    static JPanel RIGHT;
    static JPanel BOTTOM;
    JLabel game_title;
    BottomPanel bottom_panel;
    Gameboard_L0 your_board;
    Gameboard_L0 opponents_board;

    public GamePanel() {
        this.setLayout(null);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(_MAIN);

        initializeMainJPanels();
        populateTopJPanel();
        populateLeftJPanel();
        populateRightJPanel();
        populateBottomJPanel();

        this.add(TOP);
        this.add(LEFT);
        this.add(RIGHT);
        this.add(BOTTOM);
    }

    private void initializeMainJPanels() {
        TOP = new JPanel();
        TOP.setOpaque(false);
        TOP.setBounds(386, 0, 508, 141);

        LEFT = new JPanel();
        LEFT.setOpaque(false);
        LEFT.setBounds(100, 141, 500, PANEL_HEIGHT);

        RIGHT = new JPanel();
        RIGHT.setOpaque(false);
        RIGHT.setBounds(680, 141, 500, PANEL_HEIGHT);

        BOTTOM = new JPanel();
        BOTTOM.setOpaque(false);
        BOTTOM.setBounds(0, 520, 1280, 200);
    }

    public void populateTopJPanel() {
        game_title = new JLabel(battleship_title);
        TOP.add(game_title);
    }

    public void populateLeftJPanel() {
        your_board = new Gameboard_L0("left");
        your_board.setCoverText("You win!");
        your_board.populateLayer0();
        LEFT.add(your_board);
//        your_board.removeLayoutButtons();
    }

    public void populateRightJPanel() {
        opponents_board = new Gameboard_L0("right");
        opponents_board.setCoverText("Waiting for an opponent to join...");
        opponents_board.populateLayer0();
        opponents_board.setCoverActive(true,true);
        RIGHT.add(opponents_board);
    }

    public void populateBottomJPanel() {
        bottom_panel = new BottomPanel();
        BOTTOM.add(bottom_panel);
    }

    public static Dimension getPanelSize(String component_name) {
        switch (component_name) {
            case "BOTTOM":
                return BOTTOM.getSize();
            case "TOP":
                return TOP.getSize();
            case "LEFT":
                return LEFT.getSize();
            case "RIGHT":
                return RIGHT.getSize();
            default:
                return new Dimension(0, 0);
        }
    }

    public void addLog(String text) {
        bottom_panel.addLog(text);
    }

    public void setCoverText(String cover_text, boolean mode) {
        if (!mode) {
            your_board.setCoverText(cover_text);
        } else {
            opponents_board.setCoverText(cover_text);
        }
    }
    public void setCoverActive(boolean state, boolean mode){
        if (!mode) your_board.setCoverActive(state,false);
        else opponents_board.setCoverActive(state,true);
    }
    public void setLayoutButtonsVisible(boolean state){
        your_board.setLayoutButtonsVisible(state);
    }
    public void setLayoutButtonsActive(boolean state){
        your_board.setLayoutButtonsActive(state);}
    public void setConfirmButtonActive(boolean state){
        your_board.setConfirmButtonActive(state);
    }
    public void setClearButtonActive(boolean state){
        your_board.setClearButtonActive(state);
    }
    public void setGridActive(boolean state, boolean mode){
        if(!mode){your_board.setGridActive(state);}
        else {opponents_board.setGridActive(state);}
    }
    public void setGameOverState(boolean state){
        opponents_board.setGameOverState(state);
    }
    public String hitDetector(int tile_number){
        return your_board.hitDetector(tile_number);
    }
    public void tileMarker(String result,boolean mode){
        if (!mode){
            your_board.tileMarker(result, false);
        } else {
            opponents_board.tileMarker(result, true);
        }
    }
    public void crossOutDestroyedShipsLabel(int type,boolean mode){
        if (!mode){your_board.crossOutDestroyedShipsLabel(type);}
        else {opponents_board.crossOutDestroyedShipsLabel(type);}
    }
    public void setShipLabelsToDefault(boolean mode){
        if (!mode) your_board.setShipLabelsToDefault();
        else opponents_board.setShipLabelsToDefault();
    }
    public void setNewGameButtonActive(boolean state){
        bottom_panel.setNewGameButtonActive(state);
    }
    public void resetGrid(){
        your_board.resetGrid();
        opponents_board.resetGrid();
    }
    public void clearLogs(){
        bottom_panel.clearLogs();
    }
}