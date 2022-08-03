package ui;

import exceptions.EmptyContentException;
import exceptions.InvalidMoodException;
import exceptions.NegativeIDException;
import exceptions.RemoveEntryNotInJournalException;
import model.Entry;
import model.Journal;
import model.MoodType;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the panel where all the actionable buttons are displayed
public class ActionPanel extends JPanel implements ActionListener {
    private static final String JSON_STORE = "./data/journal.json";
    private static final int BTTN_WIDTH = 200;
    private static final int BTTN_HEIGHT = 50;
    private static final Color primary = new Color(167, 190, 211);
    private static final Color secondary = new Color(198, 226, 233);
    private static final Color highlight = new Color(255, 215, 112);
    private Journal journal;
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String content;
    private MoodType mood;
    private String idNumber;

    public ActionPanel(Journal j) {
        journal = j;
        setBackground(primary);
        addButton = new JButton("Add Entry");
        addButton.setActionCommand("add");
        removeButton = new JButton("Remove Entry");
        removeButton.setActionCommand("remove");
        saveButton = new JButton("Save Journal");
        saveButton.setActionCommand("save");
        loadButton = new JButton("Load Journal");
        loadButton.setActionCommand("load");

        initializeButton(addButton);
        initializeButton(removeButton);
        initializeButton(saveButton);
        initializeButton(loadButton);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    private void initializeButton(JButton button) {
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setPreferredSize(new Dimension(BTTN_WIDTH, BTTN_HEIGHT));
        button.setBackground(highlight);
        button.addActionListener(this);
        add(button);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            addAction();
        } else if (e.getActionCommand().equals("remove")) {
            removeAction();
        } else if (e.getActionCommand().equals("save")) {
            saveAction();
        } else {
            loadAction();
        }
    }

    private void addAction() {
        addEntryDialogSequence();

        try {
            int intIdNumber = Integer.parseInt(idNumber);
            Entry entry = new Entry(content, intIdNumber, mood);
            journal.addEntry(entry);
        } catch (EmptyContentException e) {
            JOptionPane.showMessageDialog(this, "Please do not leave the content "
                            + "of your entry empty.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NegativeIDException e) {
            JOptionPane.showMessageDialog(this, "Please use a positive integer as "
                    + "your entry ID.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidMoodException e) {
            JOptionPane.showMessageDialog(this, "Please select an valid mood type.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a positive "
                            + "number for your entry ID", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void addEntryDialogSequence() {
        MoodType[] possibilities = {MoodType.Happy, MoodType.Scared, MoodType.Angry, MoodType.Disgusted, MoodType.Sad};
        content = (String)JOptionPane.showInputDialog(this, "Write your entry below:",
                "Add Entry 1/3", JOptionPane.PLAIN_MESSAGE, null, null, null);
        mood = (MoodType)JOptionPane.showInputDialog(this, "Categorize your entry "
                + "as one of the following:", "Add Entry 2/3", JOptionPane.PLAIN_MESSAGE, null, possibilities,
                MoodType.Happy);
        idNumber = (String)JOptionPane.showInputDialog(this, "PLease enter an ID number "
                + "for your entry:", "Add Entry 3/3", JOptionPane.PLAIN_MESSAGE, null,
                null, null);
    }

    private void removeAction() {
        idNumber = (String)JOptionPane.showInputDialog(this,
                "Enter the ID Number of the entry you would like to remove:", "Remove Entry",
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        try {
            int intIdNumber = Integer.parseInt(idNumber);
            journal.removeEntry(intIdNumber);
        } catch (RemoveEntryNotInJournalException e) {
            JOptionPane.showMessageDialog(this, "The entry ID you entered is "
                            + "not in your journal.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a number",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveAction() {
        try {
            jsonWriter.open();
            jsonWriter.write(journal);
            jsonWriter.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File not found",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAction() {
        try {
            journal = jsonReader.read();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "There was an error in loading your journal",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

}