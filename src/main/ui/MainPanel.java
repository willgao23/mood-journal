package ui;

import model.Journal;

import javax.swing.*;
import java.awt.*;

// Represents the parent panel holding the bar graph and entry panels
public class MainPanel extends JPanel {
    private static final Color primary = new Color(167, 190, 211);
    private EntryPanel entryPanel;
    private BarGraphPanel barGraphPanel;

    //EFFECTS: constructs a panel with an entry panel and bar graph panel
    public MainPanel(Journal journal) {
        this.setBackground(primary);
        entryPanel = new EntryPanel(journal);
        barGraphPanel = new BarGraphPanel(journal);
        add(entryPanel);
        add(barGraphPanel);
    }

    public EntryPanel getEntryPanel() {
        return entryPanel;
    }

    public BarGraphPanel getBarGraphPanel() {
        return barGraphPanel;
    }
}