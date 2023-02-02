package Battleship;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public abstract class ToggleableMouseListener extends MouseAdapter {
    private boolean active = true;
    private boolean game_end_state = false;
    public void setActive(boolean state){
        this.active = state;
    }
    public void setGameOverState(boolean state){
        this.game_end_state = state;
    }
    public boolean getState(){return this.active;}
    protected abstract void actionOnPress(MouseEvent e);
    @Override
    public void mousePressed(MouseEvent e) {
        if(active && !game_end_state) actionOnPress(e);
    }
}
