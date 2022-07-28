package persistence;

import org.json.JSONObject;

// Represents objects that are used as JSON objects
public interface Writable {
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
