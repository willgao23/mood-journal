package model;

import exceptions.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

import static model.MoodType.*;

//Represents a mood journal with entries organized by emotion
public class Journal implements Writable {
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
        Event addEvent = new Event("Entry added to journal");
        EventLog.getInstance().logEvent(addEvent);
        return true;
    }

    //MODIFIES: this
    //EFFECTS: remove the entry with the given ID from the journal
    //throws RemoveEntryNotInJournalException if there is no entry with the given ID in the journal
    public void removeEntry(int id) throws RemoveEntryNotInJournalException {
        int initialSize = journalEntries.size();
        for (int i = 0; i < journalEntries.size(); i++) {
            if (journalEntries.get(i).getIdNumber() == id) {
                journalEntries.remove(i);
                Event removeEvent = new Event("Entry removed from journal");
                EventLog.getInstance().logEvent(removeEvent);
            }
        }
        if (initialSize == journalEntries.size()) {
            throw new RemoveEntryNotInJournalException();
        }
    }

    //EFFECTS: returns all entries in the journal of the given mood type
    //throws InvalidMoodException if the given mood is invalid
    //throws NoEntriesOfTypeException if there are no entries of the given type in the journal
    public List<Entry> getEntriesOfMoodType(MoodType mood) throws NoEntriesOfTypeException, InvalidMoodException {
        List<Entry> entriesOfSpecificMood = new ArrayList<>();

        if (mood.equals(Invalid)) {
            throw new InvalidMoodException();
        }

        for (Entry e : journalEntries) {
            if (e.getMood().equals(mood)) {
                entriesOfSpecificMood.add(e);
            }
        }

        if (entriesOfSpecificMood.size() == 0) {
            throw new NoEntriesOfTypeException();
        }
        return entriesOfSpecificMood;
    }

    //EFFECTS: returns all entries in the journal
    public List<Entry> getEntries() {
        return journalEntries;
    }

    //EFFECTS: adds entries to journal JSONObject and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("entries", entriesToJson());
        return json;
    }

    //EFFECTS: returns entries in this journal as a JSON array
    private JSONArray entriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Entry e: journalEntries) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
