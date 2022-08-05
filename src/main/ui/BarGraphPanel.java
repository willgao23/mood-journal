package ui;

import model.Journal;

import javax.swing.*;
import java.awt.*;

public class BarGraphPanel extends JPanel {
    private static final Color secondary = new Color(198, 226, 233);
    private static final Color highlight = new Color(255, 215, 112);
    public static final int PNL_WIDTH = 600;
    public static final int PNL_HEIGHT = 400;
    private Journal journal;

    public BarGraphPanel(Journal j) {
        this.journal = j;
        setBackground(secondary);
        setPreferredSize(new Dimension(PNL_WIDTH, PNL_HEIGHT));
    }
}
