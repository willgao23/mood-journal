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

// Represents the panel where all the actionable events are displayed and handled
public class ActionPanel extends JPanel implements ActionListener, MouseMotionListener {
    private static final String JSON_STORE = "./data/journal.json";
    private static final int BTTN_WIDTH = 200;
    private static final int BTTN_HEIGHT = 50;
    private static final Color primary = new Color(167, 190, 211);
    private static final Color highlight = new Color(255, 215, 112);
    private Journal journal;
    private MainPanel mp;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String content;
    private MoodType mood;
    private String idNumber;

    //EFFECTS: constructs a panel with add, remove, save, and load buttons
    public ActionPanel(Journal j, MainPanel mp) {
        journal = j;
        this.mp = mp;
        setBackground(primary);
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
        mp.addMouseMotionListener(this);
        addMouseMotionListener(this);

        addButton = new JButton("Add Entry");
        addButton.setActionCommand("add");
        saveButton = new JButton("Save Journal");
        saveButton.setActionCommand("save");
        loadButton = new JButton("Load Journal");
        loadButton.setActionCommand("load");

        initializeButton(addButton);
        initializeButton(saveButton);
        initializeButton(loadButton);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //MODIFIES: this, button
    //EFFECTS: formats a button and adds it to the panel
    private void initializeButton(JButton button) {
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setPreferredSize(new Dimension(BTTN_WIDTH, BTTN_HEIGHT));
        button.setBackground(highlight);
        button.addActionListener(this);
        button.setFont(new Font("Futura", Font.BOLD, 12));
        add(button);
    }

    //EFFECTS: processes button presses
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            addAction();
        } else if (e.getActionCommand().equals("save")) {
            saveAction();
        } else {
            loadAction();
        }
    }

    //EFFECTS: prompts the user to remove an entry from their journal on mouse drag
    @Override
    public void mouseDragged(MouseEvent e) {
        removeAction();
    }

    //EFFECTS: application does nothing on mouse movement
    @Override
    public void mouseMoved(MouseEvent e) {
        //do nothing
    }

    //MODIFIES: this
    //EFFECTS: prompts the user to add an entry to their journal
    private void addAction() {
        addEntryDialogSequence();

        try {
            int intIdNumber = Integer.parseInt(idNumber);
            Entry entry = new Entry(content, intIdNumber, mood);
            if (!journal.addEntry(entry)) {
                JOptionPane.showMessageDialog(this, "There is an already an entry with that "
                        + "ID Number in your journal", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            mp.update(journal);
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

    //EFFECTS: creates a sequence of dialog boxes to get user input for adding an entry
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

    //MODIFIES: this;
    //EFFECTS: prompts the user to remove an entry from their journal
    private void removeAction() {
        idNumber = (String)JOptionPane.showInputDialog(this,
                "Enter the ID Number of the entry you would like to remove:", "Remove Entry",
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        try {
            int intIdNumber = Integer.parseInt(idNumber);
            journal.removeEntry(intIdNumber);
            mp.update(journal);
        } catch (RemoveEntryNotInJournalException e) {
            JOptionPane.showMessageDialog(this, "The entry ID you entered is "
                            + "not in your journal.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a number",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: saves the journal to file
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

    //MODIFIES: this
    //EFFECTS: loads the journal from file
    private void loadAction() {
        try {
            journal = jsonReader.read();
            mp.update(journal);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "There was an error in loading your journal",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
