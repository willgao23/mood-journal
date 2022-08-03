package ui;

import model.Entry;
import model.Journal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainPanel extends JPanel {
    private static final Color primary = new Color(167, 190, 211);
    private static final Color secondary = new Color(198, 226, 233);
    private static final Color highlight = new Color(255, 215, 112);
    private static final int SMALL_PNL_WIDTH = 200;
    private static final int BIG_PNL_WIDTH = 600;
    private static final int PNL_HEIGHT = 400;
    private JPanel barGraphPanel;
    private JPanel entryPanel;
    private JPanel labelPanelLeft;
    private JLabel entryPanelLabel;
    private JScrollPane scrollPane;
    private JTextArea entryText;
    private BoxLayout layout;
    private Journal journal;
    private List<Entry> entries;

    public MainPanel(Journal j) {
        this.journal = j;
        this.setBackground(primary);

        entryLabelSetUp();
        entryTextAreaSetUp();
        scrollPaneSetUp();
        entryPanelSetUp();
        initializePanel(entryPanel);
        barGraphPanelSetUp();
        initializePanel(barGraphPanel);

    }

    //MODIFIES: this
    //EFFECTS: creates a formatted bar graph panel
    private void barGraphPanelSetUp() {
        //TODO: complete implementation of the bar graph set up
        barGraphPanel = new JPanel();
        barGraphPanel.setPreferredSize(new Dimension(BIG_PNL_WIDTH, PNL_HEIGHT));
    }

    //MODIFIES: this
    //EFFECTS: creates a formatted entry panel with a label panel and scroll pane
    private void entryPanelSetUp() {
        entryPanel = new JPanel();
        layout = new BoxLayout(entryPanel, BoxLayout.Y_AXIS);
        entryPanel.setLayout(layout);
        entryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        entryPanel.add(labelPanelLeft);
        entryPanel.add(scrollPane);
        entryPanel.setPreferredSize(new Dimension(SMALL_PNL_WIDTH, PNL_HEIGHT));
        entryPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    //MODIFIES: this
    //EFFECTS: creates a scroll pane and places the text area inside
    private void scrollPaneSetUp() {
        scrollPane = new JScrollPane(entryText);
        scrollPane.setPreferredSize(new Dimension(SMALL_PNL_WIDTH - 20, PNL_HEIGHT - 50));
    }

    //MODIFIES: this
    //EFFECTS: creates a stylized text area to show the
    private void entryTextAreaSetUp() {
        entries = journal.getEntries();
        entryText = new JTextArea(entriesToString(entries));
        entryText.setBackground(secondary);
        entryText.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        entryText.setEditable(false);
    }

    //MODIFIES: this
    //EFFECTS: creates a stylized panel with a title label
    private void entryLabelSetUp() {
        labelPanelLeft = new JPanel();
        entryPanelLabel = new JLabel("Entries");
        entryPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        labelPanelLeft.setBackground(secondary);
        labelPanelLeft.add(entryPanelLabel);
    }

    //EFFECTS: returns given list of entries as a formatted string
    private String entriesToString(List<Entry> entries) {
        String entryString = "";

        for (Entry e : entries) {
            entryString += "Entry " + e.getIdNumber() + "\nMood: " + e.getMood() + "\n" + e.getContent() + "\n\n";
        }

        return entryString;
    }

    //MODIFIES: panel
    //EFFECTS: formats a given panel and adds it to main panel
    private void initializePanel(JPanel panel) {
        panel.setBackground(secondary);
        add(panel);
    }

    //EFFECTS: updates the entry panel and bar graph panel
    public void update(Journal j) {
        entryPanelUpdate(j);
        barGraphPanelUpdate(j);
    }

    //MODIFIES: this
    //EFFECTS: updates the bar graph to reflect changes
    private void barGraphPanelUpdate(Journal j) {
        //TODO: complete implementation
    }

    //MODIFIES: this
    //EFFECTS: updates the list of entries and text field to reflect changes
    private void entryPanelUpdate(Journal j) {
        entries = j.getEntries();
        entryText.setText(entriesToString(entries));
    }

}