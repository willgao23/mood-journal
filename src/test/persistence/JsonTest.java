package persistence;

import model.Entry;
import model.MoodType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEntry(String content, int idNumber, MoodType mood, Entry entry) {
        assertEquals(content, entry.getContent());
        assertEquals(idNumber, entry.getIdNumber());
        assertEquals(mood, entry.getMood());
    }
}
