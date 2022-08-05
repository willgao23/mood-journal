package ui;

import model.Entry;
import model.Journal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EntryPanel extends JPanel {
    private static final Color secondary = new Color(198, 226, 233);
    private static final int PNL_WIDTH = 200;
    private static final int PNL_HEIGHT = 400;
    private JPanel labelPanelLeft;
    private JLabel entryPanelLabel;
    private JScrollPane scrollPane;
    private JTextArea entryText;
    private Journal journal;
    private List<Entry> entries;

    public EntryPanel(Journal j) {
        this.journal = j;
        setBackground(secondary);
        entryLabelSetUp();
        entryTextAreaSetUp();
        scrollPaneSetUp();
        entryPanelSetUp();

    }

    //MODIFIES: this
    //EFFECTS: creates a formatted entry panel with a label panel and scroll pane
    private void entryPanelSetUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(labelPanelLeft);
        add(scrollPane);
        setPreferredSize(new Dimension(PNL_WIDTH, PNL_HEIGHT));
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    //MODIFIES: this
    //EFFECTS: creates a scroll pane and places the text area inside
    private void scrollPaneSetUp() {
        scrollPane = new JScrollPane(entryText);
        scrollPane.setPreferredSize(new Dimension(PNL_WIDTH - 20, PNL_HEIGHT - 50));
    }

    //MODIFIES: this
    //EFFECTS: creates a stylized text area to show the
    private void entryTextAreaSetUp() {
        entries = journal.getEntries();
        entryText = new JTextArea(entriesToString(entries));
        entryText.setBackground(secondary);
        entryText.setFont(new Font("Futura", Font.PLAIN, 12));
        entryText.setEditable(false);
    }

    //MODIFIES: this
    //EFFECTS: creates a stylized panel with a title label
    private void entryLabelSetUp() {
        labelPanelLeft = new JPanel();
        entryPanelLabel = new JLabel("Entries");
        entryPanelLabel.setFont(new Font("Futura", Font.BOLD, 16));
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

    //MODIFIES: this
    //EFFECTS: updates the list of entries and text field to reflect changes
    public void update(Journal j) {
        entries = j.getEntries();
        entryText.setText(entriesToString(entries));
    }
}


