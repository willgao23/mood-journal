package persistence;

import exceptions.EmptyContentException;
import exceptions.InvalidMoodException;
import exceptions.NegativeIDException;
import model.Entry;
import model.Journal;
import model.MoodType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// based on JsonSerializationDemo; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads journal from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads journal from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Journal read() throws IOException, NegativeIDException,
            EmptyContentException, InvalidMoodException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseJournal(jsonObject);
    }

    //EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses journal from JSON object and returns it
    private Journal parseJournal(JSONObject jsonObject) throws NegativeIDException,
            EmptyContentException, InvalidMoodException {
        Journal j = new Journal();
        addEntries(j, jsonObject);
        return j;
    }

    //MODIFIES: j
    //EFFECTS: parses entries from JSON object and adds them to journal
    private void addEntries(Journal j, JSONObject jsonObject) throws NegativeIDException,
            EmptyContentException, InvalidMoodException {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(j, nextEntry);
        }
    }

    //MODIFIES: j
    //EFFECTS: parses entry from JSON object and adds it to journal
    private void addEntry(Journal j, JSONObject jsonObject) throws NegativeIDException,
            EmptyContentException, InvalidMoodException {
        String content = jsonObject.getString("content");
        int idNumber = jsonObject.getInt("idNumber");
        MoodType mood = MoodType.valueOf(jsonObject.getString("mood"));
        Entry entry = new Entry(content, idNumber, mood);
        j.addEntry(entry);
    }
}
