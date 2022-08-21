package ui.console;

import exceptions.*;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// based on Teller app; link below
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Mood journal application
public class JournalApp {
    private static final String JSON_STORE = "./data/journal.json";
    private Journal myJournal;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the journal application
    public JournalApp() throws FileNotFoundException {
        runJournal();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runJournal() throws FileNotFoundException {
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
    //EFFECTS: initializes journal, scanner, jsonReader, and jsonWriter
    private void init() {
        myJournal = new Journal();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to your mood journal! \nWhat would you like to do today?");
        System.out.println("\ta - add a journal entry");
        System.out.println("\tv - view past entries");
        System.out.println("\tr - remove a past entry");
        System.out.println("\ts - save journal to file");
        System.out.println("\tl - load journal from file");
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
        } else if (command.equals("s")) {
            saveJournal();
        } else if (command.equals("l")) {
            loadJournal();
        } else {
            System.out.println("Please enter a valid command");
        }
    }

    //MODIFIES: this
    //EFFECTS: prompts the user to add an entry to their journal
    private void doAddEntry() {
        System.out.println("\nYou selected: add a journal entry");
        System.out.println("Write your entry below:");
        String content = input.next();

        System.out.println("\nCategorize your entry as one of the following:");
        printMoodList();
        MoodType mood = getMoodFromInput(input.nextInt());

        System.out.println("\nPLease enter an ID number for your entry:");
        int idNumber = input.nextInt();

        try {
            Entry entry = new Entry(content, idNumber, mood);
            if (myJournal.addEntry(entry)) {
                Collections.sort(myJournal.getEntries(), Comparator.comparingInt((Entry e) -> e.getIdNumber()));
                System.out.println("\nEntry " + idNumber + " has been added to your mood journal.");
            } else {
                System.out.println("\nThere is already an entry with that ID number in your journal.");
            }
        } catch (EmptyContentException e) {
            System.out.println("\nPlease do not leave the content of your entry empty.");
        } catch (NegativeIDException e) {
            System.out.println("\nPlease use a positive integer as your entry ID.");
        } catch (InvalidMoodException e) {
            System.out.println("\nPlease select an valid mood type.");
        }
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
            case 1:
                return MoodType.Happy;
            case 2:
                return MoodType.Scared;
            case 3:
                return MoodType.Angry;
            case 4:
                return MoodType.Disgusted;
            case 5:
                return MoodType.Sad;
            default:
                return MoodType.Invalid;
        }
    }

    //EFFECTS: prompts the user to select the category of past entry they would like to view
    private void doViewPastEntries() {
        printViewEntriesPrompt();

        int inputInt = input.nextInt();
        if (inputInt == 6) {
            if (!(myJournal.getEntries().size() == 0)) {
                for (Entry e : myJournal.getEntries()) {
                    printEntries(e);
                }
            } else {
                System.out.println("\nYou have no entries in your journal.");
            }
        } else {
            try {
                for (Entry e : myJournal.getEntriesOfMoodType(getMoodFromInput(inputInt))) {
                    printEntries(e);
                }
            } catch (NoEntriesOfTypeException e) {
                System.out.println("\nYou have no entries with the mood "
                        + getMoodFromInput(inputInt).toString().toLowerCase() + " in your journal");
            } catch (InvalidMoodException e) {
                System.out.println("\nPlease select a valid option.");
            }
        }
    }

    //EFFECTS: prints the user prompt after they select to view past entries
    private void printViewEntriesPrompt() {
        System.out.println("\nYou selected: view past entries");
        System.out.println("\nPlease select the entry category you would like to view:");
        printMoodList();
        System.out.println("\t6 - View all entries");
    }

    //EFFECTS: prints out an entry's ID, mood, and content
    private void printEntries(Entry e) {
        System.out.println("\nEntry " + e.getIdNumber());
        System.out.println("Mood: " + e.getMood());
        System.out.println(e.getContent());
    }

    //MODIFIES: this
    //EFFECTS: prompts the user to enter the ID number of the entry they would like to remove
    private void doRemovePastEntry() {
        System.out.println("\nYou selected: remove a past entry");
        System.out.println("\nEnter the ID number of the entry you would like to remove:");

        int inputInt = input.nextInt();
        try {
            myJournal.removeEntry(inputInt);
            System.out.println("\nEntry " + inputInt + " has been removed from your mood journal.");
        } catch (RemoveEntryNotInJournalException e) {
            System.out.println("\nThe entry ID you entered is not in your journal.");
        }
    }

    //EFFECTS: saves the journal to file
    private void saveJournal() {
        try {
            jsonWriter.open();
            jsonWriter.write(myJournal);
            jsonWriter.close();
            System.out.println("\nSuccessfully saved your journal to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads journal from file
    private void loadJournal() {
        try {
            myJournal = jsonReader.read();
            System.out.println("\nSuccessfully loaded your journal from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("\nUnable to read from file: " + JSON_STORE);
        } catch (NegativeIDException e) {
            System.out.println("\nThe journal you are trying to load contains a negative entry ID");
        } catch (EmptyContentException e) {
            System.out.println("\nThe journal you are trying to load contains an entry with no content");
        } catch (InvalidMoodException e) {
            System.out.println("\nThe journal you are trying to load contains an entry with an invalid mood");
        }
    }

}
