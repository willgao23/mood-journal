package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.MoodType.*;
import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {
    Entry testEntry;

    @BeforeEach
    public void setUp() {
        testEntry = new Entry("Today, I did nothing!", 111, HAPPY);
    }

    @Test
    public void testConstructor() {
        assertEquals("Today, I did nothing!", testEntry.getContent());
        assertEquals(111, testEntry.getIdNumber());
        assertEquals(HAPPY, testEntry.getMood());
    }

    @Test
    public void testSetContentOnce() {
        testEntry.setContent("I worked on my CPSC 210 project!");

        assertEquals("I worked on my CPSC 210 project!", testEntry.getContent());
    }

    @Test
    public void testSetContentMultipleTimes() {
        testEntry.setContent("I wrote unit tests for my project");
        testEntry.setContent("I finished the method implementation");

        assertEquals("I finished the method implementation", testEntry.getContent());
    }

    @Test
    public void testSetIDOnce() {
        testEntry.setIdNumber(112);

        assertEquals(112, testEntry.getIdNumber());
    }

    @Test
    public void testSetIDMultipleTimes() {
        testEntry.setIdNumber(112);
        testEntry.setIdNumber(113);

        assertEquals(113, testEntry.getIdNumber());
    }

    @Test
    public void testSetMoodOnce() {
        testEntry.setMood(SAD);

        assertEquals(SAD, testEntry.getMood());
    }

    @Test
    public void testSetMoodMultipleTimes() {
        testEntry.setMood(ANGRY);
        testEntry.setMood(SCARED);
        testEntry.setMood(DISGUSTED);

        assertEquals(DISGUSTED, testEntry.getMood());
    }
}

