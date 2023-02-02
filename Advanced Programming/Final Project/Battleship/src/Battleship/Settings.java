package Battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class Settings {
    // ####################################################### //
    // Setting screen size and adjusting for windows title bar //
    // ####################################################### //
    public final static int TITLE_BAR_WIDTH = 16;
    public final static int TITLE_BAR_HEIGHT = 39;
    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;
    public final static Dimension SCREEN_SIZE =
            new Dimension(WIDTH, HEIGHT);
    public final static Dimension ADJUSTED_SCREEN_SIZE =
            new Dimension(WIDTH + TITLE_BAR_WIDTH, HEIGHT + TITLE_BAR_HEIGHT);
    public final static int PANEL_HEIGHT = 350;
    public final static int PANEL_WIDTH = 500;

    // ################## //
    // Grid and cell size //
    // ################## //
    public final static int CELL_SIDE = 25;
    public final static Dimension CELL_SIZE = new Dimension(CELL_SIDE,CELL_SIDE);
    public final static int ROW_COUNT = 11;
    public final static int COL_COUNT = 11;
    // ############ //
    // Color scheme //
    // ############ //
    public final static Color _ORANGE = new Color(250, 164, 28);
    public final static Color _MIDNIGHT_BLUE = new Color(15, 23, 42);
    public final static Color _LIGHT_GRAY = new Color(224, 224, 224);
    public final static Color _GRAY = new Color(189, 189, 189);
    public final static Color _DARK_GRAY = new Color(158, 158, 158);
    public final static Color _BATTLESHIP = new Color(37,159,149);
    public final static Color _CARRIER = new Color(24,59,77);
    public final static Color _CRUISER = new Color(228,196,109);
    public final static Color _DESTROYER = new Color(234,109,84);
    public final static Color _SUBMARINE = new Color(246,162,102);
    public final static Color _MAIN = _MIDNIGHT_BLUE;
    public final static Color _BOARDS = _ORANGE;
    public final static Color _BOTTOM_PANE = _ORANGE;
    public final static Color _CELL = _LIGHT_GRAY;
    // ################ //
    // Textbox settings //
    // ################ //
    public final static Font GENERAL_TEXT = new Font("Segoe UI", Font.PLAIN, 20);
    public final static Font SHIP_LABEL = new Font("Century Gothic", Font.PLAIN, 15);
    public static Font SHIP_LABEL_STRIKETHROUGH;
    static {
        Map fontAttr = SHIP_LABEL.getAttributes();
        fontAttr.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        SHIP_LABEL_STRIKETHROUGH = new Font(fontAttr);
    }
    public final static Font BOARD_TITLE = new Font("Century Gothic", Font.BOLD, 16);
    public final static Insets TEXTBOX_MARGIN = new Insets(5,5,5,5);
    // ##### //
    // ICONS //
    // ##### //
    public final static ImageIcon battleship_title = new ImageIcon("icons/ui/_misc/battleship_title.png");
    public final static ImageIcon battleship_titlebar = new ImageIcon("icons/ui/_misc/battleship_titlebar.png");
    public final static ImageIcon new_game1 = new ImageIcon("icons/ui/_buttons/new_game1.png");
    public final static ImageIcon new_game2 = new ImageIcon("icons/ui/_buttons/new_game2.png");
    public final static ImageIcon new_game3 = new ImageIcon("icons/ui/_buttons/new_game3.png");
    public final static ImageIcon confirm1 = new ImageIcon("icons/ui/_buttons/confirm1.png");
    public final static ImageIcon confirm2 = new ImageIcon("icons/ui/_buttons/confirm2.png");
    public final static ImageIcon confirm3 = new ImageIcon("icons/ui/_buttons/confirm3.png");
    public final static ImageIcon clear1 = new ImageIcon("icons/ui/_buttons/clear1.png");
    public final static ImageIcon clear2 = new ImageIcon("icons/ui/_buttons/clear2.png");
    public final static ImageIcon clear3 = new ImageIcon("icons/ui/_buttons/clear3.png");
    public final static ImageIcon battleship = new ImageIcon("icons/game/_ships/_based_on_color/battleship.png");
    public final static ImageIcon carrier = new ImageIcon("icons/game/_ships/_based_on_color/carrier.png");
    public final static ImageIcon cruiser = new ImageIcon("icons/game/_ships/_based_on_color/cruiser.png");
    public final static ImageIcon destroyer = new ImageIcon("icons/game/_ships/_based_on_color/destroyer.png");
    public final static ImageIcon submarine = new ImageIcon("icons/game/_ships/_based_on_color/submarine.png");
    public final static ImageIcon anchor = new ImageIcon("icons/game/_misc/anchor.png");
    public final static ImageIcon cross = new ImageIcon("icons/game/_misc/cross.png");
    public final static ImageIcon bomb = new ImageIcon("icons/game/_misc/bomb.png");
    public final static ImageIcon bombwhite = new ImageIcon("icons/game/_misc/bombwhite.png");
    public final static ImageIcon ship = new ImageIcon("icons/game/_misc/ship.png");
    public final static ImageIcon battleshipShape = new ImageIcon("icons/game/_ships/_based_on_design/battleship.png");
    public final static ImageIcon carrierShape = new ImageIcon("icons/game/_ships/_based_on_design/carrier.png");
    public final static ImageIcon cruiserShape = new ImageIcon("icons/game/_ships/_based_on_design/cruiser.png");
    public final static ImageIcon destroyerShape = new ImageIcon("icons/game/_ships/_based_on_design/destroyer.png");
    public final static ImageIcon submarineShape = new ImageIcon("icons/game/_ships/_based_on_design/submarine.png");

//    public Settings(){
//        Map fontAttr = SHIP_LABEL.getAttributes();
//        fontAttr.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
//        SHIP_LABEL_STRIKETHROUGH = new Font(fontAttr);
//    }
}