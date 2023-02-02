package Battleship;

import javax.swing.*;
import java.awt.*;

public class blankButton extends JButton {
    public blankButton(){
        super();
        setBorderPainted(false);
        setFocusPainted(false);
        setMargin(new Insets(0, 0, 0, 0));
        setContentAreaFilled(false);
    }
}
