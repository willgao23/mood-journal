//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static model.MoodType.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class JournalTest {
//    Journal testJournal;
//    Entry testEntry1;
//    Entry testEntry2;
//    Entry testEntry3;
//
//    @BeforeEach
//    public void setUp() {
//        testJournal = new Journal();
//        testEntry1 = new Entry("Cafeteria ran out of eggs", 111, SAD);
//        testEntry2 = new Entry("Had a birthday party", 112, HAPPY);
//        testEntry3 = new Entry("Played with my dog", 113, HAPPY);
//    }
//
//    @Test
//    public void testConstructor() {
//        assertEquals(0, testJournal.getEntries().size());
//    }
//
//    @Test
//    public void testAddEntryOnce() {
//        assertTrue(testJournal.addEntry(testEntry1));
//
//        assertTrue(testJournal.getEntries().contains(testEntry1));
//        assertEquals(1, testJournal.getEntries().size());
//    }
//
//    @Test
//    public void testAddTwoDifferentEntries() {
//        assertTrue(testJournal.addEntry(testEntry1));
//        assertTrue(testJournal.addEntry(testEntry2));
//
//        assertTrue(testJournal.getEntries().contains(testEntry1));
//        assertTrue(testJournal.getEntries().contains(testEntry2));
//        assertEquals(2, testJournal.getEntries().size());
//    }
//
//    @Test
//    public void testAddSameEntryTwice() {
//        assertTrue(testJournal.addEntry(testEntry1));
//        assertFalse(testJournal.addEntry(testEntry1));
//
//        assertTrue(testJournal.getEntries().contains(testEntry1));
//        assertEquals(1, testJournal.getEntries().size());
//    }
//
//    @Test
//    public void testRemoveEntryOnce() {
//        testJournal.addEntry(testEntry1);
//        testJournal.removeEntry(111);
//
//        assertEquals(0, testJournal.getEntries().size());
//    }
//
//    @Test
//    public void testRemoveEntryMultipleTimes() {
//        testJournal.addEntry(testEntry1);
//        testJournal.addEntry(testEntry2);
//        testJournal.removeEntry(111);
//        testJournal.removeEntry(112);
//
//        assertEquals(0, testJournal.getEntries().size());
//    }
//
//    @Test
//    public void testGetEntriesOfMoodType() {
//        testJournal.addEntry(testEntry1);
//        testJournal.addEntry(testEntry2);
//        testJournal.addEntry(testEntry3);
//
//        assertTrue(testJournal.getEntriesOfMoodType(HAPPY).contains(testEntry2));
//        assertTrue(testJournal.getEntriesOfMoodType(HAPPY).contains(testEntry3));
//        assertEquals(2, testJournal.getEntriesOfMoodType(HAPPY).size());
//    }
//}
