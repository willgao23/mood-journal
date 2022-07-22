package model;

//Represents a journal entry with content, an ID number, and a mood
public class Entry {

    //EFFECTS: constructs a new entry with no content, ID number, or mood type
    public Entry() {
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
    //EFFECTS: change the entry's title to the given title
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

    //EFFECTS: return the title of the entry
    public int getIdNumber() {
        return 0; //stub
    }

    //EFFECTS: return the mood category of the entry
    public MoodType getMood() {
        return null;
    }
}
