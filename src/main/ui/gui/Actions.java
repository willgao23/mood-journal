package ui.gui;

import exceptions.ChangeEntryNotInJournalException;
import exceptions.EmptyContentException;
import exceptions.InvalidMoodException;
import exceptions.NegativeIDException;
import model.Entry;
import model.Journal;
import model.MoodType;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;

// Represents the possible actions when interacting with the GUI
public class Actions extends Observable {
    private static final String JSON_STORE = "./data/journal.json";
    private Journal journal;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructs a series of actions with the given journal and a JSON reader and writer
    public Actions(Journal journal) {
        this.journal = journal;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: creates a new entry with the given content, id number, and mood, and adds it
    //to the journal
    public void addAction(String content, int idNumber, MoodType mood) throws NegativeIDException,
            EmptyContentException, InvalidMoodException {
        Entry newEntry = new Entry(content, idNumber, mood);
        journal.addEntry(newEntry);
        setChanged();
        notifyObservers(journal);
    }

    //MODIFIES: this
    //EFFECTS: removes an entry with the given id number from the journal
    public void removeAction(int intIdNumber) throws ChangeEntryNotInJournalException {
        journal.removeEntry(intIdNumber);
        setChanged();
        notifyObservers(journal);
    }

    //MODIFIES: this
    //EFFECTS: edits the entry with the given id number with the given content and mood
    public void editAction(String content, int idNumber, MoodType mood) throws InvalidMoodException,
            EmptyContentException, ChangeEntryNotInJournalException {
        journal.editEntry(content, idNumber, mood);
        setChanged();
        notifyObservers(journal);
    }

    //EFFECTS: saves the current journal to file
    public void saveAction() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(journal);
        jsonWriter.close();
    }

    //MODIFIES: this
    //EFFECTS: reads the journal from file and sets it as the current journal
    public void loadAction() throws NegativeIDException, EmptyContentException, IOException, InvalidMoodException {
        journal = jsonReader.read();
        setChanged();
        notifyObservers(journal);

    }
}
