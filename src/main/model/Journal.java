package model;

import java.util.List;

//Represents a mood journal with entries organized by emotion
public class Journal {

    //EFFECTS: constructs a new journal with no entries
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

    //REQUIRES: an entry with the given ID exists in the journal
    //MODIFIES: this
    //EFFECTS: remove the entry with the given ID from the journal
    public void removeEntry(int id) {
        //stub
    }

    //REQUIRES: there is at least one entry of the given mood type in the journal
    //EFFECTS: returns all entries in the journal of the given mood type
    public List<Entry> getEntriesOfMoodType(MoodType mood) {
        return null; //stub
    }

    //EFFECTS: returns all entries in the journal
    public List<Entry> getEntries() {
        return null; //stub
    }

}
