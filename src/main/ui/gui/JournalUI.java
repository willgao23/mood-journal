package ui.gui;

import model.Event;
import model.EventLog;
import model.Journal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// Represents the main window in which the journal is displayed
public class JournalUI extends JFrame implements WindowListener {

    //EFFECTS: constructs main window in which journal will be displayed
    public JournalUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Journal journal = new Journal();
        MainPanel mp = new MainPanel(journal);
        ActionPanel ap = new ActionPanel(journal, mp);
        add(ap, BorderLayout.NORTH);
        add(mp);
        pack();
        centreOnScreen();
        setVisible(true);
        addWindowListener(this);
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

    //EFFECTS: prints to console all the events since the application started when main window is closing
    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    //EFFECTS: prints the date and description of all events in the given event log
    private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    //EFFECTS: application does nothing on main window open
    @Override
    public void windowOpened(WindowEvent e) {
        //do nothing
    }

    //EFFECTS: application does nothing after the main window is closed
    @Override
    public void windowClosed(WindowEvent e) {
        //do nothing
    }

    //EFFECTS: application does nothing when main window is iconified
    @Override
    public void windowIconified(WindowEvent e) {
        //do nothing
    }

    //EFFECTS: application does nothing when main window is deiconified
    @Override
    public void windowDeiconified(WindowEvent e) {
        //do nothing
    }

    //EFFECTS: application does nothing when main window is activated
    @Override
    public void windowActivated(WindowEvent e) {
        //do nothing
    }

    //EFFECTS: application does nothing when main window is deactivated
    @Override
    public void windowDeactivated(WindowEvent e) {
        //do nothing
    }
}
