package Battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static Battleship.Settings.*;

public class BoardSidePanel extends JPanel {
    private ArrayList<JLabel> ship_label_list = new ArrayList<>();
    JPanel shipLabels;
    JPanel layoutButtons;
    public blankButton confirmButton;
    private blankButton clearButton;
    private ToggleableMouseListener confirmListener;
    private ToggleableMouseListener clearListener;
    private Gameboard_L0 layer_zero_reference;
    private JLabel carrierLabel;
    private JLabel battleshipLabel;
    private JLabel cruiserLabel;
    private JLabel submarineLabel;
    private JLabel destroyerLabel;
    private final boolean mode;
    public BoardSidePanel(boolean mode, Gameboard_L0 layer_zero_reference){
        this.layer_zero_reference = layer_zero_reference;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(175,PANEL_HEIGHT));
        this.mode = mode;

        initializeBoardSidePanel();
        populateSubPanels();
        populateBoardSidePanel();
    }
    private void initializeBoardSidePanel(){
        initializeShipLabelsJPanel();
        initializeLayoutButtonsJPanel();
    }
    private void populateSubPanels(){
        populateShipLabelsJPanel();
        populateLayoutButtonsJPanel();
    }
    private void populateBoardSidePanel(){
        this.add(shipLabels,BorderLayout.PAGE_START);
        if(!mode){this.add(layoutButtons,BorderLayout.PAGE_END);}
    }
    private void initializeShipLabelsJPanel(){
        shipLabels = new JPanel();
        shipLabels.setOpaque(false);
        shipLabels.setLayout(new BoxLayout(shipLabels,BoxLayout.PAGE_AXIS));
        shipLabels.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
    }
    private void initializeLayoutButtonsJPanel(){
        layoutButtons = new JPanel();
        layoutButtons.setOpaque(false);
        layoutButtons.setLayout(new FlowLayout());
        layoutButtons.setPreferredSize(new Dimension(175,50));
    }
    private void populateShipLabelsJPanel(){
        carrierLabel = new JLabel("Carrier", new ImageIcon(downscale(carrier)), SwingConstants.CENTER);
        setSettingsShipJLabel(carrierLabel);
        shipLabels.add(carrierLabel);
        ship_label_list.add(carrierLabel);

        battleshipLabel = new JLabel("Battleship", new ImageIcon(downscale(battleship)),SwingConstants.CENTER);
        setSettingsShipJLabel(battleshipLabel);
        shipLabels.add(battleshipLabel);
        ship_label_list.add(battleshipLabel);

        cruiserLabel = new JLabel("Cruiser", new ImageIcon(downscale(cruiser)),SwingConstants.CENTER);
        setSettingsShipJLabel(cruiserLabel);
        shipLabels.add(cruiserLabel);
        ship_label_list.add(cruiserLabel);

        submarineLabel = new JLabel("Submarine", new ImageIcon(downscale(submarine)),SwingConstants.CENTER);
        setSettingsShipJLabel(submarineLabel);
        shipLabels.add(submarineLabel);
        ship_label_list.add(submarineLabel);

        destroyerLabel = new JLabel("Destroyer", new ImageIcon(downscale(destroyer)),SwingConstants.CENTER);
        setSettingsShipJLabel(destroyerLabel);
        shipLabels.add(destroyerLabel);
        ship_label_list.add(destroyerLabel);
    }
    private void initializeListeners(){
        clearListener = new ToggleableMouseListener() {
            @Override
            public void actionOnPress(MouseEvent e) {
                for (JLabel tile : layer_zero_reference.getShipTileList()){
                    tile.setIcon(null);
                }
                layer_zero_reference.clearShipTileList();
            }
        };
        confirmListener = new ToggleableMouseListener() {
            @Override
            public void actionOnPress(MouseEvent e) {
                layer_zero_reference.addShipToList();
                setConfirmButtonActive(false);
            }
        };
    }
    private void populateLayoutButtonsJPanel(){
        initializeListeners();

        confirmButton = new blankButton();
        confirmButton.setIcon(confirm1);
        confirmButton.setRolloverIcon(confirm2);
        confirmButton.setPressedIcon(confirm3);
        confirmButton.addMouseListener(confirmListener);
        setConfirmButtonActive(false);
        layoutButtons.add(confirmButton);

        clearButton = new blankButton();
        clearButton.setIcon(clear1);
        clearButton.setRolloverIcon(clear2);
        clearButton.setPressedIcon(clear3);
        clearButton.setAlignmentX(0.5f);
        clearButton.addMouseListener(clearListener);
        layoutButtons.add(clearButton);

        setLayoutButtonsVisible(false);
    }

    private Image downscale(ImageIcon icon){
        Image scaled_icon = icon.getImage();
        scaled_icon = scaled_icon.getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
        return scaled_icon;
    }

    private void setSettingsShipJLabel(JLabel label){
        label.setBorder(BorderFactory.createEmptyBorder(10,30,20,10));
        label.setForeground(Color.WHITE);
        label.setFont(SHIP_LABEL);
    }

    @Deprecated
    public void makeLayoutButtonsInvisible(){
        clearButton.setVisible(false);
        confirmButton.setVisible(false);
    }
    public void setLayoutButtonsActive(boolean state){
        clearButton.setEnabled(state);
        clearListener.setActive(state);
        confirmButton.setEnabled(state);
        confirmListener.setActive(state);
    }
    public void setConfirmButtonActive(boolean state){
        confirmButton.setEnabled(state);
        confirmListener.setActive(state);
    }
    public void setClearButtonActive(boolean state){
        clearButton.setEnabled(state);
        clearListener.setActive(state);
    }
    public void setLayoutButtonsVisible(boolean state){
        layoutButtons.setVisible(state);
    }
    public void crossOutDestroyedShipsLabel(int type){
        switch (type){
            case 0:
                carrierLabel.setFont(SHIP_LABEL_STRIKETHROUGH);
                break;
            case 1:
                battleshipLabel.setFont(SHIP_LABEL_STRIKETHROUGH);
                break;
            case 2:
                cruiserLabel.setFont(SHIP_LABEL_STRIKETHROUGH);
                break;
            case 3:
                submarineLabel.setFont(SHIP_LABEL_STRIKETHROUGH);
                break;
            case 4:
                destroyerLabel.setFont(SHIP_LABEL_STRIKETHROUGH);
                break;
        }
    }
    public void setShipLabelsToDefault(){
        for (JLabel ship_label : ship_label_list){
            ship_label.setFont(SHIP_LABEL);
        }
    }
}
