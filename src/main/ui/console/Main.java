package ui.console;

import java.io.FileNotFoundException;

// Runs a new journal application
public class Main {
    public static void main(String[] args) {
        try {
            new JournalApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }
}
