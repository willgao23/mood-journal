package ui;

import model.Journal;

import javax.swing.*;
import java.awt.*;

// Represents the main window in which the journal is displayed
public class JournalUI extends JFrame {

    private Journal journal;
    private ActionPanel ap;
//    private MainPanel mp;

    //EFFECTS: constructs main window in which journal will be displayed
    public JournalUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        journal = new Journal();
//        mp = new MainPanel(journal);
        ap = new ActionPanel(journal);
        add(ap, BorderLayout.NORTH);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: location of frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    //EFFECTS: runs the journal UI
    public static void main(String[] args) {
        new JournalUI();
    }
}
