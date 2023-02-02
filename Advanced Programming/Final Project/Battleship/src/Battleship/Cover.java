package Battleship;

import javax.swing.*;
import java.awt.*;

import static Battleship.Settings.*;

public class Cover extends JPanel {
    JLabel cover_label;
    String cover_text;
    public Cover(String cover_text){
        this.setLayout(new BorderLayout());
        this.cover_text = cover_text;
        initializeCoverLabel();
        this.add(cover_label,BorderLayout.CENTER);
        this.setOpaque(false);
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

        g2d.setColor(new Color(0,0,0,200));
        g2d.fillRoundRect(0,0,500,370,30,30);
    }
    private void initializeCoverLabel(){
        cover_label = new JLabel();
        cover_label.setText(cover_text);
        cover_label.setFont(GENERAL_TEXT);
        cover_label.setForeground(Color.WHITE);
        cover_label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public void setCover_text(String cover_text){
        cover_label.setText(cover_text);
    }
}
