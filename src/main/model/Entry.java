package model;

//Represents a journal entry with content, an ID number, and a mood
public class Entry {

    //EFFECTS: constructs a new entry with given content, ID number, and mood type
    public Entry(String content, int id, MoodType mood) {
        //stub
    }

    //REQUIRES: content != "", content length <= CONTENT_CHARACTER_LIMIT
    //MODIFIES: this
    //EFFECTS: change the entry's content to the given string
    public void setContent(String content) {
        //stub
    }

    //REQUIRES: id >= 0
    //MODIFIES: this
    //EFFECTS: change the entry's ID number to the given ID number
    public void setIdNumber(int id) {
        //stub
    }

    //REQUIRES: mood != null
    //MODIFIES: this
    //EFFECTS: change the entry's mood type to the given mood
    public void setMood(MoodType mood) {
        //stub
    }

    //EFFECTS: return the content of the entry
    public String getContent() {
        return ""; //stub
    }

    //EFFECTS: return the ID number of the entry
    public int getIdNumber() {
        return 0; //stub
    }

    //EFFECTS: return the mood category of the entry
    public MoodType getMood() {
        return null;
    }
}
