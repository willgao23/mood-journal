package persistence;

import exceptions.EmptyContentException;
import exceptions.InvalidMoodException;
import exceptions.NegativeIDException;
import model.Entry;
import model.Journal;
import model.MoodType;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Journal j = new Journal();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {

        }
    }

    @Test
    public void testWriterEmptyJournal() {
        try {
            Journal j = new Journal();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyJournal.json");
            writer.open();
            writer.write(j);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyJournal.json");
            j = reader.read();
            assertEquals(0, j.getEntries().size());
        } catch (NegativeIDException e) {
            fail("Unexpected NegativeIDException");
        } catch (EmptyContentException e) {
            fail("Unexpected EmptyContentException");
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        }
    }

    @Test
    public void testWriterGeneralJournal() {
        try {
            Journal j = new Journal();
            j.addEntry(new Entry("Had lunch with friends", 111, MoodType.Happy));
            j.addEntry(new Entry("Didn't get a promotion", 222, MoodType.Sad));
            JsonWriter writer = new JsonWriter ("./data/testWriterGeneralJournal.json");
            writer.open();
            writer.write(j);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralJournal.json");
            j = reader.read();
            List<Entry> entries = j.getEntries();
            assertEquals(2, entries.size());
            checkEntry("Had lunch with friends", 111, MoodType.Happy, entries.get(0));
            checkEntry("Didn't get a promotion", 222, MoodType.Sad, entries.get(1));
        } catch (NegativeIDException e) {
            fail("Unexpected NegativeIDException");
        } catch (EmptyContentException e) {
            fail("Unexpected EmptyContentException");
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        }
    }
}
