package persistence;

import exceptions.EmptyContentException;
import exceptions.InvalidMoodException;
import exceptions.NegativeIDException;
import model.Entry;
import model.Journal;
import model.MoodType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Journal j = reader.read();
            fail("IOException expected");
        } catch (NegativeIDException e) {
            fail("Unexpected NegativeIDException");
        } catch (EmptyContentException e) {
            fail("Unexpected EmptyContentException");
        } catch (IOException e) {

        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        }
    }

    @Test
    public void testReaderEmptyJournal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyJournal.json");
        try {
            Journal j = reader.read();
            assertEquals(0, j.getEntries().size());
        } catch (NegativeIDException e) {
            fail("Unexpected NegativeIDException");
        } catch (EmptyContentException e) {
            fail("Unexpected EmptyContentException");
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        }
    }

    @Test
    public void testReaderGeneralJournal() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralJournal.json");
        try {
            Journal j = reader.read();
            List<Entry> entries = j.getEntries();
            assertEquals(2, entries.size());
            checkEntry("I had a great day!", 111, MoodType.Happy, entries.get(0));
            checkEntry("I got scared by my brother today", 222, MoodType.Scared, entries.get(1));
        } catch (NegativeIDException e) {
            fail("Unexpected NegativeIDException");
        } catch (EmptyContentException e) {
            fail("Unexpected EmptyContentException");
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        }

    }
}
