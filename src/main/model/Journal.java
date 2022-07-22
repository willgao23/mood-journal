package model;

import java.util.List;

//Represents a mood journal with entries organized by emotion
public class Journal {

    //EFFECTS: constructs a new journal with no entries in any category
    public Journal() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: add entry to the correct list based on its MoodType and add it to the all entries list
    // return true if adding is successful; otherwise return false
    public boolean addEntry(Entry entry) {
        return true; //stub
    }

    //EFFECTS: returns a random journal prompt from those listed in JournalPrompts
    public String getRandomJournalPrompt() {
        return ""; //stub
    }

    //EFFECTS: return the list of entries categorized as HAPPY in the journal
    public List<Entry> getHappyEntries() {
        return null; //stub
    }

    //EFFECTS: return the list of entries categorized as SCARED in the journal
    public List<Entry> getScaredEntries() {
        return null; //stub
    }

    //EFFECTS: return the list of entries categorized as ANGRY in the journal
    public List<Entry> getAngryEntries() {
        return null; //stub
    }

    //EFFECTS: return the list of entries categorized as DISGUSTED in the journal
    public List<Entry> getDisgustedEntries() {
        return null; //stub
    }

    //EFFECTS: return the list of entries categorized as SAD in the journal
    public List<Entry> getSadEntries() {
        return null; //stub
    }

    //EFFECTS: return a list of all entries in the journal
    public List<Entry> getAllEntries() {
        return null; //stub
    }
}
