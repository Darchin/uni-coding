package Battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static Battleship.Settings.*;

public class GameGrid extends JPanel {
    private JLabel tile;
    private static int current_ship_size = 5;
    private ArrayList<Integer> ship_list_by_index = new ArrayList<>();
    private Iterator<Integer> ship_list_by_index_iterator;
    public ArrayList<JLabel> grid_cells = new ArrayList<>();
    public String[] grid_cell_coordinates = new String[121];
    private ToggleableMouseListener grid_mouse_listener;
//    private ToggleableMouseListener right_grid_mouse_listener;
    public ArrayList<JLabel> ship_tile_list = new ArrayList<>();
    public ArrayList<JLabel> carrier_tiles = new ArrayList<>();
    public ArrayList<JLabel> battleship_tiles = new ArrayList<>();
    public ArrayList<JLabel> cruiser_tiles = new ArrayList<>();
    public ArrayList<JLabel> submarine_tiles = new ArrayList<>();
    public ArrayList<JLabel> destroyer_tiles = new ArrayList<>();
    public ArrayList<Integer> ship_tile_index_list = new ArrayList<>();
    public ArrayList<ArrayList<JLabel>> ship_list = new ArrayList<>();
    public ArrayList<ArrayList<JLabel>> named_ship_list = new ArrayList<>();
    private ArrayList<Integer> attacked_ship_tiles = new ArrayList<>();
    private int successful_shots = 0;
    private Gameboard_L0 layer_zero_reference;
//    private Color tile_color;

