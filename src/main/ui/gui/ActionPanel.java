package ui.gui;

import exceptions.EmptyContentException;
import exceptions.InvalidMoodException;
import exceptions.NegativeIDException;
import exceptions.RemoveEntryNotInJournalException;
import model.Journal;
import model.MoodType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

// Represents the panel where all the actionable events are displayed and handled
public class ActionPanel extends JPanel implements ActionListener {
    private static final int BTTN_WIDTH = 200;
    private static final int BTTN_HEIGHT = 50;
    private static final Color primary = new Color(167, 190, 211);
    private static final Color highlight = new Color(255, 215, 112);
    private Actions actions;
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;

    //EFFECTS: constructs a panel with add, save, and load buttons
    public ActionPanel(Journal j, MainPanel mp) {
        actions = new Actions(j);
        actions.addObserver(mp.getEntryPanel());
        actions.addObserver(mp.getBarGraphPanel());
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
        } else if (e.getActionCommand().equals("remove")) {
            removeAction();
        } else if (e.getActionCommand().equals("save")) {
            saveAction();
        } else {
            loadAction();
        }
    }

    //MODIFIES: this
    //EFFECTS: prompts the user to add an entry to their journal
    private void addAction() {
        try {
            MoodType[] possibilities = {MoodType.Happy, MoodType.Scared,
                    MoodType.Angry, MoodType.Disgusted, MoodType.Sad};
            String content = (String) JOptionPane.showInputDialog(this, "Write your entry below:",
                    "Add Entry 1/3", JOptionPane.PLAIN_MESSAGE, null, null, null);
            MoodType mood = (MoodType) JOptionPane.showInputDialog(this, "Categorize your entry "
                            + "as one of the following:", "Add Entry 2/3",
                    JOptionPane.PLAIN_MESSAGE, null, possibilities, MoodType.Happy);
            String idNumber = (String) JOptionPane.showInputDialog(this,
                    "PLease enter an ID number for your entry:", "Add Entry 3/3",
                    JOptionPane.PLAIN_MESSAGE, null, null, null);
            int intIdNumber = Integer.parseInt(idNumber);
            actions.addAction(content, intIdNumber, mood);
        } catch (EmptyContentException e) {
            errorMessage("Please do not leave the content of your entry empty.");
        } catch (NegativeIDException e) {
            errorMessage("Please use a positive integer as your entry ID.");
        } catch (InvalidMoodException e) {
            errorMessage("Please select a valid mood type.");
        } catch (NumberFormatException e) {
            errorMessage("Please enter a number for your entry ID.");
        }

    }

    //MODIFIES: this
    //EFFECTS: prompts the user to remove an entry from their journal
    private void removeAction() {
        try {
            String idNumber = (String) JOptionPane.showInputDialog(this,
                    "Enter the ID Number of the entry you would like to remove:", "Remove Entry",
                    JOptionPane.PLAIN_MESSAGE, null, null, null);
            int intIdNumber = Integer.parseInt(idNumber);
            actions.removeAction(intIdNumber);
        } catch (RemoveEntryNotInJournalException e) {
            errorMessage("The entry ID you entered is not in your journal.");
        } catch (NumberFormatException e) {
            errorMessage("Please enter a number.");
        }
    }

    //EFFECTS: saves the journal to file
    private void saveAction() {
        try {
            actions.saveAction();
        } catch (FileNotFoundException ex) {
            errorMessage("File not found.");
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the journal from file
    private void loadAction() {
        try {
            actions.loadAction();
        } catch (Exception ex) {
            errorMessage("There was an error in loading your journal.");
        }
    }

    //EFFECTS: shows an error dialog box with the given message
    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
