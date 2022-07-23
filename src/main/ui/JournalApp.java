package ui;

import model.*;

import java.util.*;

// based on Teller app; link below
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Mood journal application
public class JournalApp {
    private Journal myJournal;
    private Scanner input;

    //EFFECTS: runs the journal application
    public JournalApp() {
        runJournal();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runJournal() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Thanks for journaling!");
    }

    //MODIFIES: this
    //EFFECTS: initializes journal and scanner
    private void init() {
        myJournal = new Journal();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to your mood journal! \nWhat would you like to do today?");
        System.out.println("\ta - add a journal entry");
        System.out.println("\tv - view past entries");
        System.out.println("\tr - remove a past entry");
        System.out.println("\tq - quit");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddEntry();
        } else if (command.equals("v")) {
            doViewPastEntries();
        } else if (command.equals("r")) {
            doRemovePastEntry();
        } else {
            System.out.println("Please enter a valid command");
        }
    }

    //MODIFIES: this
    //EFFECTS: prompts the user to add an entry to their journal
    private void doAddEntry() {
        String content;
        MoodType mood;
        int idNumber;
        Entry entry;

        System.out.println("\nYou selected: add a journal entry");
        System.out.println("Write your entry below:");
        content = input.next();

        System.out.println("\nCategorize your entry as one of the following:");
        printMoodList();
        mood = getMoodFromInput(input.nextInt());

        System.out.println("\nPLease enter an ID number for your entry:");
        idNumber = input.nextInt();

        entry = new Entry(content, idNumber, mood);
        myJournal.addEntry(entry);

        System.out.println("\nEntry " + idNumber + " has been added to your mood journal.");
    }

    //EFFECTS: prints out a list of moods and their integer inputs
    private void printMoodList() {
        System.out.println("\t1 - Happy");
        System.out.println("\t2 - Scared");
        System.out.println("\t3 - Angry");
        System.out.println("\t4 - Disgusted");
        System.out.println("\t5 - Sad");
    }

    //EFFECTS: returns the appropriate mood from a given entry
    private MoodType getMoodFromInput(int input) {
        switch (input) {
            case 1 :
                return MoodType.HAPPY;
            case 2 :
                return MoodType.SCARED;
            case 3 :
                return MoodType.ANGRY;
            case 4 :
                return MoodType.DISGUSTED;
            default :
                return MoodType.SAD;
        }
    }

    //EFFECTS: prompts the user to select the category of past entry they would like to view
    private void doViewPastEntries() {
        System.out.println("\nYou selected: view past entries");
        System.out.println("\nPlease select the entry category you would like to view:");
        printMoodList();
        System.out.println("\t6 - View all entries");

        int inputInt = input.nextInt();
        if (inputInt == 6) {
            for (Entry e : myJournal.getEntries()) {
                System.out.println("\nEntry " + e.getIdNumber());
                System.out.println("Mood: " + e.getMood());
                System.out.println(e.getContent());
            }
        } else {
            for (Entry e : myJournal.getEntriesOfMoodType(getMoodFromInput(inputInt))) {
                System.out.println("\nEntry " + e.getIdNumber());
                System.out.println("Mood: " + e.getMood());
                System.out.println(e.getContent());
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: prompts the user to enter the ID number of the entry they would like to remove
    private void doRemovePastEntry() {
        System.out.println("\nYou selected: remove a past entry");
        System.out.println("\nEnter the ID number of the entry you would like to remove:");

        int inputInt = input.nextInt();
        myJournal.removeEntry(inputInt);

        System.out.println("Entry " + inputInt + " has been removed from your mood journal.");
    }

}