    public GameGrid(Gameboard_L0 layer_zero_reference, boolean mode) {
        ship_list_by_index.add(0);
        ship_list_by_index.add(1);
        ship_list_by_index.add(2);
        ship_list_by_index.add(3);
        ship_list_by_index.add(4);
        this.layer_zero_reference = layer_zero_reference;
        this.setLayout(new GridLayout(ROW_COUNT, COL_COUNT, 1, 1));
        this.setBackground(_CELL);

        tile = createEmptyTile();
        this.add(tile);

        for (int i = 1; i < 11; i++) {
            tile = createTile();
            tile.setName(Integer.toString(i));
            tile.setText(String.valueOf((char) (64 + i)));
            grid_cell_coordinates[i] = tile.getText();
            tile.setVerticalAlignment(SwingConstants.BOTTOM);
            tile.setHorizontalAlignment(SwingConstants.CENTER);
            tile.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));
            this.add(tile);
        }
        for (int i = 11; i < ROW_COUNT * COL_COUNT; i++) {
            if (i % 11 == 0) {
                tile = createTile();
                tile.setName(Integer.toString(i));
                tile.setText(Integer.toString(i / 11));
                grid_cell_coordinates[i] = tile.getText();
                tile.setVerticalAlignment(SwingConstants.CENTER);
                tile.setHorizontalAlignment(SwingConstants.RIGHT);
                tile.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3));
                this.add(tile);
                continue;
            }
            tile = createTile();
            grid_cell_coordinates[i] = String.format("%c%d", (char) (64 + (i % 11)),Math.floorDiv(i,11));
            tile.setName(Integer.toString(i));
            this.add(tile);
        }
        addGridActionListener(mode);
    }

    private JLabel createEmptyTile() {
        JLabel tile = new JLabel();
        tile.setBorder(null);
        tile.setName("0");
        tile.setOpaque(true);
        tile.setBackground(_CELL);
        grid_cells.add(tile);
        grid_cell_coordinates[0] = "";
        return tile;
    }

    public JLabel createTile() {
        JLabel tile = new JLabel();
        tile.setPreferredSize(CELL_SIZE);
        tile.setOpaque(true);
        tile.setBackground(_CELL);
        tile.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        grid_cells.add(tile);
        return tile;
    }

    public <T extends Component> int getIndex(T tile) {
        return Integer.parseInt(tile.getName());
    }

    public int getLast(ArrayList<Integer> ship_tile_index_list) {
        return ship_tile_index_list.get(ship_tile_index_list.size() - 1);
    }

    public <T extends Component> void addTileIndex(ArrayList<Integer> ship_tile_index_list, T tile) {
        ship_tile_index_list.add(getIndex(tile));
        Collections.sort(ship_tile_index_list);
    }

    public boolean checkFirstAndLast(ArrayList<Integer> ship_tile_index_list, JLabel new_tile, int difference) {
        return (Math.abs(ship_tile_index_list.get(0) - getIndex(new_tile)) == difference)
                || (Math.abs(getLast(ship_tile_index_list) - getIndex(new_tile)) == difference);
    }

    public boolean isSameOrientation(ArrayList<Integer> ship_tile_index_list, JLabel new_tile) {
        boolean isHorizontal = false;
        // check if the tile is already in list
        for (int tile_coord : ship_tile_index_list) {
            if (getIndex(new_tile) == tile_coord) return false;
        }
        // check whether ship is vertical or horizontal
        if (Math.abs(ship_tile_index_list.get(0) - ship_tile_index_list.get(1)) == 1)
            isHorizontal = true;
        if (isHorizontal) {
            return checkFirstAndLast(ship_tile_index_list, new_tile, 1);
        }
        return checkFirstAndLast(ship_tile_index_list, new_tile, 11);
    }

    public void addGridActionListener(boolean mode) {
        if (!mode) {
            grid_mouse_listener = new ToggleableMouseListener() {
                @Override
                protected void actionOnPress(MouseEvent e) {
                    checkAndPlaceTiles(e);
                }
            };
        } else {
            grid_mouse_listener = new ToggleableMouseListener() {
                @Override
                protected void actionOnPress(MouseEvent e) {
                    checkIfHit(e);
                }
            };
        }
        this.addMouseListener(grid_mouse_listener);
    }
    public void setGridActive(boolean state) {
        grid_mouse_listener.setActive(state);
    }
    public void setGameOverState(boolean state){
        grid_mouse_listener.setGameOverState(state);
    }
    private void checkAndPlaceTiles(MouseEvent e) {
        Component comp = getComponentAt(e.getPoint());
        if (comp instanceof JLabel) {
            // Check grid boundaries (1-10, A-J)
            if ((getIndex(comp) < 11) || (getIndex(comp) % 11 == 0)) return;
            tile = (JLabel) comp;
            // Check if tile is filled
            if (checkIfTileIsFilled(tile)) return;
            // Place first tile unconditionally
            if (ship_tile_list.size() == 0) {
                tile.setIcon(anchor);
                tile.setHorizontalTextPosition(SwingConstants.CENTER);
                ship_tile_list.add(tile);
                addTileIndex(ship_tile_index_list, tile);
                tile.revalidate();
                tile.repaint();
                // Place second tile only if it is not diagonal to first
            } else if (ship_tile_list.size() == 1) {
                if (Math.abs(ship_tile_index_list.get(0) - getIndex(tile)) == 1 || Math.abs(ship_tile_index_list.get(0) - getIndex(tile)) == 11) {
                    tile.setIcon(anchor);
                    ship_tile_list.add(tile);
                    addTileIndex(ship_tile_index_list, tile);
//                    System.out.println(ship_tile_list.size());
                    tile.revalidate();
                    tile.repaint();
                    if (current_ship_size == 2) {
                        layer_zero_reference.setConfirmButtonActive(true);
                    }
                }
                // Place the second tile onward if its orientation is aligned
            } else if (ship_tile_list.size() < current_ship_size) {
                if (isSameOrientation(ship_tile_index_list, tile)) {
                    tile.setIcon(anchor);
                    ship_tile_list.add(tile);
                    addTileIndex(ship_tile_index_list, tile);
                    tile.revalidate();
                    tile.repaint();
                    if (ship_tile_list.size() == current_ship_size) {
                        layer_zero_reference.setConfirmButtonActive(true);
                    }
                }
            }
        }
    }
    private void checkIfHit(MouseEvent e){
        Component comp = getComponentAt(e.getPoint());
        if (comp instanceof JLabel) {
            // Check grid boundaries (1-10, A-J)
            if ((getIndex(comp) < 11) || (getIndex(comp) % 11 == 0)) return;
            if (attacked_ship_tiles.contains(getIndex(comp))) return;
            Battleship.setGridActive(false,true);
            tile = (JLabel) comp;
            attacked_ship_tiles.add(getIndex(tile));
            Battleship.addLog("You shot at "+grid_cell_coordinates[getIndex(tile)]+".");
            Battleship.sendLogUnchecked("Enemy shot at "+grid_cell_coordinates[getIndex(tile)]+".");
            Battleship.sendLog(Integer.toString(getIndex(tile)));
        }
    }
    public String hitDetector(int tile_number){
        ship_list_by_index_iterator = ship_list_by_index.iterator();
        String result = "";
        int current_ship;
        for (ArrayList<JLabel> ship : ship_list){
            current_ship = ship_list_by_index_iterator.next();
            for (JLabel tile : ship){
                if (tile_number==getIndex(tile)) {
                    increaseCountAndCheckIfGameIsOver();
                    result += "hit";
                    Battleship.addLog("Your ship at "+grid_cell_coordinates[tile_number]+" was hit.");
                    Battleship.sendLogUnchecked("Enemy ship at "+grid_cell_coordinates[tile_number]+" was hit.");
                    ship.remove(tile);
                    if (ship.size()==0){
                        ship_list_by_index.remove(Integer.valueOf(current_ship));
                        ship_list.remove(ship);
                        switch (current_ship){
                            case 0:
                                Battleship.addLog("Your carrier was destroyed!");
                                Battleship.sendLogUnchecked("Enemy carrier destroyed!");
                                break;
                            case 1:
                                Battleship.addLog("Your battleship was destroyed!");
                                Battleship.sendLogUnchecked("Enemy battleship destroyed!");
                                break;
                            case 2:
                                Battleship.addLog("Your cruiser destroyed!");
                                Battleship.sendLogUnchecked("Enemy cruiser destroyed!");
                                break;
                            case 3:
                                Battleship.addLog("Your submarine was destroyed!");
                                Battleship.sendLogUnchecked("Enemy submarine destroyed!");
                                break;
                            case 4:
                                Battleship.addLog("Your destroyer was destroyed!");
                                Battleship.sendLogUnchecked("Enemy destroyer destroyed!");
                                break;
                        }
                        Battleship.crossOutDestroyedShipsLabel(current_ship,false);
                        Battleship.sendLog("Crossout"+current_ship);
                        result += "&destroyed:";
                        result += Integer.toString(current_ship);
                        for (JLabel cell : named_ship_list.get(current_ship)){
                            result += Integer.toString(getIndex(cell));
                            result += ",";
                        }
                        result = result.substring(0,result.length()-1);
                    } else {
                        result += "@";
                        result += Integer.toString(tile_number);
                    }
                    if (ship_list.size()==0) {
                        Battleship.endGameScreen(false);
                    }
                    return result;
                }
            }
        }
        result += "miss";
        Battleship.addLog("Enemy shot missed!");
        Battleship.sendLogUnchecked("You missed!");
        result += "@";
        result += Integer.toString(tile_number);
        return result;
    }
    public void tileMarker(String result, boolean mode){
        JLabel tile;
        String type = "";
        if(mode){
            if (result.contains("miss")) {
                result = result.substring(5);
                tile = grid_cells.get(Integer.parseInt(result));
                tile.setIcon(cross);
                tile.revalidate();
                tile.repaint();
            } else if (result.contains("hit")) {
                if (result.contains("destroyed")){
                    result = result.substring(14);
                    type = result.substring(0,1);
                    result = result.substring(1);
                    for (String ship_tile_number : result.split(",")){
                        tile = grid_cells.get(Integer.parseInt(ship_tile_number));
                        tile.setIcon(bombwhite);
                        tile.setBackground(colorOfType(type));
                        tile.revalidate();
                        tile.repaint();
                    }
                } else {
                    result = result.substring(4);
                    tile = grid_cells.get(Integer.parseInt(result));
                    tile.setIcon(bomb);
                    tile.revalidate();
                    tile.repaint();
                }
            }
        } else {
            if (result.contains("miss")) {
                result = result.substring(5);
                tile = grid_cells.get(Integer.parseInt(result));
                tile.setIcon(cross);
                tile.revalidate();
                tile.repaint();
            } else if (result.contains("hit")) {
                if (result.contains("destroyed")){
                    result = result.substring(15);
                    for (String ship_tile_number : result.split(",")){
                        tile = grid_cells.get(Integer.parseInt(ship_tile_number));
                        tile.setIcon(bombwhite);
                        tile.revalidate();
                        tile.repaint();
                    }
                } else {
                    result = result.substring(4);
                    tile = grid_cells.get(Integer.parseInt(result));
                    tile.setIcon(bombwhite);
                    tile.revalidate();
                    tile.repaint();
                }
            }
        }
    }
    private Color colorOfType(String type){
        switch (type){
            case "0":
                return _CARRIER;
            case "1":
                return _BATTLESHIP;
            case "2":
                return _CRUISER;
            case "3":
                return _SUBMARINE;
            case "4":
                return _DESTROYER;
            default:
                return Color.WHITE;
        }
    }
    private boolean checkIfTileIsFilled(JLabel new_tile) {
        for (ArrayList<JLabel> ship_tile_list : ship_list) {
            for (JLabel tile : ship_tile_list) {
                if (getIndex(tile) == getIndex(new_tile)) return true;
            }
        }
        return false;
    }

    public ArrayList<JLabel> getShipTileList() {
        return ship_tile_list;
    }

    public void addShipToList() {
        ship_list.add(new ArrayList<JLabel>());
        for (JLabel tile : ship_tile_list) {
            ship_list.get(ship_list.size() - 1).add(tile);
        }

        ship_tile_index_list.clear();
        ship_tile_list.clear();

        if (ship_list.size() == 1) {
            for (JLabel tile : ship_list.get(0)) {
                carrier_tiles.add(tile);
                tile.setIcon(carrierShape);
                tile.setBackground(_CARRIER);
                tile.revalidate();
                tile.repaint();
            }
            Battleship.addLog("Select 4 tiles for the Battleship unit");
            current_ship_size--;
        } else if (ship_list.size() == 2) {
            for (JLabel tile : ship_list.get(1)) {
                battleship_tiles.add(tile);
                tile.setIcon(battleshipShape);
                tile.setBackground(_BATTLESHIP);
                tile.revalidate();
                tile.repaint();
            }
            Battleship.addLog("Select 3 tiles for the Cruiser unit");
            current_ship_size--;
        } else if (ship_list.size() == 3) {
            for (JLabel tile : ship_list.get(2)) {
                cruiser_tiles.add(tile);
                tile.setIcon(cruiserShape);
                tile.setBackground(_CRUISER);
                tile.revalidate();
                tile.repaint();
            }
            Battleship.addLog("Select 3 tiles for the Submarine unit");
        } else if (ship_list.size() == 4) {
            for (JLabel tile : ship_list.get(3)) {
                submarine_tiles.add(tile);
                tile.setIcon(submarineShape);
                tile.setBackground(_SUBMARINE);
                tile.revalidate();
                tile.repaint();
            }
            Battleship.addLog("Select 2 tiles for the Destroyer unit");
            current_ship_size--;
        } else if (ship_list.size() == 5) {
            for (JLabel tile : ship_list.get(4)) {
                destroyer_tiles.add(tile);
                tile.setIcon(destroyerShape);
                tile.setBackground(_DESTROYER);
                tile.revalidate();
                tile.repaint();
            }
            named_ship_list.add(carrier_tiles);
            named_ship_list.add(battleship_tiles);
            named_ship_list.add(cruiser_tiles);
            named_ship_list.add(submarine_tiles);
            named_ship_list.add(destroyer_tiles);
            setGridActive(false);
            layer_zero_reference.setLayoutButtonsActive(false);
            Battleship.sendPlacementFinishedFlag();
        }
    }

    public void clearShipTileList() {
        ship_tile_list.clear();
        ship_tile_index_list.clear();
        layer_zero_reference.setConfirmButtonActive(false);
    }
    public void increaseCountAndCheckIfGameIsOver(){
        successful_shots++;
        if (successful_shots==17){
            Battleship.sendLog("GAMEOVER");
        }
    }
    public void resetGrid(){
        for (JLabel tile : grid_cells){
            tile.setIcon(null);
            tile.setBackground(_CELL);
        }
        current_ship_size = 5;
        this.successful_shots = 0;
        for (ArrayList<JLabel> ship : named_ship_list){
            ship.clear();
        }
        ship_list_by_index.clear();
        ship_tile_list.clear();
        ship_tile_index_list.clear();
        ship_list.clear();
        attacked_ship_tiles.clear();
        named_ship_list.clear();
        ship_list_by_index.add(0);
        ship_list_by_index.add(1);
        ship_list_by_index.add(2);
        ship_list_by_index.add(3);
        ship_list_by_index.add(4);
    }

    private Image downscale(ImageIcon icon, int width, int height) {
        Image scaled_icon = icon.getImage();
        scaled_icon = scaled_icon.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return scaled_icon;
    }
}
