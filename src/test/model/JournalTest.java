package model;

import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.MoodType.*;
import static org.junit.jupiter.api.Assertions.*;

public class JournalTest {
    Journal testJournal;
    Entry testEntry1;
    Entry testEntry2;
    Entry testEntry3;

    @BeforeEach
    public void setUp() {
        testJournal = new Journal();
        try {
            testEntry1 = new Entry("Cafeteria ran out of eggs", 111, SAD);
            testEntry2 = new Entry("Had a birthday party", 112, HAPPY);
            testEntry3 = new Entry("Played with my dog", 113, HAPPY);
        } catch (EmptyContentException e) {
            fail("Unexpected EmptyContentException");
        } catch (NegativeIDException e) {
            fail("Unexpected NegativeIDException");
        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testJournal.getEntries().size());
    }

    @Test
    public void testAddEntryOnce() {
        assertTrue(testJournal.addEntry(testEntry1));

        assertTrue(testJournal.getEntries().contains(testEntry1));
        assertEquals(1, testJournal.getEntries().size());
    }

    @Test
    public void testAddTwoDifferentEntries() {
        assertTrue(testJournal.addEntry(testEntry1));
        assertTrue(testJournal.addEntry(testEntry2));

        assertTrue(testJournal.getEntries().contains(testEntry1));
        assertTrue(testJournal.getEntries().contains(testEntry2));
        assertEquals(2, testJournal.getEntries().size());
    }

    @Test
    public void testAddSameEntryTwice() {
        assertTrue(testJournal.addEntry(testEntry1));
        assertFalse(testJournal.addEntry(testEntry1));

        assertTrue(testJournal.getEntries().contains(testEntry1));
        assertEquals(1, testJournal.getEntries().size());
    }

    @Test
    public void testRemoveEntryOnceNothingThrown() {
        testJournal.addEntry(testEntry1);
        try {
            testJournal.removeEntry(111);
        } catch (RemoveEntryNotInJournalException e) {
            fail("Unexpected RemoveEntryNotInJournalException");
        }

        assertEquals(0, testJournal.getEntries().size());
    }

    @Test
    public void testRemoveEntryMultipleTimesNothingThrown() {
        testJournal.addEntry(testEntry1);
        testJournal.addEntry(testEntry2);
        try {
            testJournal.removeEntry(111);
            testJournal.removeEntry(112);
        } catch (RemoveEntryNotInJournalException e) {
            fail("Unexpected RemoveEntryNotInJournalException");
        }

        assertEquals(0, testJournal.getEntries().size());
    }

    @Test
    public void testRemoveEntryExpectRemoveEntryNotInJournalException() {
        testJournal.addEntry(testEntry1);

        try {
            testJournal.removeEntry(112);
            fail("RemoveEntryNotInJournalException was not thrown!");
        } catch (RemoveEntryNotInJournalException e) {

        }

        assertEquals(1, testJournal.getEntries().size());
    }

    @Test
    public void testGetEntriesOfMoodTypeNothingThrown() {
        testJournal.addEntry(testEntry1);
        testJournal.addEntry(testEntry2);
        testJournal.addEntry(testEntry3);

        try {
            assertTrue(testJournal.getEntriesOfMoodType(HAPPY).contains(testEntry2));
            assertTrue(testJournal.getEntriesOfMoodType(HAPPY).contains(testEntry3));
            assertEquals(2, testJournal.getEntriesOfMoodType(HAPPY).size());
        } catch (NoEntriesOfTypeException e) {
            fail("Unexpected NoEntriesOfTypeException");
        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        }
    }

    @Test
    public void testGetEntriesOfMoodTypeExpectInvalidMoodException() {
        try {
            testJournal.getEntriesOfMoodType(INVALID);
            fail("InvalidMoodException was not thrown!");
        } catch (InvalidMoodException e) {

        } catch (NoEntriesOfTypeException e) {
            fail("Unexpected NoEntriesOfTypeException");
        }
    }

    @Test
    public void testGetEntriesOfMoodTypeExpectNoEntriesOfMoodTypeException() {
        try {
            testJournal.getEntriesOfMoodType(HAPPY);
            fail("NoEntriesOfTypeException was not thrown!");
        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        } catch (NoEntriesOfTypeException e) {

        }
    }
}
