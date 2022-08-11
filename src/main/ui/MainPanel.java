package ui;

import model.Entry;
import model.Journal;
import model.MoodType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Represents the parent panel holding the bar graph and entry panels
public class MainPanel extends JPanel {
    private static final Color primary = new Color(167, 190, 211);
    private EntryPanel entryPanel;
    private BarGraphPanel barGraphPanel;

    //EFFECTS: constructs a panel with an entry panel and bar graph panel
    public MainPanel(Journal j) {
        this.setBackground(primary);
        entryPanel = new EntryPanel(j);
        barGraphPanel = new BarGraphPanel(j);
        add(entryPanel);
        add(barGraphPanel);
    }

    //EFFECTS: updates the entry panel and bar graph panel
    public void update(Journal j) {
        entryPanel.update(j);
        barGraphPanel.update(j);
    }
}