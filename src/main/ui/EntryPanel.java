package ui;

import model.Entry;
import model.Journal;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

// Represents the panel where the journal entries are displayed
public class EntryPanel extends JPanel implements Observer {
    private static final Color secondary = new Color(198, 226, 233);
    private static final int PNL_WIDTH = 200;
    private static final int PNL_HEIGHT = 400;
    private static final Font titleFont = new Font("Futura", Font.BOLD, 16);
    private static final Font bodyFont = new Font("Futura", Font.PLAIN, 12);
    private JPanel labelPanelLeft;
    private JLabel entryPanelLabel;
    private JScrollPane scrollPane;
    private JTextArea entryText;

    //EFFECTS: constructs an entry panel with a label, text area, scroll panel, and styling
    public EntryPanel(Journal journal) {
        setBackground(secondary);
        entryLabelSetUp();
        entryTextAreaSetUp(journal);
        scrollPaneSetUp();
        entryPanelSetUp();

    }

    //MODIFIES: this
    //EFFECTS: creates a formatted entry panel with a label panel and scroll pane
    private void entryPanelSetUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(-5, 10, 10, 10));
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
    private void entryTextAreaSetUp(Journal journal) {
        entryText = new JTextArea(entriesToString(journal.getEntries()));
        entryText.setBackground(secondary);
        entryText.setFont(bodyFont);
        entryText.setEditable(false);
    }

    //MODIFIES: this
    //EFFECTS: creates a stylized panel with a title label
    private void entryLabelSetUp() {
        labelPanelLeft = new JPanel();
        entryPanelLabel = new JLabel("Entries");
        entryPanelLabel.setFont(titleFont);
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

    @Override
    public void update(Observable o, Object arg) {
        Journal journal = (Journal) arg;
        entryText.setText(entriesToString(journal.getEntries()));
    }
}


