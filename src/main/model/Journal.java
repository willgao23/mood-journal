package model;

import java.util.List;

//Represents a mood journal with entries organized by emotion
public class Journal {

    //EFFECTS: constructs a new journal with no entries in any category
    public Journal() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: if no entry in the journal has the same ID as the given entry:
    //add given entry to the journal and return true.
    //Otherwise, do nothing and return false.
    public boolean addEntry(Entry entry) {
        return false; //stub
    }

    //MODIFIES: this
    //EFFECTS: if an entry with the given ID exists in the journal:
    //remove that entry and return true.
    //Otherwise, do nothing and return false.
    public boolean removeEntry(int id) {
        return false; //stub
    }

    //EFFECTS: returns all entries in the journal
    public List<Entry> getEntries() {
        return null; //stub
    }

    //EFFECTS: returns all entries in the journal of the given mood type
    public List<Entry> getEntriesOfMoodType(MoodType mood) {
        return null; //stub
    }

}
