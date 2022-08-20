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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;

public class Actions extends Observable {
    private static final String JSON_STORE = "./data/journal.json";
    private Journal journal;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Actions(Journal journal) {
        this.journal = journal;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public void addAction(String content, int idNumber, MoodType mood) throws NegativeIDException,
            EmptyContentException, InvalidMoodException {
        Entry newEntry = new Entry(content, idNumber, mood);
        journal.addEntry(newEntry);
        setChanged();
        notifyObservers(journal);
    }

    public void removeAction(int intIdNumber) throws RemoveEntryNotInJournalException {
        journal.removeEntry(intIdNumber);
        setChanged();
        notifyObservers(journal);
    }

    public void saveAction() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(journal);
        jsonWriter.close();
    }

    public void loadAction() throws NegativeIDException, EmptyContentException, IOException, InvalidMoodException {
        journal = jsonReader.read();
        setChanged();
        notifyObservers(journal);

    }
}
