package Battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static Battleship.Settings.*;

public class BottomPanel extends JPanel {
    blankButton new_game_button;
    JTextArea game_logs;
    JScrollPane game_logs_scroller;
    ToggleableMouseListener new_game_button_listener;
    public BottomPanel(){
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        this.setPreferredSize(GamePanel.getPanelSize("BOTTOM"));
        this.setBackground(_BOTTOM_PANE);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,10,5));
        addNewGameButton();
        addGameLogs();
    }

    public void addNewGameButton(){
        new_game_button = new blankButton();
        new_game_button.setIcon(new_game1);
        new_game_button.setRolloverIcon(new_game2);
        new_game_button.setPressedIcon(new_game3);
        new_game_button_listener = new ToggleableMouseListener() {
            @Override
            public void actionOnPress(MouseEvent e) {
                if (Battleship.showNewGameDialogue()){
                    Battleship.restartGame();
                    Battleship.sendLog("NEWGAME");
                }
            }
        };
        new_game_button.addMouseListener(new_game_button_listener);
        setNewGameButtonActive(false);
        this.add(new_game_button);
    }
    public void setNewGameButtonActive(boolean state){
        new_game_button_listener.setActive(state);
        new_game_button.setEnabled(state);
    }
    public void addGameLogs(){
        game_logs = new JTextArea();
        game_logs.setFont(GENERAL_TEXT);
        game_logs.setMargin(TEXTBOX_MARGIN);
        game_logs.setEditable(false);
        game_logs.setBackground(_LIGHT_GRAY);
        game_logs_scroller = new JScrollPane(game_logs);
        game_logs_scroller.setBackground(_BOTTOM_PANE);
        game_logs_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        game_logs_scroller.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,3,true),"Logs and events"));
//        game_logs_scroller.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
//            public void adjustmentValueChanged(AdjustmentEvent e) {
//                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
//            }
//        });
        this.add(game_logs_scroller);
    }
    public void addLog(String text){
        game_logs.append("["+ DateTimeFormatter.ofPattern("hh:mm:ss a").format(LocalTime.now())+"] "+text+"\n");
        game_logs_scroller.getVerticalScrollBar().setValue(game_logs_scroller.getVerticalScrollBar().getMaximum());
    }
    public void clearLogs(){
        game_logs.setText(null);
    }
}
