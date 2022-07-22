package model;

import java.util.ArrayList;
import java.util.List;

import static model.MoodType.*;

//Represents a mood journal with entries organized by emotion
public class Journal {
    private List<Entry> journalEntries;

    //EFFECTS: constructs a new journal with no entries
    public Journal() {
        journalEntries = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: if no entry in the journal has the same ID as the given entry:
    //add given entry to the journal and return true.
    //Otherwise, do nothing and return false.
    public boolean addEntry(Entry entry) {
        for (Entry e : journalEntries) {
            if (e.getIdNumber() == entry.getIdNumber()) {
                return false;
            }
        }
        journalEntries.add(entry);
        return true;
    }

    //REQUIRES: an entry with the given ID exists in the journal
    //MODIFIES: this
    //EFFECTS: remove the entry with the given ID from the journal
    public void removeEntry(int id) {
        Entry entryToRemove = new Entry("", 111, HAPPY);
        for (Entry e : journalEntries) {
            if (e.getIdNumber() == id) {
                entryToRemove = e;
            }
        }
        journalEntries.remove(entryToRemove);
    }

    //REQUIRES: there is at least one entry of the given mood type in the journal
    //EFFECTS: returns all entries in the journal of the given mood type
    public List<Entry> getEntriesOfMoodType(MoodType mood) {
        List<Entry> entriesOfSpecificMood = new ArrayList<>();
        for (Entry e : journalEntries) {
            if (e.getMood().equals(mood)) {
                entriesOfSpecificMood.add(e);
            }
        }
        return entriesOfSpecificMood;
    }

    //EFFECTS: returns all entries in the journal
    public List<Entry> getEntries() {
        return journalEntries; //stub
    }

}
