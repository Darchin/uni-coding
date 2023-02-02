package Battleship;

import javax.swing.*;

import java.util.ArrayList;

import static Battleship.Settings.*;

public class Gameboard_L2 extends JPanel {
    private JLabel board_label;
    private GameGrid game_grid;
    private final boolean mode;
    private Gameboard_L0 layer_zero_reference;
    public Gameboard_L2(boolean mode,Gameboard_L0 layer_zero_reference){
        this.layer_zero_reference = layer_zero_reference;
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setOpaque(false);
        this.mode = mode;
        populateLayer2();
    }
    private void createGameGrid(){
        game_grid = new GameGrid(layer_zero_reference, mode);
        game_grid.setGridActive(false);
    }
    private void initializeBoardTitleJLabel(){
        board_label = new JLabel();
        board_label.setFont(BOARD_TITLE);
        board_label.setBackground(_CELL);
        board_label.setOpaque(true);
    }
    private void createBoardTitleJLabel(){
        if(!mode){
            board_label.setText("YOUR BOARD");
            board_label.setAlignmentX(0.33f);
        }
        else {
            board_label.setText("OPPONENT'S BOARD");
            board_label.setAlignmentX(0.40f);
        }
    }
    public void setGridActive(boolean state){game_grid.setGridActive(state);}
    public void setGameOverState(boolean state){
        game_grid.setGameOverState(state);
    }
    private void configureLayer2(){
        if(!mode){
            setBorder(BorderFactory.createEmptyBorder(10,0,10,20));
        }
        else {setBorder(BorderFactory.createEmptyBorder(10,20,0,20));}
    }
    private void populateLayer2(){
        createGameGrid();
        initializeBoardTitleJLabel();
        createBoardTitleJLabel();
        configureLayer2();
        this.add(board_label);
        this.add(game_grid);
    }
    public ArrayList<JLabel> getShipTileList(){
        return game_grid.getShipTileList();
    }
    public void clearShipTileList(){
        game_grid.clearShipTileList();
    }
    public String hitDetector(int tile_number){
        return game_grid.hitDetector(tile_number);
    }
    public void tileMarker(String result,boolean mode){
        game_grid.tileMarker(result, mode);
    }
    public void addShipToList(){
        game_grid.addShipToList();
    }
    public void resetGrid(){
        game_grid.resetGrid();
    }
}
