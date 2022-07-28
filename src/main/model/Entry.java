package model;

import exceptions.*;
import org.json.JSONObject;
import persistence.Writable;

//Represents a journal entry with content, an ID number, and a mood
public class Entry implements Writable {
    private String content;
    private int idNumber;
    private MoodType mood;

    //EFFECTS: constructs a new entry with given content, ID number, and mood type
    public Entry(String content, int id, MoodType mood) throws EmptyContentException, NegativeIDException,
            InvalidMoodException {
        setContent(content);
        setIdNumber(id);
        setMood(mood);
    }

    //MODIFIES: this
    //EFFECTS: change the entry's content to the given string
    //throws EmptyContentException if no content is added
    public void setContent(String content) throws EmptyContentException {
        if (content.equals("")) {
            throw new EmptyContentException();
        }
        this.content = content;
    }

    //MODIFIES: this
    //EFFECTS: change the entry's ID number to the given ID number
    //throws NegativeIDException if the given ID is negative
    public void setIdNumber(int id) throws NegativeIDException {
        if (id < 0) {
            throw new NegativeIDException();
        }
        this.idNumber = id;
    }

    //MODIFIES: this
    //EFFECTS: change the entry's mood type to the given mood
    //thrown InvalidMoodException if the given MoodType is invalid
    public void setMood(MoodType mood) throws InvalidMoodException {
        if (mood.equals(MoodType.INVALID)) {
            throw new InvalidMoodException();
        }
        this.mood = mood;
    }

    //EFFECTS: return the content of the entry
    public String getContent() {
        return content;
    }

    //EFFECTS: return the ID number of the entry
    public int getIdNumber() {
        return idNumber;
    }

    //EFFECTS: return the mood category of the entry
    public MoodType getMood() {
        return mood;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("content", content);
        json.put("idNumber", idNumber);
        json.put("mood", mood);
        return json;
    }
}
